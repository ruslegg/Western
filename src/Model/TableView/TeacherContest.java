package Model.TableView;

/**
 * Object used for Teacher's Contest TableView
 */
public class TeacherContest {
    private String contestName;
    private int contestId;
    private int contestNumber = 0;

    public TeacherContest(int contestId, String contestName) {
        this.contestName = contestName;
        this.contestId = contestId;
    }

    /**
     * Increments contest number
     */
    public void increment() {
        contestNumber++;
    }


    public int getContestId() {
        return contestId;
    }

}
