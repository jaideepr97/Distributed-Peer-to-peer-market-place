import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utilities {

    public static void main(String[] args) {
        populateStarTopologyConfig();
    }

    public static void populateStarTopologyConfig()
    {
        ArrayList<Integer> names = new ArrayList<Integer>( Arrays.asList(5000,5001,5002) );
        HashMap<Integer, Integer> portMap = new HashMap<>();
        portMap.put(1, 5002);
        portMap.put(2, 5007);
        portMap.put(3, 5012);
        portMap.put(4, 5017);
        portMap.put(5, 5022);
        List<Config> configList = new ArrayList<>();
        // 1
        Config config = new Config(1,
                new ArrayList<Integer>(Arrays.asList(5000,5001,5002)),
                new ArrayList<Integer>(Arrays.asList(5026,5005)),
                new ArrayList<Integer>( Arrays.asList(2,5)),
                new HashMap<>(portMap));
        configList.add(config);
        // 2
        config = new Config(2,
                new ArrayList<Integer>(Arrays.asList(5005,5006,5007)),
                new ArrayList<Integer>(Arrays.asList(5001,5010)),
                new ArrayList<Integer>( Arrays.asList(1,3)),
                new HashMap<>(portMap));
        configList.add(config);
        //3
        config = new Config(3,
                new ArrayList<Integer>(Arrays.asList(5010,5011,5012)),
                new ArrayList<Integer>(Arrays.asList(5006,5015)),
                new ArrayList<Integer>( Arrays.asList(2,4)),
                new HashMap<>(portMap));
        configList.add(config);
        //4
        config = new Config(4,
                new ArrayList<Integer>(Arrays.asList(5015,5016,5017)),
                new ArrayList<Integer>(Arrays.asList(5011,5020)),
                new ArrayList<Integer>( Arrays.asList(3,5)),
                new HashMap<>(portMap));
        configList.add(config);
        //5
        config = new Config(5,
                new ArrayList<Integer>(Arrays.asList(5020,5021,5022)),
                new ArrayList<Integer>(Arrays.asList(5016,5025)),
                new ArrayList<Integer>( Arrays.asList(6,4)),
                new HashMap<>(portMap));
        configList.add(config);
        //6
        config = new Config(6,
                new ArrayList<Integer>(Arrays.asList(5025,5026,5027)),
                new ArrayList<Integer>(Arrays.asList(5021,5000)),
                new ArrayList<Integer>( Arrays.asList(1,5)),
                new HashMap<>(portMap));
        configList.add(config);
        // create a new Gson instance
        Gson gson = new Gson();
        // convert your list to json
        String jsonString = gson.toJson(configList);
        // print your generated json
        try
        {
            FileWriter f = new FileWriter("config.txt");
            f.write(jsonString);
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }


}
