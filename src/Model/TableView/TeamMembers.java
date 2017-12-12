package Model.TableView;

public class TeamMembers {
    String memberName;
    int memberPoints;

    public TeamMembers(String memberName, int memberPoints) {
        this.memberName = memberName;
        this.memberPoints = memberPoints;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMemberPoints() {
        return memberPoints;
    }

    public void setMemberPoints(int memberPoints) {
        this.memberPoints = memberPoints;
    }
}
