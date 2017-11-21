package Model;

import java.io.*;

public class User implements Serializable {
    String name;
    String team;
    String classNumber;

    public User() {

    }

    public User(String name, String team, String classNumber) {
        this.name = name;
        this.team = team;
        this.classNumber = classNumber;
    }


    public void serialize() throws IOException {
        Writer wr = new FileWriter("src/data/users/user.txt",true);
        BufferedWriter bw = new BufferedWriter(wr);
        bw.write(name);
        bw.write(" ");
        bw.write(classNumber);
        bw.write(" ");
        bw.write(team);
        bw.newLine();
        bw.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
