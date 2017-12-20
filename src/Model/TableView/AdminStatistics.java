package Model.TableView;


/**
 * Object used in Teacher's Statistics in Admin's Menu for TableView
 */
public class AdminStatistics {
    private Integer teacherTableViewId;
    private String teacherTableViewName;
    private Integer teacherTableViewCompetitionNumber;
    private Integer teacherTableViewContestNumber;


    public AdminStatistics(Integer teacherTableViewId, String teacherTableViewName, Integer teacherTableViewCompetitionNumber, Integer teacherTableViewContestNumber) {
        this.teacherTableViewId = teacherTableViewId;
        this.teacherTableViewName = teacherTableViewName;
        this.teacherTableViewCompetitionNumber = teacherTableViewCompetitionNumber;
        this.teacherTableViewContestNumber = teacherTableViewContestNumber;
    }

    public void incrementCompetition() {
        this.teacherTableViewCompetitionNumber++;
    }

    public void incrementContest() {
        this.teacherTableViewContestNumber++;
    }

    public Integer getTeacherTableViewId() {
        return teacherTableViewId;
    }

}
