import java.util.HashMap;
import java.util.List;

/**
 * This class defines the fields for the config file, which are serialized into a string
 */
public class Config {

    private int peerID;
    private int serverPort;
    private List<Integer> neighborPorts;
    private List<Integer> neighborIDs;
    private HashMap<Integer, Integer> portMap;
    private HashMap<Integer, Integer> locationMap;
    private int location;

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Config(int _peerID,
                  int _serverPort,
                  List<Integer> _neighborPorts,
                  List<Integer> _neighborIDs,
                  HashMap<Integer, Integer> _portMap,
                  HashMap<Integer, Integer> _locationMap,
                  int _location)
    {
        this.peerID = _peerID;
        this.serverPort = _serverPort;
        this.neighborPorts = _neighborPorts;
        this.neighborIDs = _neighborIDs;
        this.portMap = _portMap;
        this.locationMap = _locationMap;
        this.location = _location;
    }
    public HashMap<Integer, Integer> getLocationMap() {
        return locationMap;
    }

    public void setLocationMap(HashMap<Integer, Integer> locationMap) {
        this.locationMap = locationMap;
    }
    public int getPeerID() {
        return peerID;
    }

    public void setPeerID(int peerID) {
        this.peerID = peerID;
    }

    public List<Integer> getNeighborIDs() {
        return neighborIDs;
    }

    public void setNeighborIDs(List<Integer> neighborIDs) {
        this.neighborIDs = neighborIDs;
    }

    public List<Integer> getNeighborPorts() {
        return neighborPorts;
    }

    public void setNeighborPorts(List<Integer> neighborPorts) {
        this.neighborPorts = neighborPorts;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public HashMap<Integer, Integer> getPortMap() {
        return portMap;
    }

    public void setPortMap(HashMap<Integer, Integer> portMap) {
        this.portMap = portMap;
    }
}


