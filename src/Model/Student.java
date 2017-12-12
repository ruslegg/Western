package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

public class Student extends User implements Serializable {
    Team team;
    SchoolClass schoolClass;
    boolean isTeamLeader;

    public Student(Integer id, String name, String username, String password, Team team, SchoolClass schoolClass) {
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

    public void serializeTeamRequest() throws IOException {
        LoginController.teamRequests.add(this);
        File file = new File("src/data/requests/students.ser");
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.teamRequests);
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
    public void deleteTeamRequest() throws IOException, ClassNotFoundException {

        for (int i=0;i<LoginController.teamRequests.size();i++) {
            if (LoginController.teamRequests.get(i).getId() == id) {
                LoginController.teamRequests.remove(i);
            }
        }
        File file = new File("src/data/requests/students.ser");
        File file2 = new File("src/data/users/students.ser");
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.teamRequests);
        fileOut = new FileOutputStream(file2,false);
        out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.studentList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            LoginController.requestsList = (ArrayList) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        fileIn = new FileInputStream(file2);
        in = new ObjectInputStream(fileIn);
        LoginController.studentList = (ArrayList) in.readObject();
        in.close();
        fileIn.close();
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public boolean isTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(boolean teamLeader) {
        isTeamLeader = teamLeader;
    }
}