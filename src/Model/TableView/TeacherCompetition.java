package Model.TableView;

public class TeacherCompetition {
    String competitionName;
    int competitionId;
    int competitionNumber=0;

    public TeacherCompetition(int competitionId,String competitionName) {
        this.competitionName = competitionName;
        this.competitionId = competitionId;
    }

    public void increment(){
        competitionNumber++;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getCompetitionNumber() {
        return competitionNumber;
    }

    public void setCompetitionNumber(int competitionNumber) {
        this.competitionNumber = competitionNumber;
    }
}
