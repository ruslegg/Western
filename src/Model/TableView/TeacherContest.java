package Model.TableView;

public class TeacherContest {
    String contestName;
    int contestId;
    int contestNumber=0;

    public TeacherContest(int contestId,String contestName){
        this.contestName = contestName;
        this.contestId = contestId;
    }

    public void increment(){
        contestNumber++;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getContestNumber() {
        return contestNumber;
    }

    public void setContestNumber(int contestNumber) {
        this.contestNumber = contestNumber;
    }
}
