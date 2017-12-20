package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

/**
 * Results Object that is associated with Students
 */

public class Results implements Serializable {

    private int userID, teacherID, type, contestID, score, rank;
    private String name, teamAbbreviation, teamName, contestName;
    private double correctAnswerPercent;
    private SchoolClass schoolClass;

    public Results() {

    }

    public Results(String name) {
        this.name = name;
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

    /**
     * Serializing results to a file
     * @throws IOException caused by serialization
     * @throws ClassNotFoundException caused by serialization
     */
    public void serialize() throws IOException, ClassNotFoundException {
        LoginController.getResultsList().add(this);
        File file = new File("src/data/attributes/results.ser");
        FileOutputStream fileOut = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getResultsList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.setResultsList((ArrayList) in.readObject());
        in.close();
        fileIn.close();
    }

    /**
     * Refresh Lists
     * @throws IOException caused by serialization
     * @throws ClassNotFoundException caused by serialization
     */
    public static void refresh() throws IOException, ClassNotFoundException {
        File file = new File("src/data/attributes/results.ser");
        FileOutputStream fileOut = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getResultsList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.setResultsList((ArrayList) in.readObject());
        in.close();
        fileIn.close();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setTeamAbbreviation(String teamAbbreviation) {
        this.teamAbbreviation = teamAbbreviation;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public int getType() {
        return type;
    }

    public int getContestID() {
        return contestID;
    }

    public int getScore() {
        return score;
    }

    public double getCorrectAnswerPercent() {
        return correctAnswerPercent;
    }

    public String getContestName() {
        return contestName;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
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







