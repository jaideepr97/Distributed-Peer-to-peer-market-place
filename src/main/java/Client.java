//package main.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public Client()
    {
        running = true;
    }
    public Client(int _port, int peerId, Message message)
    {
        this.port = _port;
        this.peerId = peerId;
        this.message = message;
        running = true;
    }

    public void stopThread()
    {
        running = false;
    }

    public void getSocket() throws IOException
    {
        clientSocket = new Socket("localhost", this.port);
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
    public void run() {
        while(running) {
            try
            {
                this.getSocket();
                if(this.message.getType() == 0) {
                    lookup(this.message.getProductName(), this.message.getHopCount());
                }
                else if(this.message.getType() == 1) {
                    int destinationPeerId = this.message.messagePath.get(this.message.messagePath.size() -1);
                    reply(destinationPeerId);
                }
                else if(this.message.getType() == 2) {
                    buy(this.message.getDestinationSellerId());
                }
            }
            catch (Exception e)
            {
                System.out.println("Client: Exception in run():"+e.getStackTrace()+"\n");
            }
        }

    }

    @Override
    public void lookup(String productName, int hopCount) {
        try {
            this.message.setHopCount(hopCount-1);
            this.message.messagePath.add(this.peerId);
            clientOutputStream.writeBytes(Message.serializeMessage(this.message));

        } catch (IOException e) {
            System.out.println("Client: IOException\n");
        }
        finally {
            this.stopThread();
        }
    }

    @Override
    public void buy(int sellerID) {
        try {
            clientOutputStream.writeBytes(Message.serializeMessage(this.message));
        } catch (IOException e) {
            System.out.println("Client: IOException\n");
        }
        finally {
            this.stopThread();
        }
    }
    @Override
    public void reply(int sellerID) {
        this.message.messagePath.remove(this.message.messagePath.size() -1);
        try {
            clientOutputStream.writeBytes(Message.serializeMessage(this.message));
        } catch (IOException e) {
            System.out.println("Client: IOException\n");
        }
        finally {
            this.stopThread();
        }
    }
}
