package Modles;

/**
 * Created by Prateek on 9/5/2017.
 */
public class UserData {

    private String Status,Message;
    private boolean permanent;
    private Data data;


    @Override
    public String toString() {
        return "UserData{" +
                "Status='" + Status + '\'' +
                ", Message='" + Message + '\'' +
                ", permanent=" + permanent +
                ", data=" + data +
                '}';
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
