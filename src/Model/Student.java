package Model;

import java.io.*;

public class Student extends User {
    String team;
    String schoolClass;


    public Student() {


    }

    public Student(String id, String name, String username, String password, String schoolClass,String team) {
        super(id, name, username, password);
        this.team = team;
        this.schoolClass = schoolClass;
    }

    @Override
    public void serialize() throws IOException {
        Writer wr = new FileWriter("src/data/users/students.txt",true);
        BufferedWriter bw = new BufferedWriter(wr);
        bw.write(id);
        bw.write(" ");
        bw.write(name);
        bw.write(" ");
        bw.write(username);
        bw.write(" ");
        bw.write(password);
        bw.write(" ");
        bw.write(schoolClass);
        bw.write(" ");
        bw.write(team);
        bw.write(" ");
        bw.newLine();
        bw.close();
    }



}