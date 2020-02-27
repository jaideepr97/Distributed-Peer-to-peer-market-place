package main.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerNode implements P2PInterface
{
    private static final String JAVA_FILE_LOCATION = "/Users/aayushgupta/IdeaProjects/lab-1-rao-gupta/src/main/java/";
    public static void main(String[] args)  {

        System.out.println("Hello World!");
        Client client = new Client();

        Thread clientThread = new Thread(client);


    }

    @Override
    public void lookup(String productName, int hopCount) {

    }

    @Override
    public void reply(String sellerID) {

    }

    @Override
    public void buy(String sellerID) {

    }
}
