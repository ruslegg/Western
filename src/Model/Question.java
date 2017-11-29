package Model;

import javafx.scene.control.DatePicker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Question {
    String quizType,quizID,question,answer1,answer2,answer3,answer4,correctAnswer,subject,classList,fromDate,toDate;

    public Question(String quizType, String quizID, String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, String subject, String classList) {
        this.quizType = quizType;
        this.quizID = quizID;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.subject = subject;
        this.classList = classList;
    }

    public Question(String quizType, String quizID, String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, String subject, String classList, String fromDate, String toDate) {
        this.quizType = quizType;
        this.quizID = quizID;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.subject = subject;
        this.classList = classList;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Question(){

    }
    public void serialize() throws IOException {
        Writer wr = new FileWriter("src/data/attributes/questions.txt",true);
        BufferedWriter bw = new BufferedWriter(wr);
        bw.write(quizType);
        bw.write(" ");
        bw.write(quizID);
        bw.write(" ");
        bw.write(question);
        bw.write(" ");
        bw.write(answer1);
        bw.write(" ");
        bw.write(answer2);
        bw.write(" ");
        bw.write(answer3);
        bw.write(" ");
        bw.write(answer4);
        bw.write(" ");
        bw.write(correctAnswer);
        bw.write(" ");
        bw.write(subject);
        bw.write(" ");
        bw.write(classList);
        if (fromDate!=null){
            bw.write(" ");
            bw.write(fromDate);
            bw.write(" ");
            bw.write(toDate);
        }
        bw.newLine();
        bw.close();
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
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

    public String getClassList() {
        return classList;
    }

    public void setClassList(String classList) {
        this.classList = classList;
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
}
