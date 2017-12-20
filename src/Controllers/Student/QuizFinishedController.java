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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This controller performs an informative presentation to the scene, as how many points the student gathered or the percentage
 * of correct answers from a finished game.
 */
public class QuizFinishedController implements Initializable {
    private Stage stage;
    @FXML
    public Label congratulationLabel;
    public Label scoreLabel;
    public Label answerRateLabel;
    public Button mainMenuButton;

    private double correctAnswerPercentage;
    private static int correctAnswers = 0;
    private static double numberOfQuestions = 0;

    /**
     * Performs a calculation and edits the labels in order to present the student about his results
     * After labels are set, a check is performed whether is a competition/contest game or a "Normal" game, in order
     * to register the result or not.
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        congratulationLabel.getStyleClass().add("congratulation");
        correctAnswerPercentage = Math.round(((correctAnswers * 100) / numberOfQuestions) * 100d) / 100d;
        answerRateLabel.setText(String.valueOf(correctAnswerPercentage) + "%");
        scoreLabel.setText(String.valueOf(correctAnswers * 20));
        if (!GameController.isRandom()) {
            Results results = new Results(LoginController.getStudent().getId(), LoginController.getStudent().getName(), LoginController.getStudent().getTeam().getAbbreviation(),
                    LoginController.getStudent().getTeam().getName(), ChosenGameMenuController.getTeacherId(), ChosenGameMenuController.getQuizType(),
                    ChosenGameMenuController.getQuizId(), ChosenGameMenuController.getQuizName(), correctAnswers * 20, correctAnswerPercentage, LoginController.getStudent().getSchoolClass());
        }
    }

    /**
     * Switches to Student's Main Menu
     */
    public void toMainMenu(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) mainMenuButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }


    public static void setCorrectAnswers(int correctAnswers) {
        QuizFinishedController.correctAnswers = correctAnswers;
    }

    public static void setNumberOfQuestions(double numberOfQuestions) {
        QuizFinishedController.numberOfQuestions = numberOfQuestions;
    }
}