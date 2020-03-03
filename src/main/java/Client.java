//package main.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;

public class Client implements Runnable, P2PBuyerInterface
{
    private int port;
    private int peerId;
    private Socket clientSocket;
    DataOutputStream clientOutputStream;
    BufferedReader bufferedReader;
    volatile boolean running;
    Message message;
    int nextPeerID;
    String host;
    public Client()
    {
        running = true;
    }
    public Client(String _host, int _port, int peerId, Message message, int _nextPeerID)
    {
        this.port = _port;
        this.peerId = peerId;
        this.message = message;
        this.nextPeerID = _nextPeerID;
        running = true;
        this.host = _host;
    }

    public void stopThread()
    {
        running = false;
    }

    public void getSocket() throws IOException
    {
        clientSocket = new Socket(this.host, this.port);
        clientOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    public void closeSocket() throws IOException
    {
        if(clientSocket != null) {
            clientSocket.close();
        }
        if(clientOutputStream != null) {
            clientOutputStream.close();
        }
        if(bufferedReader != null) {
            bufferedReader.close();
        }
    }

    @Override
    public void run()  {
        while(running) {
            try
            {
//                System.out.println("Client:"+peerId+", Sending request......\n");
                this.getSocket();
                if(this.message.getType() == 0) {
//                    System.out.println("Client:"+peerId+", Request type is 0\n");
                    lookup(this.message.getProductName(), this.message.getHopCount());
                }
                else if(this.message.getType() == 1) {
//                    System.out.println("Client:"+peerId+", Request type is 1\n");
                    int destinationPeerId = this.nextPeerID;
                    reply(destinationPeerId);
                }
                else if(this.message.getType() == 2) {
//                    System.out.println("Client:"+peerId+", Request type is 2\n");
                    buy(this.message.getDestinationSellerId());
                }
            }
            catch (ConnectException e)
            {
                System.out.println("Client:"+peerId+", ConnectException in run():\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            catch (Exception e)
            {
                System.out.println("Client:"+peerId+", Exception in run():\n");
                e.printStackTrace();
            }
        }

    }

    @Override
    public void lookup(String productName, int hopCount) {
//        System.out.println("Client:"+peerId+", Inside lookup()\n");
        try {
            //this.message.setHopCount(hopCount-1);
            //this.message.messagePath.add(this.peerId);
            clientOutputStream.writeBytes(Message.serializeMessage(this.message));
//            System.out.println("Client:"+peerId+", Message sent");

        } catch (IOException e) {
            System.out.println("Client:"+peerId+", Exception in lookup():\n");
            e.printStackTrace();
        }
        finally {
            this.stopThread();
        }
    }

    @Override
    public void buy(int sellerID) {
//        System.out.println("Client:"+peerId+", Inside buy()\n");
        try {
            clientOutputStream.writeBytes(Message.serializeMessage(this.message));
            synchronized (PeerNode.servicedRequests)
            {
                PeerNode.servicedRequests.put(this.message.getRequestId(), 0);
            }
            String ack = bufferedReader.readLine();
            if(ack.equals("0"))
            {
                System.out.println("-------Bought product:"+message.getProductName()+
                        " from seller:"+message.getDestinationSellerId()+"------------");
            }

        } catch (IOException e) {
            System.out.println("Client:"+peerId+", Exception in buy():\n");
            e.printStackTrace();
        }
        finally {
            this.stopThread();
        }
    }
    @Override
    public void reply(int sellerID) {
//        System.out.println("Client:"+peerId+", Inside reply()\n");
        //this.message.messagePath.remove(this.message.messagePath.size() -1);
        try {
            clientOutputStream.writeBytes(Message.serializeMessage(this.message));
        } catch (IOException e) {
            System.out.println("Client:"+peerId+", Exception in reply():\n");
            e.printStackTrace();
        }
        finally {
            this.stopThread();
        }
    }
}
