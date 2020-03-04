//package main.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;

public class Server implements Runnable
{
    int port;
    int peerID;
    int productToSell;
    int sellQuantity;
    ServerSocket serverSocket ;
    Socket clientSocket ;
    BufferedReader bufferedReader ;
    DataOutputStream outputStream ;
    volatile boolean running;
    public Server()
    {
        running = true;
    }
    public Server(int _port, int _peerID, int _productToSell, int _sellQuantity)
    {
        this.port = _port;
        this.peerID = _peerID;
        this.productToSell = _productToSell;
        this.sellQuantity = _sellQuantity;
        running = true;
    }


    public void stopThread()
    {
        running = false;
    }
    public void closeSocket() throws IOException
    {
        if(outputStream!= null)
        {
            outputStream.close();
        }
        if(clientSocket != null)
        {
            clientSocket.close();
        }
        if(serverSocket != null)
        {
            serverSocket.close();
        }
    }
    public void listen() throws IOException
    {
        serverSocket = new ServerSocket(port);
        long ut1 = Instant.now().getEpochSecond();
        try
        {
            clientSocket = serverSocket.accept();
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
//                System.out.println("Server:"+peerID+", Listening........\n");
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String data = null;
            while((data = bufferedReader.readLine()) != null)
            {
//                    System.out.println("Server:"+peerID+", Data Received!\n");
                Message message = Message.deserializeMessage(data);
                synchronized (PeerNode.requestHistory)
                {
                    if(PeerNode.requestHistory.keySet().contains(message) ) {
//                            System.out.println("Server:"+peerID+", Request already serviced\n");
                        continue;
                    }
                }
                if(message.getType() == 0 && message.getHopCount() < 1) {
//                        System.out.println("Server:"+peerID+", Hops exhausted for this message\n");
                    continue;
                }
                switch(message.getType()) {
                    case 0:
//                            System.out.println("Server:"+peerID+", Request Type 0\n");
                        if((PeerNode.role == 1 || PeerNode.role == 2) &&
                                message.getProductId() == PeerNode.productToSell) {
//                                System.out.println("Server:"+peerID+", Seller has the product!\n");
                            Message replyMessage = new Message();
                            replyMessage.setDestinationSellerId(this.peerID);
                            replyMessage.setProductName(PeerNode.productMap.get(message.getProductId()));
                            replyMessage.setType(1);
                            replyMessage.setSourcePeerId(message.getSourcePeerId());
                            replyMessage.setMessagePath(message.getMessagePath());
                            replyMessage.setRequestId(message.getRequestId());
                            replyMessage.setProductId(message.getProductId());
                            replyMessage.setHopCount(0);
                            replyMessage.setProductName(message.getProductName());
                            /*
                            synchronized (PeerNode.sharedReplyBuffer) {
                                boolean contains = false;
                                for(Message m: PeerNode.sharedReplyBuffer) {
                                    if(m.equals(replyMessage)) {
                                        contains = true;
                                        break;
                                    }
                                }
                                if(!contains) {
                                    System.out.println("Server:"+peerID+", Adding to sharedReplyBuffer.\n");
                                    PeerNode.sharedReplyBuffer.offer(message);
                                }
                                else {
                                    continue;
                                }

                            }
                             */
                            synchronized (PeerNode.sharedReplyBuffer)
                            {
//                                    System.out.println("Server:"+peerID+", Adding to sharedReplyBuffer.\n");
                                PeerNode.sharedReplyBuffer.offer(replyMessage);
                            }
                        }
                        else {
                            if(PeerNode.role == 0)
                            {
//                                    System.out.println("Server:"+peerID+", is not a seller, forwarding...\n");
                            }
                            else
                            {
//                                    System.out.println("Server:"+peerID+", Seller does not have the product, forwarding...\n");
                            }
                            synchronized (PeerNode.sharedRequestBuffer) {
//                                    System.out.println("Server:"+peerID+", Adding to sharedRequestBuffer.\n");
                                PeerNode.sharedRequestBuffer.offer(message);
                            }
                        }
                        break;
                    case 1:
//                            System.out.println("Server:"+peerID+", Request Type 1\n");
                        if(message.getSourcePeerId() == this.peerID) {
//                                System.out.println("Server:"+peerID+", This is the request originator!\n");
                            Message replyMessage = new Message();
                            replyMessage.setDestinationSellerId(message.getDestinationSellerId());
                            replyMessage.setProductName(PeerNode.productMap.get(message.getProductId()));
                            replyMessage.setType(2);
                            replyMessage.setSourcePeerId(message.getSourcePeerId());
                            replyMessage.setMessagePath(message.getMessagePath());
                            replyMessage.setRequestId(message.getRequestId());
                            replyMessage.setProductId(message.getProductId());
                            replyMessage.setHopCount(0);
                            replyMessage.setProductName(message.getProductName());
                            synchronized (PeerNode.sharedTransactionBuffer) {
//                                    System.out.println("Server:"+peerID+", Adding to sharedTransactionBuffer.\n");
                                PeerNode.sharedTransactionBuffer.offer(replyMessage);
                            }
                            System.out.println("\n#############Reply received from:"+ message.getDestinationSellerId()+
                                    " for product:"+ message.getProductName()+"#############\n");
                        }
                        else {
//                                System.out.println("Server:"+peerID+", This is not the originator, forwarding...\n");
                            synchronized (PeerNode.sharedReplyBuffer) {
//                                    System.out.println("Server:"+peerID+", Adding to sharedReplyBuffer.\n");
                                PeerNode.sharedReplyBuffer.offer(message);
                            }
                        }

                        break;
                    case 2:
//                            System.out.println("Server:"+peerID+", Request Type 2\n");
                        synchronized (PeerNode.numberOfItems) {
                            if(PeerNode.numberOfItems > 0) {
//                                    System.out.println("Server:"+peerID+", Decrementing number of items\n");
                                PeerNode.numberOfItems -= 1;
//                                    System.out.println("Server:"+peerID+", New number of items:"+PeerNode.numberOfItems+"\n");
                                System.out.println("\n----------Product:"+message.getProductName()
                                        +" sold!! Buyer:"+message.getSourcePeerId()+", Seller:"
                                                +peerID+" -------------\n");
//                                    System.out.println("\nDetails:Product:"+message.getProductName()+
//                                            ", Buyer:"+message.getSourcePeerId()+", Seller:"+peerID+"\n");
                            }
                            outputStream = new DataOutputStream(clientSocket.getOutputStream());
                            outputStream.writeBytes("0" + "\n");
                        }
                        break;
                }
                //Check if the current server has items
                synchronized (PeerNode.requestHistory) {
//                        System.out.println("Server:"+peerID+", Adding to requestHistory.\n");
                    PeerNode.requestHistory.put(message, 0);
                }
            }
//                this.stopThread();
        }

        catch (IOException e)
        {
            System.out.println("Server:"+peerID+", Exception in listen():\n");
            e.printStackTrace();
        }
//        finally {
        this.closeSocket();
//        }
    }

    public void run(){

        try
        {
//            System.out.println("Starting Server for peerID:"+this.peerID+" and port:"+this.port+"\n");
            listen();
        }
        catch(Exception e)
        {
            System.out.println("Server:"+this.peerID+", Exception in run():\n");
            e.printStackTrace();
        }

    }



}
