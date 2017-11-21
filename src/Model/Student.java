package Model;

import java.io.*;

public class Student extends User {
    String name;
    String team;
    String classNumber;
    String score;


    public Student() {

    }

    public Student(String name, String team, String classNumber, String score) {
        this.name = name;
        this.team = team;
        this.classNumber = classNumber;
        this.score = score;
    }

    @Override
    public void serialize() throws IOException {
        Writer wr = new FileWriter("src/data/users/student.txt", true);
        BufferedWriter bw = new BufferedWriter(wr);
        bw.write(name);
        bw.write(" ");
        bw.write(classNumber);
        bw.write(" ");
        bw.write(team);
        bw.newLine();
        bw.close();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


    public String getTeam() {
        return team;
    }

    public void setTeam(String team){
        this.team = team;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }


}