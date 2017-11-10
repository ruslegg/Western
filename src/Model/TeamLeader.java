package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

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

    @Override
    public void serialize() throws IOException {
        Writer wr=new FileWriter("src/data/users/teamleader.txt", true);
        BufferedWriter bw=new BufferedWriter(wr);
        bw.write(name);
        bw.write(" ");
        bw.write(classNumber);
        bw.write(" ");
        bw.write(team);
        bw.write(" ");
        bw.write(String.valueOf(isTeamLeader));

        bw.newLine();
        bw.close();
    }

    public boolean isTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(boolean teamLeader) {
        isTeamLeader = teamLeader;
    }
}
