import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to create configurations for the different test cases
 */
public class Utilities {

    public static void main(String[] args) {
        populateTestTopologyConfig(Integer.parseInt(args[0]));
    }

    public static void populateTestTopologyConfig(int peers) {
        List<Config> configList = new ArrayList<>();
        HashMap<Integer, Integer> portMap;
        HashMap<Integer, Integer> locationMap;
        Config config;
        String fileName = "";
        switch (peers)
        {
            /* TEST CASE 1: 6 PEERS - TESTING LOCALLY */
            case 1:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                locationMap = new HashMap<>();
                locationMap.put(5000,0);
                locationMap.put(5001,0);
                locationMap.put(5002,0);
                locationMap.put(5003,0);
                locationMap.put(5004,0);
                locationMap.put(5005,0);
                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001,5005)),
                        new ArrayList<Integer>( Arrays.asList(2,6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5000,5002)),
                        new ArrayList<Integer>( Arrays.asList(1,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5001,5003)),
                        new ArrayList<Integer>( Arrays.asList(2,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5002,5004)),
                        new ArrayList<Integer>( Arrays.asList(3,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5003,5005)),
                        new ArrayList<Integer>( Arrays.asList(6,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5000,5004)),
                        new ArrayList<Integer>( Arrays.asList(1,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "Test_1_local";
                break;




                /* TEST CASE 1: 6 PEERS - TESTING ON EDLAB */
            case 2:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                locationMap = new HashMap<>();
                locationMap.put(5000,1);
                locationMap.put(5001,1);
                locationMap.put(5002,2);
                locationMap.put(5003,2);
                locationMap.put(5004,3);
                locationMap.put(5005,3);
                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001,5005)),
                        new ArrayList<Integer>( Arrays.asList(2,6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                // 2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5000,5002)),
                        new ArrayList<Integer>( Arrays.asList(1,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5001,5003)),
                        new ArrayList<Integer>( Arrays.asList(2,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        2);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5002,5004)),
                        new ArrayList<Integer>( Arrays.asList(3,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        2);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5003,5005)),
                        new ArrayList<Integer>( Arrays.asList(6,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        3);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5000,5004)),
                        new ArrayList<Integer>( Arrays.asList(1,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        3);
                configList.add(config);
                fileName = "Test_1_EDLAB";
                break;




            /* TEST CASE 2: 7 PEERS - TESTING LOCALLY */
            case 3:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                portMap.put(7, 5006);
                locationMap = new HashMap<>();
                locationMap.put(5000,0);
                locationMap.put(5001,0);
                locationMap.put(5002,0);
                locationMap.put(5003,0);
                locationMap.put(5004,0);
                locationMap.put(5005,0);
                locationMap.put(5006,0);
                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001,5002,5003,5004,5005,5006)),
                        new ArrayList<Integer>( Arrays.asList(2,3,4,5,6,7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //7
                config = new Config(7,
                        5006,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "Test_2_local";
                break;




            /* TEST CASE 2: 7 PEERS - TESTING ON EDLAB */
            case 4:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                portMap.put(7, 5006);
                locationMap = new HashMap<>();
                locationMap.put(5000,1);
                locationMap.put(5001,2);
                locationMap.put(5002,2);
                locationMap.put(5003,3);
                locationMap.put(5004,3);
                locationMap.put(5005,7);
                locationMap.put(5006,7);
                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001,5002,5003,5004,5005,5006)),
                        new ArrayList<Integer>( Arrays.asList(2,3,4,5,6,7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //7
                config = new Config(7,
                        5006,
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "Test_2_EDLAB";
                break;




            /* TEST CASE 3: 8 PEERS - TESTING LOCALLY */
            case 5:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                portMap.put(7, 5006);
                portMap.put(8, 5007);
                locationMap = new HashMap<>();
                locationMap.put(5000,0);
                locationMap.put(5001,0);
                locationMap.put(5002,0);
                locationMap.put(5003,0);
                locationMap.put(5004,0);
                locationMap.put(5005,0);
                locationMap.put(5006,0);
                locationMap.put(5007,0);
                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5000,5002,5003)),
                        new ArrayList<Integer>( Arrays.asList(1,3,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5001,5003)),
                        new ArrayList<Integer>( Arrays.asList(2,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5001,5002,5004)),
                        new ArrayList<Integer>( Arrays.asList(2,3,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5003,5005,5006)),
                        new ArrayList<Integer>( Arrays.asList(4,6,7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //7
                config = new Config(7,
                        5006,
                        new ArrayList<Integer>(Arrays.asList(5004,5007)),
                        new ArrayList<Integer>( Arrays.asList(5,8)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //8
                config = new Config(8,
                        5007,
                        new ArrayList<Integer>(Arrays.asList(5006)),
                        new ArrayList<Integer>( Arrays.asList(7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "Test_3_local";
                break;





            /* TEST CASE 3: 8 PEERS - TESTING ON EDLAB */
            case 6:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                portMap.put(7, 5006);
                portMap.put(8, 5007);
                locationMap = new HashMap<>();
                locationMap.put(5000,1);
                locationMap.put(5001,1);
                locationMap.put(5002,2);
                locationMap.put(5003,2);
                locationMap.put(5004,3);
                locationMap.put(5005,3);
                locationMap.put(5006,7);
                locationMap.put(5007,7);
                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                // 2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5000,5002,5003)),
                        new ArrayList<Integer>( Arrays.asList(1,3,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5001,5003)),
                        new ArrayList<Integer>( Arrays.asList(2,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        2);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5001,5002,5004)),
                        new ArrayList<Integer>( Arrays.asList(2,3,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        2);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5003,5005,5006)),
                        new ArrayList<Integer>( Arrays.asList(4,6,7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        3);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        3);
                configList.add(config);
                //7
                config = new Config(7,
                        5006,
                        new ArrayList<Integer>(Arrays.asList(5004,5007)),
                        new ArrayList<Integer>( Arrays.asList(5,8)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        7);
                configList.add(config);
                //8
                config = new Config(8,
                        5007,
                        new ArrayList<Integer>(Arrays.asList(5006)),
                        new ArrayList<Integer>( Arrays.asList(7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        7);
                configList.add(config);
                fileName = "Test_3_EDLAB";
                break;



            /* TEST CASE 4: 7 PEERS - TESTING ON EDLAB */
            case 7:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                portMap.put(7, 5006);

                locationMap = new HashMap<>();
                locationMap.put(5000,1);
                locationMap.put(5001,1);
                locationMap.put(5002,2);
                locationMap.put(5003,2);
                locationMap.put(5004,3);
                locationMap.put(5005,3);
                locationMap.put(5006,7);

                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                // 2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5000, 5002)),
                        new ArrayList<Integer>( Arrays.asList(1,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5001, 5003)),
                        new ArrayList<Integer>( Arrays.asList(2,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        2);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5002,5004)),
                        new ArrayList<Integer>( Arrays.asList(3,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        2);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5003,5005)),
                        new ArrayList<Integer>( Arrays.asList(4,6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        3);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5004,5006)),
                        new ArrayList<Integer>( Arrays.asList(5,7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        3);
                configList.add(config);
                //7
                config = new Config(7,
                        5006,
                        new ArrayList<Integer>(Arrays.asList(5005)),
                        new ArrayList<Integer>( Arrays.asList(6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        7);
                configList.add(config);

                fileName = "Test_4_EDLAB";
                break;




            /* TEST CASE 5: 8 PEERS - TESTING ON EDLAB */
            case 8:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                portMap.put(7, 5006);
                portMap.put(8, 5007);
                locationMap = new HashMap<>();
                locationMap.put(5000,1);
                locationMap.put(5001,1);
                locationMap.put(5002,2);
                locationMap.put(5003,3);
                locationMap.put(5004,3);
                locationMap.put(5005,2);
                locationMap.put(5006,7);
                locationMap.put(5007,7);
                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001,5002)),
                        new ArrayList<Integer>( Arrays.asList(2,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                // 2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5000,5003,5004)),
                        new ArrayList<Integer>( Arrays.asList(1,4,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5000,5005, 5006)),
                        new ArrayList<Integer>( Arrays.asList(1,6,7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        2);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5001)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        3);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5001)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        3);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5002)),
                        new ArrayList<Integer>( Arrays.asList(3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        2);
                configList.add(config);
                //7
                config = new Config(7,
                        5006,
                        new ArrayList<Integer>(Arrays.asList(5002,5007)),
                        new ArrayList<Integer>( Arrays.asList(3,8)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        7);
                configList.add(config);
                //8
                config = new Config(8,
                        5007,
                        new ArrayList<Integer>(Arrays.asList(5006)),
                        new ArrayList<Integer>( Arrays.asList(7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        7);
                configList.add(config);
                fileName = "Test_5_EDLAB";
                break;




            /* TEST CASE 6: 6 PEERS - TESTING ON EDLAB */
            case 9:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);

                locationMap = new HashMap<>();
                locationMap.put(5000,1);
                locationMap.put(5001,1);
                locationMap.put(5002,2);
                locationMap.put(5003,7);
                locationMap.put(5004,3);
                locationMap.put(5005,7);

                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001,5002)),
                        new ArrayList<Integer>( Arrays.asList(2,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                // 2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5000,5002)),
                        new ArrayList<Integer>( Arrays.asList(1,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        1);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5000,5001,5003,5004)),
                        new ArrayList<Integer>( Arrays.asList(1,2,4,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        2);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5002,5004,5005)),
                        new ArrayList<Integer>( Arrays.asList(3,5,6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        7);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5002,5003,5005)),
                        new ArrayList<Integer>( Arrays.asList(3,4,6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        3);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5003,5004)),
                        new ArrayList<Integer>( Arrays.asList(4,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        7);
                configList.add(config);

                fileName = "Test_6_EDLAB";
                break;




            /* TEST CASE 7: 12 PEERS - TESTING LOCALLY */

            case 10:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                portMap.put(7, 5006);
                portMap.put(8, 5007);
                portMap.put(9, 5008);
                portMap.put(10,5009);
                portMap.put(11, 5010);
                portMap.put(12, 5011);

                locationMap = new HashMap<>();
                for(int i=0; i<=11; i++)
                {
                    locationMap.put(5000+i, 0);
                }
                // 1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5001,5002)),
                        new ArrayList<Integer>( Arrays.asList(2,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                       5001,
                        new ArrayList<Integer>(Arrays.asList(5000, 5002)),
                        new ArrayList<Integer>( Arrays.asList(1,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5000,5001,5003)),
                        new ArrayList<Integer>( Arrays.asList(1,2,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5002,5004,5005)),
                        new ArrayList<Integer>( Arrays.asList(3,5,6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5003,5005)),
                        new ArrayList<Integer>( Arrays.asList(4,6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5003,5004, 5006)),
                        new ArrayList<Integer>( Arrays.asList(4,5,7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 7
                config = new Config(7,
                        5006,
                        new ArrayList<Integer>(Arrays.asList(5005,5007,5008)),
                        new ArrayList<Integer>( Arrays.asList(6,8,9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 8
                config = new Config(8,
                        5007,
                        new ArrayList<Integer>(Arrays.asList(5006,5008)),
                        new ArrayList<Integer>( Arrays.asList(7,9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 9
                config = new Config(9,
                        5008,
                        new ArrayList<Integer>(Arrays.asList(5006,5007,5009)),
                        new ArrayList<Integer>( Arrays.asList(7,8,10)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 10
                config = new Config(10,
                        5009,
                        new ArrayList<Integer>(Arrays.asList(5008,5010,5011)),
                        new ArrayList<Integer>( Arrays.asList(9,11,12)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 11
                config = new Config(11,
                        5010,
                        new ArrayList<Integer>(Arrays.asList(5009,5011)),
                        new ArrayList<Integer>( Arrays.asList(10,12)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 12
                config = new Config(12,
                        5011,
                        new ArrayList<Integer>(Arrays.asList(5009,5010)),
                        new ArrayList<Integer>( Arrays.asList(10,11)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);

                fileName = "Test_7_local";
                break;





            /* TEST CASE 8: 16 PEERS - TESTING LOCALLY */
            case 11:
                portMap = new HashMap<>();
                portMap.put(1, 5005);
                portMap.put(2, 5011);
                portMap.put(3, 5017);
                portMap.put(4, 5023);
                portMap.put(5, 5025);
                portMap.put(6, 5027);
                portMap.put(7,5029);
                portMap.put(8,5031);
                portMap.put(9, 5033);
                portMap.put(10,5035);
                portMap.put(11,5037);
                portMap.put(12, 5039);
                portMap.put(13, 5041);
                portMap.put(14, 5043);
                portMap.put(15, 5045);
                portMap.put(16, 5047);
                locationMap = new HashMap<>();
                for(int i=0; i<=47; i++)
                {
                    locationMap.put(5000+i, 0);
                }
                // 1
                config = new Config(1,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5011,5017,5023,5025,5041)),
                        new ArrayList<Integer>( Arrays.asList(2,3,4,5,13)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        5011,
                        new ArrayList<Integer>(Arrays.asList(5005)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 3
                config = new Config(3,
                        5017,
                        new ArrayList<Integer>(Arrays.asList(5005)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 4
                config = new Config(4,
                        5023,
                        new ArrayList<Integer>(Arrays.asList(5005)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 5
                config = new Config(5,
                        5025,
                        new ArrayList<Integer>(Arrays.asList(5005,5027,5029,5031,5033)),
                        new ArrayList<Integer>( Arrays.asList(1,6,7,8,9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 6
                config = new Config(6,
                        5027,
                        new ArrayList<Integer>(Arrays.asList(5025)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 7
                config = new Config(7,
                        5029,
                        new ArrayList<Integer>(Arrays.asList(5025)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 8
                config = new Config(8,
                        5031,
                        new ArrayList<Integer>(Arrays.asList(5025)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 9
                config = new Config(9,
                        5033,
                        new ArrayList<Integer>(Arrays.asList(5025,5035,5037,5039,5041)),
                        new ArrayList<Integer>( Arrays.asList(5,10,11,12,13)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 10
                config = new Config(10,
                        5035,
                        new ArrayList<Integer>(Arrays.asList(5033)),
                        new ArrayList<Integer>( Arrays.asList(9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 11
                config = new Config(11,
                        5037,
                        new ArrayList<Integer>(Arrays.asList(5033)),
                        new ArrayList<Integer>( Arrays.asList(9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 12
                config = new Config(12,
                        5039,
                        new ArrayList<Integer>(Arrays.asList(5033)),
                        new ArrayList<Integer>( Arrays.asList(9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 13
                config = new Config(13,
                        5041,
                        new ArrayList<Integer>(Arrays.asList(5005,5033,5043,5045,5047)),
                        new ArrayList<Integer>( Arrays.asList(1,9,14,15,16)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 14
                config = new Config(14,
                       5043,
                        new ArrayList<Integer>(Arrays.asList(5041)),
                        new ArrayList<Integer>( Arrays.asList(13)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 15
                config = new Config(15,
                        5045,
                        new ArrayList<Integer>(Arrays.asList(5041)),
                        new ArrayList<Integer>( Arrays.asList(13)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 16
                config = new Config(16,
                        5047,
                        new ArrayList<Integer>(Arrays.asList(5041)),
                        new ArrayList<Integer>( Arrays.asList(13)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "Test_8_local";
                break;




            /* TEST CASE 9: 17 PEERS - TESTING LOCALLY */
            case 12:
                portMap = new HashMap<>();
                portMap.put(1, 5004);
                portMap.put(2, 5009);
                portMap.put(3, 5014);
                portMap.put(4, 5019);
                portMap.put(5, 5024);
                portMap.put(6, 5026);
                portMap.put(7,5028);
                portMap.put(8,5030);
                portMap.put(9, 5032);
                portMap.put(10,5034);
                portMap.put(11,5036);
                portMap.put(12, 5038);
                portMap.put(13, 5040);
                portMap.put(14, 5042);
                portMap.put(15, 5044);
                portMap.put(16, 5046);
                portMap.put(17, 5048);
                locationMap = new HashMap<>();
                for(int i=0; i<=48; i++)
                {
                    locationMap.put(5000+i, 0);
                }
                // 1
                config = new Config(1,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5009,5026,5034,5042)),
                        new ArrayList<Integer>( Arrays.asList(2,6,10,14)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        5009,
                        new ArrayList<Integer>(Arrays.asList(5004,5014,5019,5024)),
                        new ArrayList<Integer>( Arrays.asList(1,3,4,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 3
                config = new Config(3,
                        5014,
                        new ArrayList<Integer>(Arrays.asList(5009)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 4
                config = new Config(4,
                        5019,
                        new ArrayList<Integer>(Arrays.asList(5009)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 5
                config = new Config(5,
                        5024,
                        new ArrayList<Integer>(Arrays.asList(5009)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 6
                config = new Config(6,
                        5026,
                        new ArrayList<Integer>(Arrays.asList(5004,5028,5030,5032)),
                        new ArrayList<Integer>( Arrays.asList(1,7,8,9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 7
                config = new Config(7,
                        5028,
                        new ArrayList<Integer>(Arrays.asList(5026)),
                        new ArrayList<Integer>( Arrays.asList(6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 8
                config = new Config(8,
                        5030,
                        new ArrayList<Integer>(Arrays.asList(5026)),
                        new ArrayList<Integer>( Arrays.asList(6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 9
                config = new Config(9,
                        5032,
                        new ArrayList<Integer>(Arrays.asList(5026)),
                        new ArrayList<Integer>( Arrays.asList(6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 10
                config = new Config(10,
                        5034,
                        new ArrayList<Integer>(Arrays.asList(5004,5036,5038,5040)),
                        new ArrayList<Integer>( Arrays.asList(1,11,12,13)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 11
                config = new Config(11,
                        5036,
                        new ArrayList<Integer>(Arrays.asList(5034)),
                        new ArrayList<Integer>( Arrays.asList(10)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 12
                config = new Config(12,
                        5038,
                        new ArrayList<Integer>(Arrays.asList(5034)),
                        new ArrayList<Integer>( Arrays.asList(10)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 13
                config = new Config(13,
                        5040,
                        new ArrayList<Integer>(Arrays.asList(5034)),
                        new ArrayList<Integer>( Arrays.asList(10)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 14
                config = new Config(14,
                        5042,
                        new ArrayList<Integer>(Arrays.asList(5004,5044,5046,5048)),
                        new ArrayList<Integer>( Arrays.asList(1,15,16,17)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 15
                config = new Config(15,
                        5044,
                        new ArrayList<Integer>(Arrays.asList(5042)),
                        new ArrayList<Integer>( Arrays.asList(14)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 16
                config = new Config(16,
                        5046,
                        new ArrayList<Integer>(Arrays.asList(5042)),
                        new ArrayList<Integer>( Arrays.asList(14)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 17
                config = new Config(17,
                        5048,
                        new ArrayList<Integer>(Arrays.asList(5042)),
                        new ArrayList<Integer>( Arrays.asList(14)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "Test_9_local";
                break;
            case 13:
                portMap = new HashMap<>();
                portMap.put(1, 5000);
                portMap.put(2, 5001);
                portMap.put(3, 5002);
                portMap.put(4, 5003);
                portMap.put(5, 5004);
                portMap.put(6, 5005);
                portMap.put(7, 5006);
                portMap.put(8, 5007);
                portMap.put(9, 5008);
                locationMap = new HashMap<>();
                for(int i=0; i<=8; i++)
                {
                    locationMap.put(5000+i, 0);
                }
                //1
                config = new Config(1,
                        5000,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //2
                config = new Config(2,
                        5001,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //3
                config = new Config(3,
                        5002,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //4
                config = new Config(4,
                        5003,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //5
                config = new Config(5,
                        5004,
                        new ArrayList<Integer>(Arrays.asList(5000,5001,5002,5003,5005,5006,5007,5008)),
                        new ArrayList<Integer>( Arrays.asList(1,2,3,4,6,7,8,9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //6
                config = new Config(6,
                        5005,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //7
                config = new Config(7,
                        5006,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //8
                config = new Config(8,
                        5007,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //9
                config = new Config(9,
                        5008,
                        new ArrayList<Integer>(Arrays.asList(5004)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "Test_10_local";
                break;
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(configList);
        try
        {
            FileWriter f = new FileWriter(fileName+".txt");
            f.write(jsonString);
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
