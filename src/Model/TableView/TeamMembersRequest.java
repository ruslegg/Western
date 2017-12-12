package Model.TableView;

public class TeamMembersRequest {
    int requestId;
    String requestName;

    public TeamMembersRequest(int requestId, String requestName) {
        this.requestId = requestId;
        this.requestName = requestName;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }
}
