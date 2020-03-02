import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utilities {

    public static void main(String[] args) {
        //populateStarTopologyConfig();
        populateTestTopologyConfig();
//        test();
//        pathTest();
    }

    public static void pathTest()
    {
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath);
        filePath.concat("path to the property file");

    }
    public static void populateStarTopologyConfig()
    {
        HashMap<Integer, Integer> portMap = new HashMap<>();
        portMap.put(1, 5002);
        portMap.put(2, 5007);
        portMap.put(3, 5012);
        portMap.put(4, 5017);
        portMap.put(5, 5022);
        portMap.put(6, 5027);
        HashMap<Integer, Integer> locationMap = new HashMap<>();
        locationMap.put(5000, 1);
        locationMap.put(5001, 1);
        locationMap.put(5002, 1);
        locationMap.put(5005, 1);
        locationMap.put(5006, 1);
        locationMap.put(5007, 1);
        locationMap.put(5010, 1);
        locationMap.put(5011, 1);
        locationMap.put(5012, 1);
        locationMap.put(5016, 1);
        locationMap.put(5017, 1);
        locationMap.put(5015, 1);
        locationMap.put(5020, 1);
        locationMap.put(5021, 1);
        locationMap.put(5022, 1);
        locationMap.put(5025, 1);
        locationMap.put(5026, 1);
        locationMap.put(5027, 1);
        List<Config> configList = new ArrayList<>();
        // 1
        Config config = new Config(1,
                new ArrayList<Integer>(Arrays.asList(5000,5001,5002)),
                new ArrayList<Integer>(Arrays.asList(5026,5005)),
                new ArrayList<Integer>( Arrays.asList(2,5)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        // 2
        config = new Config(2,
                new ArrayList<Integer>(Arrays.asList(5005,5006,5007)),
                new ArrayList<Integer>(Arrays.asList(5001,5010)),
                new ArrayList<Integer>( Arrays.asList(1,3)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        //3
        config = new Config(3,
                new ArrayList<Integer>(Arrays.asList(5010,5011,5012)),
                new ArrayList<Integer>(Arrays.asList(5006,5015)),
                new ArrayList<Integer>( Arrays.asList(2,4)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        //4
        config = new Config(4,
                new ArrayList<Integer>(Arrays.asList(5015,5016,5017)),
                new ArrayList<Integer>(Arrays.asList(5011,5020)),
                new ArrayList<Integer>( Arrays.asList(3,5)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        //5
        config = new Config(5,
                new ArrayList<Integer>(Arrays.asList(5020,5021,5022)),
                new ArrayList<Integer>(Arrays.asList(5016,5025)),
                new ArrayList<Integer>( Arrays.asList(6,4)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        //6
        config = new Config(6,
                new ArrayList<Integer>(Arrays.asList(5025,5026,5027)),
                new ArrayList<Integer>(Arrays.asList(5021,5000)),
                new ArrayList<Integer>( Arrays.asList(1,5)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        // create a new Gson instance
        Gson gson = new Gson();
        // convert your list to json
        String jsonString = gson.toJson(configList);
        // print your generated json
        try
        {
            FileWriter f = new FileWriter("StarTopologySixNodesConfig.txt");
            f.write(jsonString);
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void populateTestTopologyConfig() {

        // Four Nodes
        HashMap<Integer, Integer> portMap = new HashMap<>();
        portMap.put(1, 5001);
        portMap.put(2, 5005);
        portMap.put(3, 5008);
        portMap.put(4, 5011);
        HashMap<Integer, Integer> locationMap = new HashMap<>();
        locationMap.put(5000, 0);
        locationMap.put(5001, 0);
        locationMap.put(5002, 0);
        locationMap.put(5003, 0);
	    locationMap.put(5004, 0);
        locationMap.put(5005, 0);
        locationMap.put(5006, 1);
        locationMap.put(5007, 1);
	    locationMap.put(5008, 1);
        locationMap.put(5009, 0);
        locationMap.put(5010, 0);
        locationMap.put(5011, 0);
        List<Config> configList = new ArrayList<>();
        // 1
        Config config = new Config(1,
                new ArrayList<Integer>(Arrays.asList(5000,5001)),
                new ArrayList<Integer>(Arrays.asList(5002)),
                new ArrayList<Integer>( Arrays.asList(2)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        // 2
        config = new Config(2,
                new ArrayList<Integer>(Arrays.asList(5002,5003,5004, 5005)),
                new ArrayList<Integer>(Arrays.asList(5000,5006, 5009)),
                new ArrayList<Integer>( Arrays.asList(1,3,4)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        //3
        config = new Config(3,
                new ArrayList<Integer>(Arrays.asList(5006,5007,5008)),
                new ArrayList<Integer>(Arrays.asList(5003,5010)),
                new ArrayList<Integer>( Arrays.asList(2,4)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        //4
        config = new Config(4,
                new ArrayList<Integer>(Arrays.asList(5009,5010,5011)),
                new ArrayList<Integer>(Arrays.asList(5007,5004)),
                new ArrayList<Integer>( Arrays.asList(2,3)),
                new HashMap<>(portMap),
                new HashMap<>(locationMap));
        configList.add(config);
        //5

        // create a new Gson instance
        Gson gson = new Gson();
        // convert your list to json
        String jsonString = gson.toJson(configList);
        // print your generated json
        try
        {
            FileWriter f = new FileWriter("TestTopologyFourNodesConfig.txt");
            f.write(jsonString);
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        //Two Nodes
        /*
        HashMap<Integer, Integer> portMap = new HashMap<>();
        portMap.put(1, 5000);
        portMap.put(2, 5001);

        List<Config> configList = new ArrayList<>();
        // 1
        Config config = new Config(1,
                new ArrayList<Integer>(Arrays.asList(5000)),
                new ArrayList<Integer>(Arrays.asList(5001)),
                new ArrayList<Integer>( Arrays.asList(1)),
                new HashMap<>(portMap));
        configList.add(config);
        // 2
        config = new Config(2,
                new ArrayList<Integer>(Arrays.asList(5001)),
                new ArrayList<Integer>(Arrays.asList(5000)),
                new ArrayList<Integer>( Arrays.asList(2)),
                new HashMap<>(portMap));
        configList.add(config);

        // create a new Gson instance
        Gson gson = new Gson();
        // convert your list to json
        String jsonString = gson.toJson(configList);
        // print your generated json
        try
        {
            FileWriter f = new FileWriter("TestTopologyTwoNodesConfig.txt");
            f.write(jsonString);
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

         */

    }
    public static void test()
    {
        Message m = new Message();
        m.setSourcePeerId(1);
        m.setRequestId(1);
        Message n = new Message();
        n.setRequestId(1);
        n.setSourcePeerId(1);
        System.out.println(m.equals(n));
        n.setSourcePeerId(2);
        System.out.println(m.equals(n));
    }


}
