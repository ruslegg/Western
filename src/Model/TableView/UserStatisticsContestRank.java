package Model.TableView;

public class UserStatisticsContestRank {
    String contestName;
    int rank,contestId;

    public UserStatisticsContestRank(int contestId,String contestName, int rank) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.rank = rank;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }
}
