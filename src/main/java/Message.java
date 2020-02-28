import java.util.ArrayList;
import java.util.List;

public class Message {
    private String productName;
    private String productId;
    public List<Integer> messagePath;
    private int hopCount;
    private int type;
    private int requestId;
    private int sourcePeerId;

    public Message(){
        this.productName = "";
        this.productId = "";
        this.hopCount = 0;
        this.type = 0;
        this.messagePath = new ArrayList<>();
        this.requestId = 0;
        this.sourcePeerId = 0;

    }

    public String getProductId() {
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

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setId(int requestId) {
        this.requestId = requestId;
    }

    public void setSourcePeerId(int sourcePeerId) {
        this.sourcePeerId = sourcePeerId;
    }

    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Message message = (Message) object;
            if (this.hopCount == message.getHopCount() && this.type == message.getType() &&
                    this.productName.equals(message.getProductName()) &&
                    this.sourcePeerId == message.getSourcePeerId() &&
                    this.requestId == message.getRequestId() &&
                    this.productId.equals(message.getProductId())) {
                result = true;
            }
        }
        return result;
    }


}
