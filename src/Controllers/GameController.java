package Controllers;

import Model.Question;
import includes.MYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class GameController implements Initializable {
    Stage stage;
    @FXML
    Button answerButton1,answerButton2,answerButton3,answerButton4,doorButton,exitButton;
    @FXML
    Label answerLabel1,answerLabel2,answerLabel3,answerLabel4,questionNumberLabel,clockLabel,questionLabel;
    @FXML
    ImageView questionPersonImage;
    ObservableList<Question> questions = FXCollections.observableArrayList();
    int questionNumber = 0;
    int correctAnswers = 0;
    Question question = new Question();



    public static String fieldString = "";

    public void newQuestion() throws SQLException, IOException {
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
    public void dead() throws IOException {
        stage = (Stage) answerButton1.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/loseMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/loseMenu.css");
        stage.setScene(scene);
    }
    public void exit() throws  IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) exitButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
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
        root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/css/menu.css");
            stage.setScene(scene);
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        answerButton1.getStyleClass().add("window-button");
        answerButton1.getStylesheets().add("/css/menu.css");

        answerButton2.getStyleClass().add("window-button");
        answerButton2.getStylesheets().add("/css/menu.css");

        answerButton3.getStyleClass().add("window-button");
        answerButton3.getStylesheets().add("/css/menu.css");

        answerButton4.getStyleClass().add("window-button");
        answerButton4.getStylesheets().add("/css/menu.css");


        questions = FXCollections.observableArrayList();
        questionNumber=0;
        correctAnswers=0;
        question=new Question();
        try {
            getQuestions();
            newQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        answerButton1.setOnMouseClicked(event -> {
            try {
                ;
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
    public void getQuestions() throws SQLException {
        Connection connHandle = MYSQL.getConnection();
        PreparedStatement checkUserQuery = connHandle.prepareStatement("SELECT * FROM `questions` WHERE `field` = '"+fieldString+"'");
        ResultSet rs = checkUserQuery.executeQuery();
        while (rs.next()){
            String question = rs.getString("question");
            String answer1 = rs.getString("answer1");
            String answer2 = rs.getString("answer2");
            String answer3 = rs.getString("answer3");
            String answer4 = rs.getString("answer4");
            String field = rs.getString("field");
            String correctAnswer = rs.getString("correctAnswer");
            Question questionObject = new Question(question,answer1,answer2,answer3,answer4,field,correctAnswer);
            questions.add(questionObject);
        }
    }
}

