package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Question;
import Model.SchoolClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;


public class GameController implements Initializable {
    private Stage stage;
    @FXML
    Button answerButton1,answerButton2,answerButton3,answerButton4,exitButton;
    @FXML
    Label questionNumberLabel,questionLabel;
    @FXML
    ImageView questionPersonImage;
    Question question;
    ObservableList<Question> questions = FXCollections.observableArrayList();
    int questionNumber = 0;
    int correctAnswers = 0;
    public static boolean isRandom = false;
    public static int randomNumber = 0;



    public void newQuestion() throws IOException {
        if(questionNumber==questions.size()){
            QuizFinishedController.numberOfQuestions=questions.size();
            QuizFinishedController.correctAnswers=correctAnswers;
            results();
        }
        else {
            question = questions.get(questionNumber);
            questionNumberLabel.setText("Question number: " + String.valueOf(questionNumber + 1));
            questionLabel.setText("Question: " + question.getQuestion());
            answerButton1.setText(question.getAnswer1());
            answerButton2.setText(question.getAnswer2());
            answerButton3.setText(question.getAnswer3());
            answerButton4.setText(question.getAnswer4());
        }
    }
    public void exit() throws  IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) exitButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/mainMenu.assets.css");
        stage.setScene(scene);
    }
    public void checkAnswer(String answer) throws IOException, SQLException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        if (answer.equals(question.getCorrectAnswer())){
            questionNumber++;
            correctAnswers++;
            newQuestion();
            System.out.println("correct");
        }
        else{
            questionNumber++;
            newQuestion();
            System.out.println("incorrect");
        }
    }
    public void results() throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
            stage = (Stage) exitButton.getScene().getWindow();
            Pane root;
            root = FXMLLoader.load(getClass().getResource("/FXML/quizFinished.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/assets/css/quizfinished.assets.css");
            stage.setScene(scene);
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionNumber = 0;
        getQuestions();
        try {
            newQuestion();
        } catch (IOException e) {
            e.printStackTrace();
        }
        answerButton1.setOnMouseClicked(event -> {
            try {
                checkAnswer(answerButton1.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        answerButton2.setOnMouseClicked(event -> {
            try {
                checkAnswer(answerButton2.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        answerButton3.setOnMouseClicked(event -> {
            try {
                checkAnswer(answerButton3.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        answerButton4.setOnMouseClicked(event -> {
            try {
                checkAnswer(answerButton4.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    public void getQuestions() {
        if (isRandom){
            Collections.shuffle(RandomQuestionsOptionsController.questions);
            for (int i=0;i<randomNumber;i++){
                questions.add(RandomQuestionsOptionsController.questions.get(i));
            }
        }
        else {
            for (Question question : LoginController.questionsList
                    ) {
                if (question.getQuizType() == ChosenGameMenuController.getQuizType() && question.getQuizID() == ChosenGameMenuController.getQuizId()) {
                    questions.add(question);
                }
            }
        }
    }
}


