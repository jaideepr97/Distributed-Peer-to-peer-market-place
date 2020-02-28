package main.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import main.java.Client;
import main.java.Server;
import main.java.propgatedMessage.Message;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

//public class PeerNode implements P2PInterface
public class PeerNode
{
//    private static final String JAVA_FILE_LOCATION = "/Users/aayushgupta/IdeaProjects/lab-1-rao-gupta/src/main/java/";
    private static final String CONFIG_FILE_LOCATION = "/home/hadoopuser/Desktop/lab-1-rao-gupta/src/main/java/Config.txt";

    static int peerPort;
    static int neighbourPort;
    static int peerId;

//    private ConcurrentHashMap<Message, Integer> sharedRequestBuffer = new ConcurrentHashMap

    public static void main(String[] args)  {
//        peerPort = 5050;
        peerId = 1;

        int lineNumber;
        try {

            FileReader readfile = new FileReader(new File(CONFIG_FILE_LOCATION));
            BufferedReader readbuffer = new BufferedReader(readfile);
            int i = peerId*2;
            for (lineNumber = i; lineNumber < i+2; lineNumber++) {
                if (lineNumber %2 == 1) {
                    peerPort = Integer.parseInt(readbuffer.readLine());
                } else
                    neighbourPort = Integer.parseInt(readbuffer.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Client client = new Client(peerPort, peerId);
        Server server = new Server(neighbourPort);
        Thread clientThread = new Thread(client);
        Thread serverThread = new Thread(server);
        serverThread.start();
        clientThread.start();
    }

//    @Override
//    public void lookup(String productName, int hopCount) {
//
//    }
//
//    @Override
//    public void reply(String sellerID) {
//
//    }
//
//    @Override
//    public void buy(String sellerID) {
//
//    }
}
