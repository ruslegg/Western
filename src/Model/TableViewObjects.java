package Model;

public class TableViewObjects {
    String teacherRequestName;
    String teacherTableViewId;
    String teacherTableViewName;
    Integer teacherTableViewCompetitionNumber;
    Integer teacherTableViewContestNumber;

    public TableViewObjects(String teacherRequestName) {
        this.teacherRequestName = teacherRequestName;
    }

    public TableViewObjects(String teacherTableViewId, String teacherTableViewName, int teacherTableViewCompetitionNumber, int teacherTableViewContestNumber) {
        this.teacherTableViewId = teacherTableViewId;
        this.teacherTableViewName = teacherTableViewName;
        this.teacherTableViewCompetitionNumber = teacherTableViewCompetitionNumber;
        this.teacherTableViewContestNumber = teacherTableViewContestNumber;
    }

    public String getTeacherRequestName() {
        return teacherRequestName;
    }

    public void setTeacherRequestName(String teacherRequestName) {
        this.teacherRequestName = teacherRequestName;
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

    public String getTeacherTableViewId() {
        return teacherTableViewId;
    }

    public void setTeacherTableViewId(String teacherTableViewId) {
        this.teacherTableViewId = teacherTableViewId;
    }
    public void incrementCompetition(){
        this.teacherTableViewCompetitionNumber++;
    }
    public void incrementContest(){
        this.teacherTableViewContestNumber++;
    }
}

