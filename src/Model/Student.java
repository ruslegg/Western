package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

/**
 * Student object that extends from User Object
 */
public class Student extends User implements Serializable {
    private Team team;
    private SchoolClass schoolClass;
    private boolean isTeamLeader;

    public Student(Integer id, String name, String username, String password, Team team, SchoolClass schoolClass) {
        super(id, name, username, password);
        this.team = team;
        this.schoolClass = schoolClass;
    }

    public Student() {
    }

    /**
     * Serialize student to StudentList
     * @throws IOException - caused by serialization
     */
    @Override
    public void serialize() throws IOException {
        LoginController.getStudentList().add(this); //Add student to existing list
        File file = new File("src/data/users/students.ser"); //Create file directory
        FileOutputStream fileOut = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getStudentList()); //Serialize existing list to file
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            LoginController.setStudentList((ArrayList) in.readObject()); //
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        fileIn.close();
    }

    /**
     * Serialize student's team request
     * @throws IOException - caused by serialization
     */
    public void serializeTeamRequest() throws IOException {
        LoginController.getTeamRequests().add(this);
        File file = new File("src/data/requests/students.ser");
        FileOutputStream fileOut = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getTeamRequests());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            LoginController.setTeamRequests((ArrayList) in.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        fileIn.close();
    }

    /**
     * Serialize student's new team creation
     * @throws IOException - caused by serialization
     */
    public void createTeamSerialize() throws IOException {
        File file = new File("src/data/users/students.ser");
        FileOutputStream fileOut = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getStudentList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            LoginController.setStudentList((ArrayList) in.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        fileIn.close();
    }

    /**
     * Serialize deleting a team request from list
     * @throws IOException - caused by serialization
     * @throws ClassNotFoundException - caused by serialization
     */
    public void deleteTeamRequest() throws IOException, ClassNotFoundException {

        for (int i = 0; i < LoginController.getTeamRequests().size(); i++) {
            if (LoginController.getTeamRequests().get(i).getId() == getId()) {
                LoginController.getTeamRequests().remove(i);
            }
        }
        File file = new File("src/data/requests/students.ser");
        File file2 = new File("src/data/users/students.ser");
        FileOutputStream fileOut = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getTeamRequests());
        fileOut = new FileOutputStream(file2, false);
        out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getStudentList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            LoginController.setTeamRequests((ArrayList) in.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        fileIn = new FileInputStream(file2);
        in = new ObjectInputStream(fileIn);
        LoginController.setStudentList((ArrayList) in.readObject());
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

    public boolean isTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(boolean teamLeader) {
        isTeamLeader = teamLeader;
    }
}