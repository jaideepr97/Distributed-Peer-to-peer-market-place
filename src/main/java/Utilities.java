import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utilities {

    public static void main(String[] args) {
        populateTestTopologyConfig(Integer.parseInt(args[0]));
    }

    public static void pathTest()
    {
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath);
        filePath.concat("path to the property file");

    }
    public static void populateTestTopologyConfig(int peers) {
        List<Config> configList = new ArrayList<>();
        HashMap<Integer, Integer> portMap;
        HashMap<Integer, Integer> locationMap;
        Config config;
        String fileName = "";
        switch (peers)
        {
            case 2:
                portMap = new HashMap<>();
                portMap.put(1, 5001);
                portMap.put(2, 5003);
                locationMap = new HashMap();
                locationMap.put(5000, 0);
                locationMap.put(5001, 0);
                locationMap.put(5002, 0);
                locationMap.put(5003, 0);
                // 1
                config = new Config(1,
                        new ArrayList<Integer>(Arrays.asList(5000, 5001)),
                        new ArrayList<Integer>(Arrays.asList(5002)),
                        new ArrayList<Integer>( Arrays.asList(1)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        new ArrayList<Integer>(Arrays.asList(5002, 5003)),
                        new ArrayList<Integer>(Arrays.asList(5000)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "TwoNodes";
                break;
            case 4:
                portMap = new HashMap<>();
                portMap.put(1, 5001);
                portMap.put(2, 5005);
                portMap.put(3, 5008);
                portMap.put(4, 5011);
                locationMap = new HashMap<>();
                locationMap.put(5000, 0);
                locationMap.put(5001, 0);
                locationMap.put(5002, 0);
                locationMap.put(5003, 0);
                locationMap.put(5004, 0);
                locationMap.put(5005, 0);
                locationMap.put(5006, 0);
                locationMap.put(5007, 0);
                locationMap.put(5008, 0);
                locationMap.put(5009, 0);
                locationMap.put(5010, 0);
                locationMap.put(5011, 0);
                // 1
                config = new Config(1,
                        new ArrayList<Integer>(Arrays.asList(5000,5001)),
                        new ArrayList<Integer>(Arrays.asList(5002)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        new ArrayList<Integer>(Arrays.asList(5002,5003,5004, 5005)),
                        new ArrayList<Integer>(Arrays.asList(5000,5006, 5009)),
                        new ArrayList<Integer>( Arrays.asList(1,3,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //3
                config = new Config(3,
                        new ArrayList<Integer>(Arrays.asList(5006,5007,5008)),
                        new ArrayList<Integer>(Arrays.asList(5003,5010)),
                        new ArrayList<Integer>( Arrays.asList(2,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //4
                config = new Config(4,
                        new ArrayList<Integer>(Arrays.asList(5009,5010,5011)),
                        new ArrayList<Integer>(Arrays.asList(5007,5004)),
                        new ArrayList<Integer>( Arrays.asList(2,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "FourNodes";
                break;
            case 6:
                portMap = new HashMap<>();
                portMap.put(1, 5002);
                portMap.put(2, 5007);
                portMap.put(3, 5012);
                portMap.put(4, 5017);
                portMap.put(5, 5022);
                portMap.put(6, 5027);
                locationMap = new HashMap<>();
                locationMap.put(5000, 0);
                locationMap.put(5001, 0);
                locationMap.put(5002, 0);
                locationMap.put(5005, 0);
                locationMap.put(5006, 0);
                locationMap.put(5007, 0);
                locationMap.put(5010, 0);
                locationMap.put(5011, 0);
                locationMap.put(5012, 0);
                locationMap.put(5016, 0);
                locationMap.put(5017, 0);
                locationMap.put(5015, 0);
                locationMap.put(5020, 0);
                locationMap.put(5021, 0);
                locationMap.put(5022, 0);
                locationMap.put(5025, 0);
                locationMap.put(5026, 0);
                locationMap.put(5027, 0);
                // 1
                config = new Config(1,
                        new ArrayList<Integer>(Arrays.asList(5000,5001,5002)),
                        new ArrayList<Integer>(Arrays.asList(5026,5005)),
                        new ArrayList<Integer>( Arrays.asList(2,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        new ArrayList<Integer>(Arrays.asList(5005,5006,5007)),
                        new ArrayList<Integer>(Arrays.asList(5001,5010)),
                        new ArrayList<Integer>( Arrays.asList(1,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //3
                config = new Config(3,
                        new ArrayList<Integer>(Arrays.asList(5010,5011,5012)),
                        new ArrayList<Integer>(Arrays.asList(5006,5015)),
                        new ArrayList<Integer>( Arrays.asList(2,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //4
                config = new Config(4,
                        new ArrayList<Integer>(Arrays.asList(5015,5016,5017)),
                        new ArrayList<Integer>(Arrays.asList(5011,5020)),
                        new ArrayList<Integer>( Arrays.asList(3,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //5
                config = new Config(5,
                        new ArrayList<Integer>(Arrays.asList(5020,5021,5022)),
                        new ArrayList<Integer>(Arrays.asList(5016,5025)),
                        new ArrayList<Integer>( Arrays.asList(6,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //6
                config = new Config(6,
                        new ArrayList<Integer>(Arrays.asList(5025,5026,5027)),
                        new ArrayList<Integer>(Arrays.asList(5021,5000)),
                        new ArrayList<Integer>( Arrays.asList(1,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "SixNodes";
                break;
            case 8:
                portMap = new HashMap<>();
                portMap.put(1, 5003);
                portMap.put(2, 5007);
                portMap.put(3, 5009);
                portMap.put(4, 5012);
                portMap.put(5, 5014);
                portMap.put(6, 5018);
                portMap.put(7,5020);
                portMap.put(8,5023);
                locationMap = new HashMap<>();
                locationMap.put(5000, 0);
                locationMap.put(5001, 0);
                locationMap.put(5002, 0);
                locationMap.put(5003, 0);
                locationMap.put(5004, 0);
                locationMap.put(5005, 0);
                locationMap.put(5006, 0);
                locationMap.put(5007, 0);
                locationMap.put(5008, 0);
                locationMap.put(5009, 0);
                locationMap.put(5010, 0);
                locationMap.put(5011, 0);
                locationMap.put(5012, 0);
                locationMap.put(5013, 0);
                locationMap.put(5014, 0);
                locationMap.put(5015, 0);
                locationMap.put(5016, 0);
                locationMap.put(5017, 0);
                locationMap.put(5018, 0);
                locationMap.put(5019, 0);
                locationMap.put(5020, 0);
                locationMap.put(5021, 0);
                locationMap.put(5022, 0);
                locationMap.put(5023, 0);
                // 1
                config = new Config(1,
                        new ArrayList<Integer>(Arrays.asList(5000,5001,5002,5003)),
                        new ArrayList<Integer>(Arrays.asList(5021,5004,5015)),
                        new ArrayList<Integer>( Arrays.asList(2,6,8)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        new ArrayList<Integer>(Arrays.asList(5004,5005,5006,5007)),
                        new ArrayList<Integer>(Arrays.asList(5000,5008,5010)),
                        new ArrayList<Integer>( Arrays.asList(1,3,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 3
                config = new Config(3,
                        new ArrayList<Integer>(Arrays.asList(5008,5009)),
                        new ArrayList<Integer>(Arrays.asList(5005)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 4
                config = new Config(4,
                        new ArrayList<Integer>(Arrays.asList(5010,5011,5012)),
                        new ArrayList<Integer>(Arrays.asList(5006,5013)),
                        new ArrayList<Integer>( Arrays.asList(2,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 5
                config = new Config(5,
                        new ArrayList<Integer>(Arrays.asList(5013,5014)),
                        new ArrayList<Integer>(Arrays.asList(5011)),
                        new ArrayList<Integer>( Arrays.asList(4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 6
                config = new Config(6,
                        new ArrayList<Integer>(Arrays.asList(5015,5016,5017,5018)),
                        new ArrayList<Integer>(Arrays.asList(5001,5019,5022)),
                        new ArrayList<Integer>( Arrays.asList(1,7,8)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 7
                config = new Config(7,
                        new ArrayList<Integer>(Arrays.asList(5019,5020)),
                        new ArrayList<Integer>(Arrays.asList(5016)),
                        new ArrayList<Integer>( Arrays.asList(6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 8
                config = new Config(8,
                        new ArrayList<Integer>(Arrays.asList(5021,5022,5023)),
                        new ArrayList<Integer>(Arrays.asList(5017,5002)),
                        new ArrayList<Integer>( Arrays.asList(1,6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "EightNodes";
                break;
            case 9:
                portMap = new HashMap<>();
                portMap.put(1, 5001);
                portMap.put(2, 5003);
                portMap.put(3, 5005);
                portMap.put(4, 5007);
                portMap.put(5, 5016);
                portMap.put(6, 5018);
                portMap.put(7,5020);
                portMap.put(8,5022);
                portMap.put(9, 5024);
                locationMap = new HashMap<>();
                locationMap.put(5000, 0);
                locationMap.put(5001, 0);
                locationMap.put(5002, 0);
                locationMap.put(5003, 0);
                locationMap.put(5004, 0);
                locationMap.put(5005, 0);
                locationMap.put(5006, 0);
                locationMap.put(5007, 0);
                locationMap.put(5008, 0);
                locationMap.put(5009, 0);
                locationMap.put(5010, 0);
                locationMap.put(5011, 0);
                locationMap.put(5012, 0);
                locationMap.put(5013, 0);
                locationMap.put(5014, 0);
                locationMap.put(5015, 0);
                locationMap.put(5016, 0);
                locationMap.put(5017, 0);
                locationMap.put(5018, 0);
                locationMap.put(5019, 0);
                locationMap.put(5020, 0);
                locationMap.put(5021, 0);
                locationMap.put(5022, 0);
                locationMap.put(5023, 0);
                locationMap.put(5024, 0);
                // 1
                config = new Config(1,
                        new ArrayList<Integer>(Arrays.asList(5000,5001)),
                        new ArrayList<Integer>(Arrays.asList(5008)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        new ArrayList<Integer>(Arrays.asList(5002,5003)),
                        new ArrayList<Integer>(Arrays.asList(5009)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 3
                config = new Config(3,
                        new ArrayList<Integer>(Arrays.asList(5004,5005)),
                        new ArrayList<Integer>(Arrays.asList(5010)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 4
                config = new Config(4,
                        new ArrayList<Integer>(Arrays.asList(5006,5007)),
                        new ArrayList<Integer>(Arrays.asList(5011)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 5
                config = new Config(5,
                        new ArrayList<Integer>(Arrays.asList(5008,5009,5010,5011,5012,5013,5014,5015,5016)),
                        new ArrayList<Integer>(Arrays.asList(5000,5002,5004,5006,5017,5019,5021,5023)),
                        new ArrayList<Integer>( Arrays.asList(1,2,3,4,6,7,8,9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 6
                config = new Config(6,
                        new ArrayList<Integer>(Arrays.asList(5017,5018)),
                        new ArrayList<Integer>(Arrays.asList(5012)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 7
                config = new Config(7,
                        new ArrayList<Integer>(Arrays.asList(5019,5020)),
                        new ArrayList<Integer>(Arrays.asList(5013)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 8
                config = new Config(8,
                        new ArrayList<Integer>(Arrays.asList(5021,5022)),
                        new ArrayList<Integer>(Arrays.asList(5014)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 9
                config = new Config(9,
                        new ArrayList<Integer>(Arrays.asList(5023,5024)),
                        new ArrayList<Integer>(Arrays.asList(5015)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "NineNodes";
                break;
            case 7:
                portMap = new HashMap<>();
                portMap.put(1, 5001);
                portMap.put(2, 5004);
                portMap.put(3, 5007);
                portMap.put(4, 5010);
                portMap.put(5, 5013);
                portMap.put(6, 5016);
                portMap.put(7, 5018);
                locationMap = new HashMap<>();
                locationMap.put(5000, 0);
                locationMap.put(5001, 0);
                locationMap.put(5002, 0);
                locationMap.put(5003, 0);
                locationMap.put(5004, 0);
                locationMap.put(5005, 0);
                locationMap.put(5006, 0);
                locationMap.put(5007, 0);
                locationMap.put(5008, 0);
                locationMap.put(5009, 0);
                locationMap.put(5010, 0);
                locationMap.put(5011, 0);
                locationMap.put(5012, 0);
                locationMap.put(5013, 0);
                locationMap.put(5014, 0);
                locationMap.put(5015, 0);
                locationMap.put(5016, 0);
                locationMap.put(5017, 0);
                locationMap.put(5018, 0);
                // 1
                config = new Config(1,
                        new ArrayList<Integer>(Arrays.asList(5000,5001)),
                        new ArrayList<Integer>(Arrays.asList(5002)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        new ArrayList<Integer>(Arrays.asList(5002,5003,5004)),
                        new ArrayList<Integer>(Arrays.asList(5000,5005)),
                        new ArrayList<Integer>( Arrays.asList(1,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //3
                config = new Config(3,
                        new ArrayList<Integer>(Arrays.asList(5005,5006,5007)),
                        new ArrayList<Integer>(Arrays.asList(5003,5008)),
                        new ArrayList<Integer>( Arrays.asList(2,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //4
                config = new Config(4,
                        new ArrayList<Integer>(Arrays.asList(5008,5009,5010)),
                        new ArrayList<Integer>(Arrays.asList(5006,5011)),
                        new ArrayList<Integer>( Arrays.asList(3,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //5
                config = new Config(5,
                        new ArrayList<Integer>(Arrays.asList(5011,5012,5013)),
                        new ArrayList<Integer>(Arrays.asList(5009,5014)),
                        new ArrayList<Integer>( Arrays.asList(6,4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //6
                config = new Config(6,
                        new ArrayList<Integer>(Arrays.asList(5014,5015,5016)),
                        new ArrayList<Integer>(Arrays.asList(5012,5017)),
                        new ArrayList<Integer>( Arrays.asList(1,7)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                //7
                config = new Config(7,
                        new ArrayList<Integer>(Arrays.asList(5017,5018)),
                        new ArrayList<Integer>(Arrays.asList(5014)),
                        new ArrayList<Integer>( Arrays.asList(6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "SevenNodes";
                break;
            case 12:
                portMap = new HashMap<>();
                portMap.put(1, 5002);
                portMap.put(2, 5007);
                portMap.put(3, 5012);
                portMap.put(4, 5015);
                portMap.put(5, 5017);
                portMap.put(6, 5020);
                portMap.put(7,5022);
                portMap.put(8,5025);
                portMap.put(9, 5027);
                portMap.put(10,5029);
                portMap.put(11,5031);
                portMap.put(12, 5033);
                locationMap = new HashMap<>();
                locationMap.put(5000, 0);
                locationMap.put(5001, 0);
                locationMap.put(5002, 0);
                locationMap.put(5003, 0);
                locationMap.put(5004, 0);
                locationMap.put(5005, 0);
                locationMap.put(5006, 0);
                locationMap.put(5007, 0);
                locationMap.put(5008, 0);
                locationMap.put(5009, 0);
                locationMap.put(5010, 0);
                locationMap.put(5011, 0);
                locationMap.put(5012, 0);
                locationMap.put(5013, 0);
                locationMap.put(5014, 0);
                locationMap.put(5015, 0);
                locationMap.put(5016, 0);
                locationMap.put(5017, 0);
                locationMap.put(5018, 0);
                locationMap.put(5019, 0);
                locationMap.put(5020, 0);
                locationMap.put(5021, 0);
                locationMap.put(5022, 0);
                locationMap.put(5023, 0);
                locationMap.put(5024, 0);
                locationMap.put(5025, 0);
                locationMap.put(5026, 0);
                locationMap.put(5027, 0);
                locationMap.put(5028, 0);
                locationMap.put(5029, 0);
                locationMap.put(5030, 0);
                locationMap.put(5031, 0);
                locationMap.put(5032, 0);
                locationMap.put(5033, 0);
                // 1
                config = new Config(1,
                        new ArrayList<Integer>(Arrays.asList(5000,5001,5002)),
                        new ArrayList<Integer>(Arrays.asList(5003,5008)),
                        new ArrayList<Integer>( Arrays.asList(2,3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        new ArrayList<Integer>(Arrays.asList(5003,5004,5005,5006,5007)),
                        new ArrayList<Integer>(Arrays.asList(5000,5013,5016,5018)),
                        new ArrayList<Integer>( Arrays.asList(1,4,5,6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 3
                config = new Config(3,
                        new ArrayList<Integer>(Arrays.asList(5008,5009,5010,5011,5012)),
                        new ArrayList<Integer>(Arrays.asList(5001,5021,5023,5026)),
                        new ArrayList<Integer>( Arrays.asList(1,7,8,9)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 4
                config = new Config(4,
                        new ArrayList<Integer>(Arrays.asList(5013,5014,5015)),
                        new ArrayList<Integer>(Arrays.asList(5004,5028)),
                        new ArrayList<Integer>( Arrays.asList(2,10)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 5
                config = new Config(5,
                        new ArrayList<Integer>(Arrays.asList(5016,5017)),
                        new ArrayList<Integer>(Arrays.asList(5005)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 6
                config = new Config(6,
                        new ArrayList<Integer>(Arrays.asList(5018,5019,5020)),
                        new ArrayList<Integer>(Arrays.asList(5006,5030)),
                        new ArrayList<Integer>( Arrays.asList(2,11)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 7
                config = new Config(7,
                        new ArrayList<Integer>(Arrays.asList(5021,5022)),
                        new ArrayList<Integer>(Arrays.asList(5009)),
                        new ArrayList<Integer>( Arrays.asList(3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 8
                config = new Config(8,
                        new ArrayList<Integer>(Arrays.asList(5023,5024,5025)),
                        new ArrayList<Integer>(Arrays.asList(5010,5032)),
                        new ArrayList<Integer>( Arrays.asList(3,12)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 9
                config = new Config(9,
                        new ArrayList<Integer>(Arrays.asList(5026,5027)),
                        new ArrayList<Integer>(Arrays.asList(5011)),
                        new ArrayList<Integer>( Arrays.asList(3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 10
                config = new Config(10,
                        new ArrayList<Integer>(Arrays.asList(5028,5029)),
                        new ArrayList<Integer>(Arrays.asList(5014)),
                        new ArrayList<Integer>( Arrays.asList(4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 11
                config = new Config(11,
                        new ArrayList<Integer>(Arrays.asList(5030,5031)),
                        new ArrayList<Integer>(Arrays.asList(5019)),
                        new ArrayList<Integer>( Arrays.asList(6)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 12
                config = new Config(12,
                        new ArrayList<Integer>(Arrays.asList(5032,5033)),
                        new ArrayList<Integer>(Arrays.asList(5024)),
                        new ArrayList<Integer>( Arrays.asList(8)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "TwelveNodes";
            break;
            case 17:
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
                        new ArrayList<Integer>(Arrays.asList(5000,5001,5002,5003,5004)),
                        new ArrayList<Integer>(Arrays.asList(5005,5010,5015,5020)),
                        new ArrayList<Integer>( Arrays.asList(2,3,4,5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 2
                config = new Config(2,
                        new ArrayList<Integer>(Arrays.asList(5005,5006,5007,5008,5009)),
                        new ArrayList<Integer>(Arrays.asList(5001,5047,5045,5043)),
                        new ArrayList<Integer>( Arrays.asList(1,15,16,17)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 3
                config = new Config(3,
                        new ArrayList<Integer>(Arrays.asList(5010,5011,5012,5013,5014)),
                        new ArrayList<Integer>(Arrays.asList(5002,5037,5039,5041)),
                        new ArrayList<Integer>( Arrays.asList(1,12,13,14)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 4
                config = new Config(4,
                        new ArrayList<Integer>(Arrays.asList(5015,5016,5017, 5018, 5019)),
                        new ArrayList<Integer>(Arrays.asList(5000,5031, 5033, 5035)),
                        new ArrayList<Integer>( Arrays.asList(1,9,10,11)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 5
                config = new Config(5,
                        new ArrayList<Integer>(Arrays.asList(5020,5021,5022,5023,5024)),
                        new ArrayList<Integer>(Arrays.asList(5003,5025,5027,5029)),
                        new ArrayList<Integer>( Arrays.asList(1,6,7,8)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 6
                config = new Config(6,
                        new ArrayList<Integer>(Arrays.asList(5025,5026)),
                        new ArrayList<Integer>(Arrays.asList(5021)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 7
                config = new Config(7,
                        new ArrayList<Integer>(Arrays.asList(5027,5028)),
                        new ArrayList<Integer>(Arrays.asList(5022)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 8
                config = new Config(8,
                        new ArrayList<Integer>(Arrays.asList(5029,5030)),
                        new ArrayList<Integer>(Arrays.asList(5023)),
                        new ArrayList<Integer>( Arrays.asList(5)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 9
                config = new Config(9,
                        new ArrayList<Integer>(Arrays.asList(5031,5032)),
                        new ArrayList<Integer>(Arrays.asList(5016)),
                        new ArrayList<Integer>( Arrays.asList(4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 10
                config = new Config(10,
                        new ArrayList<Integer>(Arrays.asList(5033,5034)),
                        new ArrayList<Integer>(Arrays.asList(5017)),
                        new ArrayList<Integer>( Arrays.asList(4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 11
                config = new Config(11,
                        new ArrayList<Integer>(Arrays.asList(5035,5036)),
                        new ArrayList<Integer>(Arrays.asList(5018)),
                        new ArrayList<Integer>( Arrays.asList(4)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 12
                config = new Config(12,
                        new ArrayList<Integer>(Arrays.asList(5037,5038)),
                        new ArrayList<Integer>(Arrays.asList(5011)),
                        new ArrayList<Integer>( Arrays.asList(3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 13
                config = new Config(13,
                        new ArrayList<Integer>(Arrays.asList(5039,5040)),
                        new ArrayList<Integer>(Arrays.asList(5012)),
                        new ArrayList<Integer>( Arrays.asList(3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 14
                config = new Config(14,
                        new ArrayList<Integer>(Arrays.asList(5041,5042)),
                        new ArrayList<Integer>(Arrays.asList(5013)),
                        new ArrayList<Integer>( Arrays.asList(3)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 15
                config = new Config(15,
                        new ArrayList<Integer>(Arrays.asList(5043,5044)),
                        new ArrayList<Integer>(Arrays.asList(5008)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 16
                config = new Config(16,
                        new ArrayList<Integer>(Arrays.asList(5045,5046)),
                        new ArrayList<Integer>(Arrays.asList(5007)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                // 17
                config = new Config(17,
                        new ArrayList<Integer>(Arrays.asList(5047,5048)),
                        new ArrayList<Integer>(Arrays.asList(5006)),
                        new ArrayList<Integer>( Arrays.asList(2)),
                        new HashMap<>(portMap),
                        new HashMap<>(locationMap),
                        0);
                configList.add(config);
                fileName = "SeventeenNodes";
                break;
        }
        // create a new Gson instance
        Gson gson = new Gson();
        // convert your list to json
        String jsonString = gson.toJson(configList);
        // print your generated json
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
    public static void test() throws InterruptedException
    {
        long ut1 = Instant.now().getEpochSecond();
        Thread.sleep(1000);
        long ut2 = Instant.now().getEpochSecond();
        System.out.println(ut2-ut1);

    }


}
