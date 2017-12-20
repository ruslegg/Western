package Model.TableView;


/**
 * Object used for Teacher's Competition TableView
 */

public class TeacherCompetition {
    private String competitionName;
    private int competitionId;
    private int competitionNumber = 0;

    public TeacherCompetition(int competitionId, String competitionName) {
        this.competitionName = competitionName;
        this.competitionId = competitionId;
    }

    /**
     * Increments competition number
     */
    public void increment() {
        competitionNumber++;
    }


    public int getCompetitionId() {
        return competitionId;
    }


}
