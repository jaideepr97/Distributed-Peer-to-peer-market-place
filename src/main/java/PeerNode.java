import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * This is the main class, which is the starting point for the program
 */
public class PeerNode {

    private String CONFIG_FILE_LOCATION = "";
    private String FILENAME = "";
    public HashMap<Integer, String> productMap = new HashMap<>();
    private Config config;
    private int peerID;
    private int requestId;
    private int productToSell;
    private int productToBuy;
    private int role;
    public static AtomicInteger numberOfItems;
    public static ConcurrentLinkedQueue<Message> sharedRequestBuffer = new ConcurrentLinkedQueue<>();
    public static ConcurrentHashMap<Message, Integer> requestHistory = new ConcurrentHashMap();
    public static ConcurrentLinkedQueue<Message> sharedReplyBuffer = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Message> sharedTransactionBuffer = new ConcurrentLinkedQueue<>();
    public static ConcurrentHashMap<Integer, Integer> servicedRequests = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Integer, ResponseTimeTracker> ResponseTimeMap = new ConcurrentHashMap<>();


    public int getPeerID() {
        return peerID;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getProductToSell() {
        return productToSell;
    }

    public static void main(String[] args)  {

        try {
            PeerNode peer = new PeerNode();
            peer.peerID = Integer.parseInt(args[0]);
            numberOfItems = new AtomicInteger();
            peer.FILENAME = args[1];
            peer.setOutputFile();
            System.out.println("---------Starting peer with ID: "+ peer.peerID+"----------\n");
            peer.requestId = 0;
            peer.productMap.put(0, "Boar");
            peer.productMap.put(1, "Fish");
            peer.productMap.put(2, "Salt");
            System.out.println("Getting Config for peerID:"+peer.peerID+"\n");
            peer.getConfig();
            if(peer.config == null)
            {
                System.out.println("Error: Config file not found!! for peerID:"+peer.peerID+"\n");
                return;
            }
            System.out.println("Getting role details for peerID:"+peer.peerID+"\n");
            peer.setRole();
            LookupRequestGenerator lookupRequestGenerator;
            Thread lookupRequestGeneratorThread;
            List<Thread> serverThreads = new ArrayList<>();

            switch (peer.role)
            {
                case 0:
                    System.out.println("The peer with peerID:"+peer.peerID+" is a buyer!\n");
                    //Starting server threads for the peer
                    for (int i=0; i<peer.config.getServerPorts().size(); i++)
                    {
                        Server server = new Server(peer.config.getServerPorts().get(i), peer, -1);
                        Thread serverThread = new Thread(server);
                        serverThread.start();
                        serverThreads.add(serverThread);
                    }
                    //Starting lookup requests for the peer
                    lookupRequestGenerator = new LookupRequestGenerator(peer);
                    lookupRequestGeneratorThread = new Thread(lookupRequestGenerator);
                    lookupRequestGeneratorThread.start();
                    break;
                case 1:
                    System.out.println("The peer with peerID:"+peer.peerID+" is a seller!\n");
                    //Setting Seller details for the peer
                    peer.getSellDetails();
                    //Starting server threads for the peer
                    for (int i=0; i<peer.config.getServerPorts().size(); i++)
                    {
                        Server server = new Server(peer.config.getServerPorts().get(i), peer, peer.productToSell);
                        Thread serverThread = new Thread(server);
                        serverThread.start();
                        serverThreads.add(serverThread);
                    }
                    break;
                case 2:
                    System.out.println("The peer with peerID:"+peer.peerID+" is a buyer and seller!\n");
                    //Setting Seller details for the peer
                    peer.getSellDetails();
                    //Starting server threads for the peer
                    for (int i=0; i<peer.config.getServerPorts().size(); i++)
                    {
                        Server server = new Server(peer.config.getServerPorts().get(i), peer, peer.productToSell);
                        Thread serverThread = new Thread(server);
                        serverThread.start();
                        serverThreads.add(serverThread);
                    }
                    //Starting lookup requests for the peer
                    lookupRequestGenerator = new LookupRequestGenerator(peer);
                    lookupRequestGeneratorThread = new Thread(lookupRequestGenerator);
                    lookupRequestGeneratorThread.start();
            }
            long start = System.currentTimeMillis();
            long end = start + 120*1000; // 60 seconds * 1000 ms/sec
            while(System.currentTimeMillis() < end) {
                synchronized (numberOfItems) {
                    if((peer.role == 1 || peer.role == 2) && numberOfItems.get() <1) {
                        //Number of available items less than 1 for the peer
                        peer.getSellDetails();
                    }
                }
                synchronized (sharedRequestBuffer) {
                    if (sharedRequestBuffer.size() > 0) {
                        //Number of items in sharedRequestBuffer > 0 for the peer
                        Message m = sharedRequestBuffer.poll();
                        m.setHopCount(m.getHopCount()-1);
                        int lastNeighbourID = -1;
                        if(m.getMessagePath().size() > 0)
                        {
                            lastNeighbourID = m.getMessagePath().get(m.getMessagePath().size()-1);
                        }
                        m.messagePath.add(peer.peerID);
                        //Starting client threads for the peer
                        for (int i = 0; i <peer. config.getNeighborIDs().size(); i++) {
                            int port = peer.config.getNeighborPorts().get(i);
                            if(lastNeighbourID == -1 || port != peer.config.getPortMap().get(lastNeighbourID)) {
                                String host = peer.getHostName(port);
                                Client client = new Client(host, port, peer.peerID, m, -1);
                                Thread clientThread = new Thread(client);
                                clientThread.start();

                            }
                        }
                    }

                }
                synchronized (sharedReplyBuffer) {
                    if (sharedReplyBuffer.size() > 0) {
                       // Number of items in sharedReplyBuffer > 0 for the peer
                        Message m = sharedReplyBuffer.poll();
                        int destinationPeerId = m.messagePath.get(m.messagePath.size() - 1);
                        m.messagePath.remove(m.messagePath.size() -1);
                        // Starting client thread for the peer
                        int port = peer.config.getPortMap().get(destinationPeerId);
                        String host = peer.getHostName(port);
                        Client client = new Client(host, port, peer.peerID, m, destinationPeerId);
                        Thread clientThread = new Thread(client);
                        clientThread.start();


                    }
                }
                synchronized (sharedTransactionBuffer)
                {
                    if (sharedTransactionBuffer.size() > 0) {
                        // Number of items in sharedTransactionBuffer > 0 for the peer
                        Message m = sharedTransactionBuffer.poll();
                        int destinationPeerId = m.getDestinationSellerId();
                        System.out.println("\n-------------Starting buy process for buyer:"
                                +peer.peerID+" and seller:"+destinationPeerId+" for product:"
                                +peer.productMap.getOrDefault(m.getProductId(), "")+" -------------\n");
                        int port = peer.config.getPortMap().get(destinationPeerId);
                        String host = peer.getHostName(port);
                        synchronized (servicedRequests)
                        {
                            if(!servicedRequests.containsKey(m.getRequestId()))
                            {
                                Client client = new Client(host, port, peer.peerID, m, -1);
                                Thread clientThread = new Thread(client);
                                clientThread.start();


                            }
                        }
                    }
                }
                Thread.sleep(1);
            }
            System.out.println("Main Thread stopped");
            for (Thread t : serverThreads)
            {
                t.interrupt();
            }

            long averageRequestResponseTime =  0L;

            synchronized (ResponseTimeMap) {
                if(ResponseTimeMap.size() > 0) {
                    System.out.println("RequestId\t\t\t\tAverage Response Time");
                    for(Map.Entry<Integer, ResponseTimeTracker> entry: ResponseTimeMap.entrySet()) {
                        long totalResponseTime = 0L;
                        for(Long t: entry.getValue().responseTimes) {
                            totalResponseTime += t-entry.getValue().getCreationTime();
                        }
                        if(entry.getValue().responseTimes.size() > 0)
                            totalResponseTime = totalResponseTime/(entry.getValue().responseTimes.size());
                        averageRequestResponseTime += totalResponseTime;
                        System.out.println(entry.getKey()+"\t\t\t\t" + totalResponseTime);
                    }
                    averageRequestResponseTime = averageRequestResponseTime/ResponseTimeMap.size();
                    System.out.println("Average response time across all lookup requests: " + averageRequestResponseTime);
                }
            }


            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method gets the host name from the config file
     * @param port - The port of the socket
     * @return - The hostname
     */
    public String getHostName(int port)
    {
        String host = "";
//        if(this.config.getLocation() ==  this.config.getLocationMap().get(port))
//        {
//            host = "localhost";
//        }
        if(this.config.getLocationMap().get(port) == 1)
        {
            host = "elnux7.cs.umass.edu";
        }
        else if(this.config.getLocationMap().get(port) == 2)
        {
            host = "elnux1.cs.umass.edu";
        }
        else if(this.config.getLocationMap().get(port) == 3)
        {
            host = "elnux2.cs.umass.edu";
        }
        return host;
    }

    public int getRole() {
        return role;
    }

    /**
     * This method gets the role of the peer randomly
     */
    public void setRole()
    {
        Random r = new Random();
        this.role = r.nextInt((2-0)+1);
    }

    /**
     * This method gets the product to sell randomly
     */
    public void getSellDetails() {
        Random r = new Random();
        this.productToSell = r.nextInt((2 - 0) + 1);
        System.out.println("\n############### Product to sell = "+
                this.productMap.getOrDefault(this.productToSell, "")+" ###############\n");
        synchronized (numberOfItems)
        {
            numberOfItems.set(10);
        }
    }

    /**
     * This method gets the product to buy randomly
     * @return - the product to buy
     */
    public int getBuyDetails() {

        Random r = new Random();
        int nextProductToBuy;
        do {
            nextProductToBuy  = r.nextInt((2 - 0) + 1);;
        }while(nextProductToBuy == this.productToSell);
        this.productToBuy = nextProductToBuy;
        return nextProductToBuy;
    }


    /**
     * This method sets the system output to a file
     */
    public void setOutputFile()
    {
        try {
            File file = new File(this.peerID+"_"+this.FILENAME+"_output.txt");
            PrintStream stream = new PrintStream(file);
            System.setOut(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method gets the config file for the peer
     */
    public void getConfig() {
        String json = "";
        try
        {
            json = new String(Files.readAllBytes(Paths.get(this.CONFIG_FILE_LOCATION+this.FILENAME)));
            Gson gson = new Gson();
            Type type = new TypeToken<List<Config>>(){}.getType();
            List<Config> configs = gson.fromJson(json, type);
            for(Config c : configs)
            {
                if(c.getPeerID() == this.peerID)
                {
                    this.config = c;
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

/**
 * This class generates new requests after every second if the peer is a buyer
 */
class LookupRequestGenerator implements Runnable {
    boolean running;
    PeerNode peer;
    public LookupRequestGenerator(PeerNode _peer) {
        peer = _peer;
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
                int productId = peer.getBuyDetails();
                Message newLookupRequest = new Message();
                counter ++;
                peer.setRequestId(peer.getRequestId() +1);
                //New request with created
                newLookupRequest.setRequestId(peer.getRequestId());
                newLookupRequest.setHopCount(5);
                newLookupRequest.setSourcePeerId(peer.getPeerID());
                newLookupRequest.setProductId(productId);
                newLookupRequest.setType(0);
                newLookupRequest.setProductName(peer.productMap.get(productId));
                //Adding request  to the sharedRequestBuffer for the peer
                System.out.println("\n############## Message created for product request:"+newLookupRequest.getProductName()+
                        " ##############\n");
                synchronized (PeerNode.ResponseTimeMap) {
                    Calendar c1 = Calendar.getInstance();
                    Date dateOne = c1.getTime();
                    ResponseTimeTracker timeTracker = new ResponseTimeTracker();
                    timeTracker.setCreationTime(dateOne.getTime());
                    PeerNode.ResponseTimeMap.put(peer.getRequestId(), timeTracker);

                }

                synchronized (PeerNode.sharedRequestBuffer)
                {
                    PeerNode.sharedRequestBuffer.offer(newLookupRequest);
                }
                synchronized (PeerNode.requestHistory)
                {
                    PeerNode.requestHistory.put(newLookupRequest, 0);
                }

            } catch (InterruptedException e) {
                System.out.println("Exception in LookupRequestGenerator.run() for peerID:"+peer.getPeerID()+"\n");
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
