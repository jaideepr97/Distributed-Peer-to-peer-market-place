import java.util.ArrayList;
import java.util.List;

public class Message {
    private String productName;
    private int productId;
    public List<Integer> messagePath;
    private int hopCount;
    private int type;
    private int requestId;
    private int sourcePeerId;
    private int destinationSellerId;

    public int getDestinationSellerId() {
        return destinationSellerId;
    }

    public void setDestinationSellerId(int destinationSellerId) {
        this.destinationSellerId = destinationSellerId;
    }

    public Message(){
        this.productName = "";
        this.productId = -1;
        this.hopCount = 0;
        this.type = 0;
        this.messagePath = new ArrayList<>();
        this.requestId = 0;
        this.sourcePeerId = 0;
        this.destinationSellerId = -1;

    }

    public int getProductId() {
        return productId;
    }

    public int getHopCount() {
        return hopCount;
    }

    public int getType() {
        return type;
    }

    public String getProductName() {
        return productName;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getSourcePeerId() {
        return sourcePeerId;
    }

    public void setHopCount(int hopCount) {
        this.hopCount = hopCount;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setSourcePeerId(int sourcePeerId) {
        this.sourcePeerId = sourcePeerId;
    }

    public List<Integer> getMessagePath() {
        return messagePath;
    }

    public void setMessagePath(List<Integer> messagePath) {
        this.messagePath = messagePath;
    }

    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Message message = (Message) object;
            if (this.sourcePeerId == message.getSourcePeerId() &&
                    this.requestId == message.getRequestId()) {
                result = true;
            }
        }
        return result;
    }
    public static Message deserializeMessage(String s)
    {
        System.out.println("Message:"+s+"\n");
        Message m = new Message();
        String[] objArray = s.split("#");
        String[] list = objArray[2].split(",");
        m.setProductName(objArray[0]);
        m.setProductId(Integer.parseInt(objArray[1]));
        List<Integer> tempList = new ArrayList();
        for(int i=0; i<list.length; i++)
        {
            if(list[i].length() == 0)
                continue;
            tempList.add(Integer.parseInt(list[i]));
        }
        m.setMessagePath(tempList);
        m.setHopCount(Integer.parseInt(objArray[3]));
        m.setType(Integer.parseInt(objArray[4]));
        m.setRequestId(Integer.parseInt(objArray[5]));
        m.setSourcePeerId(Integer.parseInt(objArray[6]));
        m.setDestinationSellerId(Integer.parseInt(objArray[7]));
        return m;
    }
    public static String serializeMessage(Message m)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(m.getProductName());
        sb.append("#");
        sb.append(m.getProductId());
        sb.append("#");
        for(int i: m.getMessagePath())
        {
            sb.append(i);
            sb.append(",");
        }
        sb.append("#");
        sb.append(m.getHopCount());
        sb.append("#");
        sb.append(m.getType());
        sb.append("#");
        sb.append(m.getRequestId());
        sb.append("#");
        sb.append(m.getSourcePeerId());
        sb.append("#");
        sb.append(m.getDestinationSellerId());
        sb.append("#");
        sb.append("\n");
        return sb.toString();
    }


}
