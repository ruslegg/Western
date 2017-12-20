package Model.TableView;

/**
 * Object that stands for Student's Statistics in Contest TableView that shows the rank for each contest
 */
public class UserStatisticsContestRank {
    private String contestName;
    private int rank, contestId;

    public UserStatisticsContestRank(int contestId, String contestName, int rank) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.rank = rank;
    }

}
