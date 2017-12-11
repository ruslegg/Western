package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

public class Student extends User implements Serializable {
    String team;
    String schoolClass;

    public Student(Integer id, String name, String username, String password, String schoolClass,String team) {
        super(id, name, username, password);
        this.team = team;
        this.schoolClass = schoolClass;
    }

    public Student() {
    }

    @Override
    public void serialize() throws IOException {
        LoginController.studentList.add(this);
        File file = new File("src/data/users/students.ser");
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.studentList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            LoginController.studentList = (ArrayList) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        fileIn.close();
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}