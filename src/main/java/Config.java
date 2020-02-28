//package main.java;
import java.util.HashMap;
import java.util.List;

public class Config {

    private int peerID;
    private List<Integer> serverPorts;
    private List<Integer> neighborPorts;
    private List<Integer> neighborIDs;
    private HashMap<Integer, Integer> portMap;

    public Config(int _peerID,
                  List<Integer> _serverPorts,
                  List<Integer> _neighborPorts,
                  List<Integer> _neighborIDs,
                  HashMap<Integer, Integer> _portMap)
    {
        this.peerID = _peerID;
        this.serverPorts = _serverPorts;
        this.neighborPorts = _neighborPorts;
        this.neighborIDs = _neighborIDs;
        this.portMap = _portMap;
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

    public List<Integer> getServerPorts() {
        return serverPorts;
    }

    public void setServerPorts(List<Integer> serverPorts) {
        this.serverPorts = serverPorts;
    }

    public HashMap<Integer, Integer> getPortMap() {
        return portMap;
    }

    public void setPortMap(HashMap<Integer, Integer> portMap) {
        this.portMap = portMap;
    }
}


