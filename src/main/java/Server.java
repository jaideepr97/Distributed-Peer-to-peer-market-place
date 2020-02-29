//package main.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
        clientSocket = serverSocket.accept();
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
        bufferedReader = null;
        while(running)
        {
            try
            {

                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String data = null;
                data = bufferedReader.readLine();
                if(data != null)
                {
                    Message message = Message.deserializeMessage(data);
                    if(PeerNode.requestHistory.keySet().contains(message)) {
                        continue;
                    }
                    switch(message.getType()) {
                        case 0:
                            if(message.getProductId() == PeerNode.productToSell) {
                                Message replyMessage = new Message();
                                replyMessage.setDestinationSellerId(this.peerID);
                                replyMessage.setType(1);
                                replyMessage.setSourcePeerId(message.getSourcePeerId());
                                replyMessage.setMessagePath(message.getMessagePath());
                                replyMessage.setRequestId(message.getRequestId());
                                replyMessage.setProductId(message.getProductId());
                                replyMessage.setHopCount(0);
                                replyMessage.setProductName(message.getProductName());

                                synchronized (PeerNode.sharedReplyBuffer) {
                                    PeerNode.sharedReplyBuffer.offer(message);
                                }

                            }
                            else {
                                synchronized (PeerNode.sharedRequestBuffer) {
                                    PeerNode.sharedRequestBuffer.offer(message);
                                }
                            }

                            break;
                        case 1:
                            if(message.getSourcePeerId() == this.peerID) {
                                Message replyMessage = new Message();
                                replyMessage.setDestinationSellerId(message.getDestinationSellerId());
                                replyMessage.setType(2);
                                replyMessage.setSourcePeerId(message.getSourcePeerId());
                                replyMessage.setMessagePath(message.getMessagePath());
                                replyMessage.setRequestId(message.getRequestId());
                                replyMessage.setProductId(message.getProductId());
                                replyMessage.setHopCount(0);
                                replyMessage.setProductName(message.getProductName());

                                synchronized (PeerNode.sharedTransactionBuffer) {
                                    PeerNode.sharedTransactionBuffer.offer(message);
                                }
                            }
                            else {
                                synchronized (PeerNode.sharedReplyBuffer) {
                                    PeerNode.sharedReplyBuffer.offer(message);
                                }
                            }

                            break;
                        case 2:
                            synchronized (PeerNode.numberOfItems) {
                                if(PeerNode.numberOfItems > 0) {
                                    PeerNode.numberOfItems -= 1;
                                }
                            }
                            break;
                    }
                    //Check if the current server has items
                    synchronized (PeerNode.requestHistory) {
                        PeerNode.requestHistory.put(message, 0);
                    }
                    System.out.println("Received:"+ data +"\n");
                }
//                this.stopThread();
            }

            catch (IOException e)
            {
            System.out.println("(listen): Master Heartbeat for worker" + 0 + "IO exception\n");
            }
        }

//        finally {
            serverSocket.close();
            clientSocket.close();
            bufferedReader.close();
//        }
    }

    public void run(){

        try
        {
            System.out.println("Server: Starting listen() for Server:" +this.port+"\n");
            listen();
        }
        catch(Exception e)
        {
            System.out.println("Server: Exception in run(): "+e.getStackTrace());
        }

    }



}
