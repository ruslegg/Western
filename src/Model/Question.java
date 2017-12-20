package Model;


import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

/**
 * Question Object that is used in Game
 */
public class Question implements Serializable {

    private String question, answer1, answer2, answer3, answer4, correctAnswer, subject, fromDate, toDate;
    private int quizID, teacherID, quizType;
    private ArrayList<SchoolClass> classList = new ArrayList();

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

    public Question(int quizID, int teacherID, int quizType, ArrayList<SchoolClass> classList, String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, String subject) {
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

    /**
     * Serialize question to file and refreshes current question list
     * @throws IOException caused by serialization
     * @throws ClassNotFoundException cause by serialization
     */
    public void serialize() throws IOException, ClassNotFoundException {
        LoginController.getQuestionsList().add(this);
        File file = new File("src/data/attributes/questions.ser");
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getQuestionsList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.setQuestionsList((ArrayList) in.readObject());
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getSubject() {
        return subject;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public int getQuizID() {
        return quizID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public int getQuizType() {
        return quizType;
    }

    public ArrayList<SchoolClass> getClassList() {
        return classList;
    }
}
