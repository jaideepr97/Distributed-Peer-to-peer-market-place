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
//        int counter = 0;
        while(running) {
            try
            {
                    this.getSocket();
                    try {
                        clientOutputStream.writeBytes(this.message + "\n");
                    } catch (IOException e) {
                        System.out.println("Client: IOException\n");
                    }
            }
            catch (Exception e)
            {
                System.out.println("Client: Exception in run():"+e.getStackTrace()+"\n");
            }
        }
//        }


    }

    @Override
    public void lookup(String productName, int hopCount) {

    }

    @Override
    public void buy(String sellerID) {

    }
}
