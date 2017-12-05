package Model.TableView;

public class AdminStatistics {
    String teacherTableViewId;
    String teacherTableViewName;
    Integer teacherTableViewCompetitionNumber;
    Integer teacherTableViewContestNumber;


    public AdminStatistics(String teacherTableViewId, String teacherTableViewName, Integer teacherTableViewCompetitionNumber, Integer teacherTableViewContestNumber) {
        this.teacherTableViewId = teacherTableViewId;
        this.teacherTableViewName = teacherTableViewName;
        this.teacherTableViewCompetitionNumber = teacherTableViewCompetitionNumber;
        this.teacherTableViewContestNumber = teacherTableViewContestNumber;
    }

    public void incrementCompetition(){
        this.teacherTableViewCompetitionNumber++;
    }
    public void incrementContest(){
        this.teacherTableViewContestNumber++;
    }

    public String getTeacherTableViewId() {
        return teacherTableViewId;
    }

    public void setTeacherTableViewId(String teacherTableViewId) {
        this.teacherTableViewId = teacherTableViewId;
    }

    public String getTeacherTableViewName() {
        return teacherTableViewName;
    }

    public void setTeacherTableViewName(String teacherTableViewName) {
        this.teacherTableViewName = teacherTableViewName;
    }

    public Integer getTeacherTableViewCompetitionNumber() {
        return teacherTableViewCompetitionNumber;
    }

    public void setTeacherTableViewCompetitionNumber(Integer teacherTableViewCompetitionNumber) {
        this.teacherTableViewCompetitionNumber = teacherTableViewCompetitionNumber;
    }

    public Integer getTeacherTableViewContestNumber() {
        return teacherTableViewContestNumber;
    }

    public void setTeacherTableViewContestNumber(Integer teacherTableViewContestNumber) {
        this.teacherTableViewContestNumber = teacherTableViewContestNumber;
    }
}
