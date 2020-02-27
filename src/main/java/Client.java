package main.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Client implements Runnable
{
    private int port = 5000;
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
    public Client(int _port)
    {
        this.port = _port;
        running = true;

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

        try
        {
            this.getSocket();
            try{
                clientOutputStream.writeBytes(this.message+"\n");
            }
            catch (IOException e){
                System.out.println("Client: IOException\n");
            }

        }
        catch (Exception e)
        {
            System.out.println("Client: Exception in run():"+e.getStackTrace()+"\n");
        }


    }
}
