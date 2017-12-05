package Model;

import java.io.*;



public class Results implements Serializable{

    private int userID;
    private String name;
    private String teamAbreviation;
    private int teacherID;
    private int type;
    private int contestID;
    private int score;
    private double correctAnswerPercent;

    public Results(){

    }
    public Results(String name){
        this.name=name;
    }


    public Results(int userID, String name, String teamAbreviation, int teacherID, int type, int contestID, int score, double correctAnswerPercent) {
        this.userID = userID;
        this.name = name;
        this.teamAbreviation = teamAbreviation;
        this.teacherID = teacherID;
        this.type = type;
        this.contestID = contestID;
        this.score = score;
        this.correctAnswerPercent = correctAnswerPercent;
    }

    public void addResult() throws IOException {
        try{
            FileWriter wr = new FileWriter("src/data/attributes/results.txt",true);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(String.valueOf(userID));
            bw.write(" ");
            bw.write(name);
            bw.write(" ");
            bw.write(teamAbreviation);
            bw.write(" ");
            bw.write(String.valueOf(teacherID));
            bw.write(" ");
            bw.write(String.valueOf(type));
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}







