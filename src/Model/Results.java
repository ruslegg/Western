package Model;

import java.io.*;



public class Results implements Serializable{

    private int userID;
    private String type;
    private String subject;
    private int contestID;
    private int score;
    private double correctAnswerPercent;


    public Results(){
        this(0, "", "", 0, 0, 0.0);
    }

    public Results(int userID, String type, String subject, int contestID, int score, double correctAnswerPercent) {
        this.userID = userID;
        this.type = type;
        this.subject = subject;
        this.contestID = contestID;
        this.score = score;
        this.correctAnswerPercent = correctAnswerPercent;
    }



    public void addResult() throws IOException {
        try{
            FileWriter wr = new FileWriter("src/data/users/results.txt",true);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(String.valueOf(userID));
            bw.write(" ");
            bw.write(type);
            bw.write(" ");
            bw.write(subject);
            bw.write(" ");
            bw.write(String.valueOf(contestID));
            bw.write(" ");
            bw.write(String.valueOf(score));
            bw.write(" ");
            bw.write(String.valueOf(correctAnswerPercent));
            bw.newLine();

            if(bw != null)
                bw.close();
        }
        catch (IOException e) {
            System.err.println("Error opening file. Terminating.");}

    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

















}







