
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
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class PeerNode {
    //private static final String CONFIG_FILE_LOCATION = "/Users/aayushgupta/IdeaProjects/lab-1-rao-gupta/";
    //private static final String CONFIG_FILE_LOCATION = "/home/hadoopuser/Desktop/lab-1-rao-gupta/";
    private static String CONFIG_FILE_LOCATION = "";
    private static String FILENAME = "FourNodes.txt";

    public static HashMap<Integer, String> productMap = new HashMap<>();
    private static Config config;
    static int peerID;
    static int requestId;
    static int productToSell;
    public static Integer numberOfItems = 0;
    static int productToBuy;
    static boolean running = true;
    static int role;
    public static ConcurrentLinkedQueue<Message> sharedRequestBuffer = new ConcurrentLinkedQueue<>();
    public static ConcurrentHashMap<Message, Integer> requestHistory = new ConcurrentHashMap();
    public static ConcurrentLinkedQueue<Message> sharedReplyBuffer = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Message> sharedTransactionBuffer = new ConcurrentLinkedQueue<>();
    public static ConcurrentHashMap<Integer, Integer> servicedRequests = new ConcurrentHashMap<>();

    public static void main(String[] args)  {

        try {
            peerID = Integer.parseInt(args[0]);
            FILENAME = args[1];
            setOutputFile();
            System.out.println("---------Starting peer with ID: "+ peerID+"----------\n");
            requestId = 0;
            productMap.put(0, "Boar");
            productMap.put(1, "Fish");
            productMap.put(2, "Salt");
            System.out.println("Getting Config for peerID:"+peerID+"\n");
            getConfig();
            if(config == null)
            {
                System.out.println("Error: Config file not found!! for peerID:"+peerID+"\n");
                return;
            }
            System.out.println("Getting role details for peerID:"+peerID+"\n");
            getRole();
            LookupRequestGenerator lookupRequestGenerator;
            Thread lookupRequestGeneratorThread;
            List<Thread> serverThreads = new ArrayList<>();

            switch (role)
            {
                case 0:
                    System.out.println("The peer with peerID:"+peerID+" is a buyer!\n");
                    //System.out.println("Starting server threads for peerID:"+peerID+"\n");
                    for (int i=0; i<config.getServerPorts().size(); i++)
                    {
                        Server server = new Server(config.getServerPorts().get(i), peerID, -1, -1);
                        Thread serverThread = new Thread(server);
                        serverThread.start();
                        serverThreads.add(serverThread);
                    }
                    //System.out.println("Starting lookup requests for peerID:"+peerID+"\n");
                    lookupRequestGenerator = new LookupRequestGenerator();
                    lookupRequestGeneratorThread = new Thread(lookupRequestGenerator);
                    lookupRequestGeneratorThread.start();
                    break;
                case 1:
                    System.out.println("The peer with peerID:"+peerID+" is a seller!\n");
                    //System.out.println("Setting Seller details for peerID:"+peerID+"\n");
                    getSellDetails();
                    //System.out.println("Starting server threads for peerID:"+peerID+"\n");
                    for (int i=0; i<config.getServerPorts().size(); i++)
                    {
                        Server server = new Server(config.getServerPorts().get(i), peerID, productToSell, numberOfItems);
                        Thread serverThread = new Thread(server);
                        serverThread.start();
                        serverThreads.add(serverThread);
                    }
                    break;
                case 2:
                    System.out.println("The peer with peerID:"+peerID+" is a buyer and seller!\n");
                    //System.out.println("Setting Seller details for peerID:"+peerID+"\n");
                    getSellDetails();
                    //System.out.println("Starting server threads for peerID:"+peerID+"\n");
                    for (int i=0; i<config.getServerPorts().size(); i++)
                    {
                        Server server = new Server(config.getServerPorts().get(i), peerID, productToSell, numberOfItems);
                        Thread serverThread = new Thread(server);
                        serverThread.start();
                        serverThreads.add(serverThread);
                    }
                    //System.out.println("Starting lookup requests for peerID:"+peerID+"\n");
                    lookupRequestGenerator = new LookupRequestGenerator();
                    lookupRequestGeneratorThread = new Thread(lookupRequestGenerator);
                    lookupRequestGeneratorThread.start();
            }
            long start = System.currentTimeMillis();
            long end = start + 120*1000; // 60 seconds * 1000 ms/sec
            while(System.currentTimeMillis() < end) {
                synchronized (numberOfItems) {
                    if((role == 1 || role == 2) && numberOfItems <1) {
                        //System.out.println("Number of available items less than 1 for peerID:"+peerID+"\n");
                        getSellDetails();
                    }
                }
                synchronized (sharedRequestBuffer) {
                    if (sharedRequestBuffer.size() > 0) {
                        //System.out.println("Number of items in sharedRequestBuffer > 0 for peerID:"+peerID+"\n");
                        Message m = sharedRequestBuffer.poll();
                        m.setHopCount(m.getHopCount()-1);
                        int lastNeighbourID = -1;
                        if(m.getMessagePath().size() > 0)
                        {
                            lastNeighbourID = m.getMessagePath().get(m.getMessagePath().size()-1);
                        }
                        m.messagePath.add(peerID);
                        //System.out.println("Starting client threads for peerID:"+peerID+"\n");
                        for (int i = 0; i < config.getNeighborIDs().size(); i++) {
                            int port = config.getNeighborPorts().get(i);
                            if(lastNeighbourID == -1 || port != config.getPortMap().get(lastNeighbourID)) {
                                String host = getHostName(port);
                                Client client = new Client(host, port, peerID, m, -1);
                                Thread clientThread = new Thread(client);
                                clientThread.start();
                            }
                        }
                    }

                }
                synchronized (sharedReplyBuffer) {
                    if (sharedReplyBuffer.size() > 0) {
                       // System.out.println("Number of items in sharedReplyBuffer > 0 for peerID:"+peerID+"\n");
                        Message m = sharedReplyBuffer.poll();
                        int destinationPeerId = m.messagePath.get(m.messagePath.size() - 1);
                        m.messagePath.remove(m.messagePath.size() -1);
                        //System.out.println("Starting client thread for peerID:"+peerID+"\n");
                        int port = config.getPortMap().get(destinationPeerId);
                        //System.out.println("\n--------"+port+"---------\n");
                        String host = getHostName(port);
                        Client client = new Client(host, port, peerID, m, destinationPeerId);
                        Thread clientThread = new Thread(client);
                        clientThread.start();
                    }
                }
                synchronized (sharedTransactionBuffer)
                {
                    if (sharedTransactionBuffer.size() > 0) {
                        //System.out.println("Number of items in sharedTransactionBuffer > 0 for peerID:"+peerID+"\n");
                        Message m = sharedTransactionBuffer.poll();
                        int destinationPeerId = m.getDestinationSellerId();
                        System.out.println("-------------Starting buy process for buyer:"
                                +peerID+" and seller:"+destinationPeerId+" for product:"
                                +productMap.getOrDefault(m.getProductId(), "")+" -------------\n");
                        int port = config.getPortMap().get(destinationPeerId);
                        String host = getHostName(port);
                        synchronized (servicedRequests)
                        {
                            if(!servicedRequests.containsKey(m.getRequestId()))
                            {
                                Client client = new Client(host, port, peerID, m, -1);
                                Thread clientThread = new Thread(client);
                                clientThread.start();
                            }
//                            else
//                            {
//                                System.out.println("Peer:"+peerID+", Buy request already serviced!\n");
//                            }
                        }
                    }
                }
            }
            System.out.println("Main Thread stopped");
            for (Thread t : serverThreads)
            {
                t.interrupt();
            }
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getHostName(int port)
    {
        String host = "";
        if(config.getLocation() == 0 && config.getLocationMap().get(port) == 0)
        {
            host = "localhost";
        }
        else if((config.getLocation() == 0 && config.getLocationMap().get(port) == 1) ||
                (config.getLocation() == 1 && config.getLocationMap().get(port) == 1))
        {
            host = "elnux7.cs.umass.edu";
        }
        else if(config.getLocation() == 1 && config.getLocationMap().get(port) == 0)
        {
            host = "172.30.132.77";
        }
        return host;
    }

    public static void getRole()
    {
        Random r = new Random();
        role = r.nextInt((2-0)+1);
    }


    public static void getSellDetails() {
        Random r = new Random();
        productToSell = r.nextInt((2 - 0) + 1);
        System.out.println("\n############### Product to sell = "+productMap.getOrDefault(productToSell, "")+" ###############\n");
        numberOfItems = 10;
    }

    public static int getBuyDetails() {

        Random r = new Random();
        int nextProductToBuy;
        do {
            nextProductToBuy  = r.nextInt((2 - 0) + 1);;
        }while(nextProductToBuy == productToSell);
        productToBuy = nextProductToBuy;
        //System.out.println("Product to buy = "+productMap.getOrDefault(productToBuy, "")+"\n");
        return nextProductToBuy;
    }

    public static void setOutputFile()
    {
        try {
            File file = new File(peerID+"_"+FILENAME+"_output.txt");
            PrintStream stream = new PrintStream(file);
            System.setOut(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        int counter = 0;
        while(running) {
            int timeToSleep =  r.nextInt((7 - 5) + 1) + 5;
            try {
                Thread.sleep(timeToSleep*1000);
                int productId = PeerNode.getBuyDetails();
                Message newLookupRequest = new Message();
                counter ++;
                PeerNode.requestId += 1;
                //System.out.println("New request with ID:"+PeerNode.requestId+" for peerID:"+PeerNode.peerID+"\n");
                newLookupRequest.setRequestId(PeerNode.requestId);
                newLookupRequest.setHopCount(5);
                newLookupRequest.setSourcePeerId(PeerNode.peerID);
                newLookupRequest.setProductId(productId);
                newLookupRequest.setType(0);
                newLookupRequest.setProductName(PeerNode.productMap.get(productId));
                //System.out.println("Adding request with ID:"+PeerNode.requestId+" to the sharedRequestBuffer for peerID:"+PeerNode.peerID+"\n");
                System.out.println("\n############## Message created for product request:"+newLookupRequest.getProductName()+
                        " ##############\n");
                synchronized (PeerNode.sharedRequestBuffer)
                {
                    PeerNode.sharedRequestBuffer.offer(newLookupRequest);
                }
                synchronized (PeerNode.requestHistory)
                {
                    PeerNode.requestHistory.put(newLookupRequest, 0);
                }

            } catch (InterruptedException e) {
                System.out.println("Exception in LookupRequestGenerator.run() for peerID:"+PeerNode.peerID+"\n");
                e.printStackTrace();
                stopThread();
            }
            if(counter == 5) {
                System.out.println("\nRequest counter over\n");
                stopThread();
            }


        }
    }
}
