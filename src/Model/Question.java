package Model;


import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

public class Question implements Serializable {
    String question,answer1,answer2,answer3,answer4,correctAnswer,subject,fromDate,toDate;
    int quizID,teacherID,quizType;
    ArrayList<SchoolClass> classList = new ArrayList();

    public Question(int quizID, int teacherID, int quizType, ArrayList<SchoolClass> classList, String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, String subject, String fromDate, String toDate) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.subject = subject;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.quizID = quizID;
        this.teacherID = teacherID;
        this.quizType = quizType;
        this.classList = classList;
        try {
            serialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Question(int quizID, int teacherID, int quizType, ArrayList<SchoolClass> classList,String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, String subject) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.subject = subject;
        this.quizID = quizID;
        this.teacherID = teacherID;
        this.quizType = quizType;
        this.classList = classList;
        try {
            serialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void serialize() throws IOException, ClassNotFoundException {
        LoginController.questionsList.add(this);
        File file = new File("src/data/attributes/questions.ser");
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.questionsList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.questionsList=(ArrayList) in.readObject();
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getQuizType() {
        return quizType;
    }

    public void setQuizType(int quizType) {
        this.quizType = quizType;
    }

    public ArrayList<SchoolClass> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<SchoolClass> classList) {
        this.classList = classList;
    }
}
