package Model;

public class TeamLeader extends User {
    boolean isTeamLeader = false;

    public TeamLeader(){

    }
    public TeamLeader(String name, String team,String classNumber, boolean isTeamLeader){
        this.name=name;
        this.team=team;
        this.classNumber=classNumber;
        this.isTeamLeader=isTeamLeader;
    }
}
