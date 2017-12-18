package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Results;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class QuizFinishedController implements Initializable  {
    private Stage stage;
    @FXML
    public Label congratulationLabel;
    @FXML
    public Label scoreLabel;
    @FXML
    public Label answerRateLabel;
    @FXML
    public Button mainMenuButton;
    public static int correctAnswers=0;
    public static double numberOfQuestions=0;
    public double correctAnswerPercentage;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        correctAnswerPercentage = Math.round(((correctAnswers * 100) / numberOfQuestions) * 100d) / 100d;
        answerRateLabel.setText(String.valueOf(correctAnswerPercentage) + "%");
        if (!GameController.isRandom){
            Results results = new Results(LoginController.student.getId(),LoginController.student.getName(),LoginController.student.getTeam().getAbbreviation(),
                    LoginController.student.getTeam().getName(),ChosenGameMenuController.getTeacherId(),ChosenGameMenuController.getQuizType(),
                    ChosenGameMenuController.getQuizId(),ChosenGameMenuController.getQuizName(),correctAnswers*20,correctAnswerPercentage,LoginController.student.getSchoolClass());
        }
    }


    public void toMainMenu(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) mainMenuButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
}