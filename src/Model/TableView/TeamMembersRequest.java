package Model.TableView;

/**
 * Object standing for Team Requests TableView for team-leader
 */
public class TeamMembersRequest {
    private int requestId;
    private String requestName;

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
