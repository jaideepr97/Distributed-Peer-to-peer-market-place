
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PeerNode implements P2PInterface
//public class PeerNode
{
//    private static final String JAVA_FILE_LOCATION = "/Users/aayushgupta/IdeaProjects/lab-1-rao-gupta/src/main/java/";
    private static final String CONFIG_FILE_LOCATION = "/Users/aayushgupta/IdeaProjects/lab-1-rao-gupta/config.txt";
    private static Config config;
    static int peerPort;
    static int neighbourPort;
    static int peerID;
    static int productToSell;
    static int numberOfItems;
    static int productToBuy;

    public static ConcurrentHashMap<Message, Integer> sharedRequestBuffer = new ConcurrentHashMap();
    public static ConcurrentHashMap<Message, Integer> requestHistory = new ConcurrentHashMap();

    public static void main(String[] args)  {

        peerID = Integer.parseInt(args[0]);
        getConfig();
        System.out.println("done");
        getSellDetails();
        getBuyDetails();

        if(config == null)
        {
            System.out.println("Config file not found!!");
            return;
        }
        for (int i=0; i<config.getServerPorts().size(); i++)
        {
            Server server = new Server(config.getServerPorts().get(i), peerID, productToSell, numberOfItems);
            Thread serverThread = new Thread(server);
            serverThread.start();

        }
        for(int i=0; i<config.getNeighborIDs().size(); i++)
        {
            Client client = new Client(config.getNeighborPorts().get(i), peerID);
            Thread clientThread = new Thread(client);
            clientThread.start();

        }
    }
    public static void getSellDetails()
    {
        productToSell = (int)Math.random()*3;
        numberOfItems = 10;
    }
    public static void getBuyDetails()
    {
        do {
           productToBuy  = (int)Math.random()*3;
        }while(productToBuy == productToSell);
    }

    public static void getConfig()
    {
        String json = "";
        try
        {
            json = new String(Files.readAllBytes(Paths.get(CONFIG_FILE_LOCATION)));
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
            System.out.println(ex.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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
