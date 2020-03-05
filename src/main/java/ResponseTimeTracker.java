
import java.util.ArrayList;
import java.util.List;

public class ResponseTimeTracker {

    public long creationTime;
    public List<Long> responseTimes;

    public ResponseTimeTracker(){
        this.creationTime = 0L;
        this.responseTimes = new ArrayList<>();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}

