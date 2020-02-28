//package main.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable
{
    private int port = 5000;
    private int peerId;
    private Socket clientSocket;
    DataOutputStream clientOutputStream;
    BufferedReader bufferedReader;
    volatile boolean running;
    String message;
    public Client()
    {
        running = true;
        message = "";
    }
    public Client(int _port, int peerId)
    {
        this.port = _port;
        this.peerId = peerId;
        running = true;
    }

    public void stopThread()
    {
        running = false;
    }
    public void setMessage(String m)
    {
        this.message = m;
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
        int counter = 0;
        while(running) {
            try
            {
                    counter++;
//                    System.out.println(counter);
                    this.getSocket();
                    this.setMessage("Client with id" + this.peerId + " says hi");
                    try {
                        clientOutputStream.writeBytes(this.message + "\n");
                    } catch (IOException e) {
                        System.out.println("Client: IOException\n");
                    }
//                    if (counter > 100)
//                        this.stopThread();

            }
            catch (Exception e)
            {
                System.out.println("Client: Exception in run():"+e.getStackTrace()+"\n");
            }
        }
//        }


    }
}
