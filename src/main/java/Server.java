import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class contains the code for handling the server part of the peer.
 */
public class Server implements Runnable
{
    int port;
    int peerID;
    int productToSell;
    ServerSocket serverSocket ;
    Socket clientSocket ;
    BufferedReader bufferedReader ;
    DataOutputStream outputStream ;
    volatile boolean running;
    private ExecutorService executor = Executors.newFixedThreadPool(15);
    public Server(int _port, int _peerID, int _productToSell)
    {
        this.port = _port;
        this.peerID = _peerID;
        this.productToSell = _productToSell;
        running = true;
    }

    /**
     * This function listens for an incoming client and performs the necessary task
     * @throws IOException
     */
    public void listen() throws IOException
    {
        serverSocket = new ServerSocket(port);
        while (!Thread.interrupted()) {
            try
            {
                //Server, Listening........
                clientSocket = serverSocket.accept();
                ServerExecutor serverExecutor = new ServerExecutor(peerID, clientSocket);
                executor.submit(serverExecutor);
            }
            catch (IOException e)
            {
                System.out.println("Server:"+peerID+", Exception in listen():\n");
                e.printStackTrace();
            }
        }
        serverSocket.close();
    }

    public void run(){

        try
        {
            //Starting Server
            listen();
        }
        catch(Exception e)
        {
            System.out.println("Server:"+this.peerID+", Exception in run():\n");
            e.printStackTrace();
        }

    }
}

class ServerExecutor implements Runnable
{
    int peerID;
    Socket clientSocket;

    public ServerExecutor(int _peerID, Socket _clientSocket)
    {
        this.peerID = _peerID;
        this.clientSocket = _clientSocket;
    }
    @Override
    public void run() {

        try(DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));)
        {
            String data = null;
            if((data = bufferedReader.readLine()) != null)
            {
                //Server, Data Received!
                Message message = Message.deserializeMessage(data);
                synchronized (PeerNode.requestHistory)
                {
                    if(PeerNode.requestHistory.keySet().contains(message) ) {
                        return;
                    }
                }
                if(message.getType() == 0 && message.getHopCount() < 1) {
                    return;
                }
                switch(message.getType()) {
                    case 0:
                        //Server, Request Type 0
                        if((PeerNode.role == 1 || PeerNode.role == 2) &&
                                message.getProductId() == PeerNode.productToSell) {
                            //Server, Seller has the product!
                            Message replyMessage = new Message();
                            replyMessage.setDestinationSellerId(this.peerID);
                            replyMessage.setProductName(PeerNode.productMap.get(message.getProductId()));
                            replyMessage.setType(1);
                            replyMessage.setSourcePeerId(message.getSourcePeerId());
                            replyMessage.setMessagePath(message.getMessagePath());
                            replyMessage.setRequestId(message.getRequestId());
                            replyMessage.setProductId(message.getProductId());
                            replyMessage.setHopCount(0);
                            replyMessage.setProductName(message.getProductName());
                            synchronized (PeerNode.sharedReplyBuffer)
                            {
                                //Server, Adding to sharedReplyBuffer
                                PeerNode.sharedReplyBuffer.offer(replyMessage);
                            }
                        }
                        else {
                            if(PeerNode.role == 0)
                            {
                                //Server is not a seller, forwarding...
                            }
                            else
                            {
                                //Server, Seller does not have the product, forwarding...
                            }
                            synchronized (PeerNode.sharedRequestBuffer) {
                                //Server, Adding to sharedRequestBuffer.
                                PeerNode.sharedRequestBuffer.offer(message);
                            }
                        }
                        break;
                    case 1:
                        //Server, Request Type 1
                        if(message.getSourcePeerId() == this.peerID) {
                            //Server, This is the request originator!
                            Message replyMessage = new Message();
                            replyMessage.setDestinationSellerId(message.getDestinationSellerId());
                            replyMessage.setProductName(PeerNode.productMap.get(message.getProductId()));
                            replyMessage.setType(2);
                            replyMessage.setSourcePeerId(message.getSourcePeerId());
                            replyMessage.setMessagePath(message.getMessagePath());
                            replyMessage.setRequestId(message.getRequestId());
                            replyMessage.setProductId(message.getProductId());
                            replyMessage.setHopCount(0);
                            replyMessage.setProductName(message.getProductName());
                            synchronized (PeerNode.sharedTransactionBuffer) {
                                //Server, Adding to sharedTransactionBuffer.
                                PeerNode.sharedTransactionBuffer.offer(replyMessage);
                            }
                            System.out.println("\n#############Reply received from:"+ message.getDestinationSellerId()+
                                    " for product:"+ message.getProductName()+"#############\n");
                        }
                        else {
                            //Server, This is not the originator, forwarding...
                            synchronized (PeerNode.sharedReplyBuffer) {
                                //Server, Adding to sharedReplyBuffer.
                                PeerNode.sharedReplyBuffer.offer(message);
                            }
                        }

                        break;
                    case 2:
                        //Server, Request Type 2
                        synchronized (PeerNode.numberOfItems) {
                            if(PeerNode.numberOfItems.get() > 0) {
                                //Server, Decrementing number of items
                                int newNumberOfItems = PeerNode.numberOfItems.decrementAndGet();
                                System.out.println("\n----------Product:"+message.getProductName()
                                        +" sold!! Buyer:"+message.getSourcePeerId()+", Seller:"
                                        +peerID+" -------------\n");
                                outputStream.writeBytes("0" + "\n");
                            }
                            else
                            {
                                outputStream.writeBytes("-1\n");
                            }
                        }
                        break;
                }
                //Check if the current server has items
                synchronized (PeerNode.requestHistory) {
                    //Server, Adding to requestHistory.
                    PeerNode.requestHistory.put(message, 0);
                }
            }
            clientSocket.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
