package Model.TableView;

/**
 * Object standing for Team Members TableView
 */
public class TeamMembers {
    private String memberName;
    private int memberPoints;

    public TeamMembers(String memberName, int memberPoints) {
        this.memberName = memberName;
        this.memberPoints = memberPoints;
    }

    public String getMemberName() {
        return memberName;
    }

}
