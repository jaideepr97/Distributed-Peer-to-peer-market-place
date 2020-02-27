package main.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Server implements Runnable
{
    int port = 5000;
    ServerSocket serverSocket ;
    Socket clientSocket ;
    BufferedReader bufferedReader ;
    DataOutputStream outputStream ;
    volatile boolean running;

    public Server()
    {
        running = true;
    }
    public Server(int _port)
    {
        this.port = _port;
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
        try
        {
            while(running)
            {
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String data = null;
                data = bufferedReader.readLine();
                if(data != null)
                {
                    //Check if the current server has items
                    System.out.println("Received:"+ data +"\n");
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("(listen): Master Heartbeat for worker" + 0 + "IO exception\n");
        }
        finally {
            serverSocket.close();
            clientSocket.close();
            bufferedReader.close();
        }
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
