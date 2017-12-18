package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;


public class Results implements Serializable{

    private int userID;
    private String name;
    private String teamAbbreviation;
    private String teamName;
    private int teacherID;
    private int type;
    private int contestID;
    private int score;
    private double correctAnswerPercent;
    private String contestName;
    private SchoolClass schoolClass;
    private int rank;
    public Results(){

    }
    public Results(String name){
        this.name=name;
    }


    public Results(int userID, String name, String teamAbbreviation, String teamName, int teacherID, int type, int contestID, String contestName, int score, double correctAnswerPercent, SchoolClass schoolClass) {
        this.userID = userID;
        this.name = name;
        this.teamAbbreviation = teamAbbreviation;
        this.teamName = teamName;
        this.teacherID = teacherID;
        this.type = type;
        this.contestID = contestID;
        this.score = score;
        this.correctAnswerPercent = correctAnswerPercent;
        this.contestName = contestName;
        this.schoolClass = schoolClass;
        try {
            serialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Results(int contestID, String contestName, String name, int score, int rank) {
        this.name = name;
        this.contestID = contestID;
        this.contestName = contestName;
        this.score = score;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void serialize() throws IOException, ClassNotFoundException {
        LoginController.resultsList.add(this);
        File file = new File("src/data/attributes/results.ser");
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.resultsList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.resultsList = (ArrayList) in.readObject();
        in.close();
        fileIn.close();
    }
    public static void refresh() throws IOException, ClassNotFoundException {
        File file = new File("src/data/attributes/results.ser");
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.resultsList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.resultsList = (ArrayList) in.readObject();
        in.close();
        fileIn.close();
    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTeamAbbreviation() {
        return teamAbbreviation;
    }

    public void setTeamAbbreviation(String teamAbbreviation) {
        this.teamAbbreviation = teamAbbreviation;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getContestID() {
        return contestID;
    }

    public void setContestID(int contestID) {
        this.contestID = contestID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getCorrectAnswerPercent() {
        return correctAnswerPercent;
    }

    public void setCorrectAnswerPercent(double correctAnswerPercent) {
        this.correctAnswerPercent = correctAnswerPercent;
    }

    public  String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}







