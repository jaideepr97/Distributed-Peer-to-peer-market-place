
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
//import main.java.Client;
//import main.java.Server;
//import main.java.propgatedMessage.Message;
//import main.java.Config;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class PeerNode {
//    private static final String CONFIG_FILE_LOCATION = "/Users/aayushgupta/IdeaProjects/lab-1-rao-gupta/";
    private static final String CONFIG_FILE_LOCATION = "/home/hadoopuser/Desktop/lab-1-rao-gupta/";
    private static final String FILENAME = "StarTopologySixNodesConfig.txt";


    private static Config config;
    static int peerID;
    static int requestId;
    static int productToSell;
    public static Integer numberOfItems;
    static int productToBuy;
    static boolean running = true;
    public static ConcurrentLinkedQueue<Message> sharedRequestBuffer = new ConcurrentLinkedQueue<>();
    public static ConcurrentHashMap<Message, Integer> requestHistory = new ConcurrentHashMap();
    public static ConcurrentLinkedQueue<Message> sharedReplyBuffer = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Message> sharedTransactionBuffer = new ConcurrentLinkedQueue<>();


    public static void main(String[] args)  {

        peerID = Integer.parseInt(args[0]);
        System.out.println("---------Starting peer with ID: "+ peerID+"----------\n");
        requestId = 0;
        System.out.println("Getting Config for peerID:"+peerID+"\n");
        getConfig();
        System.out.println("Setting Seller details for peerID:"+peerID+"\n");
        getSellDetails();
//        getBuyDetails();
        if(config == null)
        {
            System.out.println("Error: Config file not found!! for peerID:"+peerID+"\n");
            return;
        }
        System.out.println("Starting server threads for peerID:"+peerID+"\n");
        for (int i=0; i<config.getServerPorts().size(); i++)
        {
            Server server = new Server(config.getServerPorts().get(i), peerID, productToSell, numberOfItems);
            Thread serverThread = new Thread(server);
            serverThread.start();

        }
        System.out.println("Starting lookup requests for peerID:"+peerID+"\n");
        LookupRequestGenerator lookupRequestGenerator = new LookupRequestGenerator();
        Thread lookupRequestGeneratorThread = new Thread(lookupRequestGenerator);
        lookupRequestGeneratorThread.start();

        while(running) {

            synchronized (numberOfItems) {
                if(numberOfItems <1) {
                    System.out.println("Number of available items less than 1 for peerID:"+peerID+"\n");
                    getSellDetails();
                }
            }
            synchronized (sharedRequestBuffer) {
                if (sharedRequestBuffer.size() > 0) {
                    System.out.println("Number of items in sharedRequestBuffer > 0 for peerID:"+peerID+"\n");
                    Message m = sharedRequestBuffer.poll();
                    System.out.println("Starting client threads for peerID:"+peerID+"\n");
                    for (int i = 0; i < config.getNeighborIDs().size(); i++) {
                        int port = config.getNeighborPorts().get(i);
                        Client client = new Client(port, peerID, m);
                        Thread clientThread = new Thread(client);
                        clientThread.start();
                        System.out.println("Client started for port:"+port+"\n");
                    }
                }

            }
            synchronized (sharedReplyBuffer) {
                if (sharedReplyBuffer.size() > 0) {
                    System.out.println("Number of items in sharedReplyBuffer > 0 for peerID:"+peerID+"\n");
                    Message m = sharedReplyBuffer.poll();
                    int destinationPeerId = m.messagePath.get(m.messagePath.size() - 1);
                    System.out.println("Starting client thread for peerID:"+peerID+"\n");
                    int port = config.getPortMap().get(destinationPeerId);
                    Client client = new Client(port, peerID, m);
                    Thread clientThread = new Thread(client);
                    clientThread.start();
                    System.out.println("Client started for port:"+port+"\n");


                }
            }
            synchronized (sharedTransactionBuffer)
            {
                if (sharedTransactionBuffer.size() > 0) {
                    System.out.println("Number of items in sharedTransactionBuffer > 0 for peerID:"+peerID+"\n");
                    Message m = sharedTransactionBuffer.poll();
                    int destinationPeerId = m.getDestinationSellerId();
                    System.out.println("Starting client thread for peerID:"+peerID+"\n");
                    int port = config.getPortMap().get(destinationPeerId);
                    Client client = new Client(port, peerID, m);
                    Thread clientThread = new Thread(client);
                    clientThread.start();
                    System.out.println("Client started for port:"+port+"\n");
                }
            }
        }
    }


    public static void getSellDetails() {
        Random r = new Random();
        productToSell = r.nextInt((2 - 0) + 1);
        System.out.println("ProductID to sell = "+productToSell+"\n");
        numberOfItems = 10;
    }

    public static int getBuyDetails() {

        Random r = new Random();
        int nextProductToBuy = -1;
        do {
            nextProductToBuy  = r.nextInt((2 - 0) + 1);;
        }while(nextProductToBuy == productToSell);
        productToBuy = nextProductToBuy;
        System.out.println("ProductID to buy = "+productToBuy+"\n");
        return nextProductToBuy;
    }

    public static void getConfig() {
        String json = "";
        try
        {
            json = new String(Files.readAllBytes(Paths.get(CONFIG_FILE_LOCATION+FILENAME)));
            Gson gson = new Gson();
            Type type = new TypeToken<List<Config>>(){}.getType();
            List<Config> configs = gson.fromJson(json, type);
            for(Config c : configs)
            {
                if(c.getPeerID() == peerID)
                {
                    config = c;
                    break;
                }
            }
        }
        catch(IOException ex)
        {
            System.out.println("Exception in getConfig()\n");
            System.out.println(ex.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("Exception in getConfig()\n");
            System.out.println(e.getMessage());
        }
    }

}

class LookupRequestGenerator implements Runnable {
    boolean running;
    public LookupRequestGenerator() {
        running = true;
    }

    public void stopThread() {
        running = false;
    }
    @Override
    public void run() {
        Random r = new Random();
        while(running) {
            int timeToSleep =  r.nextInt((7 - 5) + 1) + 5;
            try {
                Thread.sleep(timeToSleep*1000);
                int productId = PeerNode.getBuyDetails();
                Message newLookupRequest = new Message();

                PeerNode.requestId += 1;
                System.out.println("New request with ID:"+PeerNode.requestId+" for peerID:"+PeerNode.peerID+"\n");
                newLookupRequest.setRequestId(PeerNode.requestId);
                newLookupRequest.setHopCount(timeToSleep-3);
                newLookupRequest.setSourcePeerId(PeerNode.peerID);
                newLookupRequest.setProductId(productId);
                newLookupRequest.setType(0);
                System.out.println("Adding request with ID:"+PeerNode.requestId+" to the sharedRequestBuffer for peerID:"+PeerNode.peerID+"\n");
                synchronized (PeerNode.sharedRequestBuffer)
                {
                    PeerNode.sharedRequestBuffer.offer(newLookupRequest);
                }

            } catch (InterruptedException e) {
                System.out.println("Exception in LookupRequestGenerator.run() for peerID:"+PeerNode.peerID+"\n");
                System.out.println(e.getMessage());
                stopThread();
            }

        }
    }
}