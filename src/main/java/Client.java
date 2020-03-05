
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;

/**
 * This class implements the client part of the peer
 */
public class Client implements Runnable, P2PInterface
{
    private int port;
    private int peerId;
    private Socket clientSocket;
    DataOutputStream clientOutputStream;
    BufferedReader bufferedReader;
    volatile boolean running;
    Message message;
    int nextPeerID;
    String host;
    public Client(String _host, int _port, int peerId, Message message, int _nextPeerID)
    {
        this.port = _port;
        this.peerId = peerId;
        this.message = message;
        this.nextPeerID = _nextPeerID;
        running = true;
        this.host = _host;
    }

    public void stopThread()
    {
        running = false;
    }

    /**
     * Establishes the socket connection to the server
     * @throws IOException
     */
    public void getSocket() throws IOException
    {
        clientSocket = new Socket(this.host, this.port);
        clientOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    public void closeSocket() throws IOException
    {
        if(clientSocket != null) {
            clientSocket.close();
        }
        if(clientOutputStream != null) {
            clientOutputStream.close();
        }
        if(bufferedReader != null) {
            bufferedReader.close();
        }
    }

    @Override
    public void run()  {
        while(running) {
            try
            {
                //Client, Sending request......
                this.getSocket();
                if(this.message.getType() == 0) {
                    //Client, Request type is 0
                    lookup(this.message.getProductName(), this.message.getHopCount());
                }
                else if(this.message.getType() == 1) {
                    //Client, Request type is 1
                    int destinationPeerId = this.nextPeerID;
                    reply(destinationPeerId);
                }
                else if(this.message.getType() == 2) {
                    //Client, Request type is 2
                    buy(this.message.getDestinationSellerId());
                }
            }
            catch (ConnectException e)
            {
                System.out.println("Client:"+peerId+", ConnectException in run():\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            catch (Exception e)
            {
                System.out.println("Client:"+peerId+", Exception in run():\n");
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * @param productName - Contains the product name that the buyer needs to buy
     * @param hopCount - Decrements after every call
     */
    @Override
    public void lookup(String productName, int hopCount) {
        try {
            clientOutputStream.writeBytes(Message.serializeMessage(this.message));

        } catch (IOException e) {
            System.out.println("Client:"+peerId+", Exception in lookup():\n");
            e.printStackTrace();
        }
        finally {
            this.stopThread();
        }
    }

    /**
     *
     * @param sellerID - Contains the ID of the seller
     */
    @Override
    public void buy(int sellerID) {
        try {
            clientOutputStream.writeBytes(Message.serializeMessage(this.message));
            synchronized (PeerNode.servicedRequests)
            {
                PeerNode.servicedRequests.put(this.message.getRequestId(), 0);
            }
            String ack = bufferedReader.readLine();
            if(ack.equals("0"))
            {
                System.out.println("\n-------Bought product:"+message.getProductName()+
                        " from seller:"+message.getDestinationSellerId()+"------------\n");
            }
            else
            {
                System.out.println("\n-------Buy failed! Product:"+message.getProductName()+
                        " from seller:"+message.getDestinationSellerId()+"------------\n");
            }

        } catch (IOException e) {
            System.out.println("Client:"+peerId+", Exception in buy():\n");
            e.printStackTrace();
        }
        finally {
            this.stopThread();
        }
    }

    /**
     *
     * @param sellerID - Contains the ID of the seller
     */
    @Override
    public void reply(int sellerID) {
        try {
            clientOutputStream.writeBytes(Message.serializeMessage(this.message));
        } catch (IOException e) {
            System.out.println("Client:"+peerId+", Exception in reply():\n");
            e.printStackTrace();
        }
        finally {
            this.stopThread();
        }
    }
}
