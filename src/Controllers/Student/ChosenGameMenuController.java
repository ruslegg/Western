package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Results;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller that manipulates the chosen game functionality, whether to play the chosen game or watch the chosen competition/
 * contest's Leaderboard
 */
public class ChosenGameMenuController implements Initializable {

    private Stage stage;
    @FXML
    private Button playButton, backButton;

    private static int quizType;
    private static int quizId;
    private static String quizName;
    private static int teacherId;

    /**
     * Disables the playButton if the student has already played the game, initially performing a check through all results
     * using a for-each loop.
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Results results : LoginController.getResultsList()
                ) {
            if (results.getType() == quizType && results.getContestID() == quizId && results.getUserID() == LoginController.getStudent().getId()) {
                playButton.setDisable(true);
                break;
            }
        }
    }

    /**
     * Switches back to School Games Scene
     */
    public void toSchoolGames() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/school-games.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /**
     * If the button is active, switches to Game Controller.
     */
    public void playGame(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/game.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/game.css");
        stage.setScene(scene);
    }

    /**
     * Switches to chosen game's Leaderboard
     */
    public void toSubjectLeaderBoard() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/subjectLeaderBoard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }


    public static String getQuizName() {
        return quizName;
    }

    public static void setQuizName(String quizName) {
        ChosenGameMenuController.quizName = quizName;
    }

    public static int getQuizType() {
        return quizType;
    }

    public static void setQuizType(int quizType) {
        ChosenGameMenuController.quizType = quizType;
    }

    public static int getQuizId() {
        return quizId;
    }

    public static void setQuizId(int quizId) {
        ChosenGameMenuController.quizId = quizId;
    }

    public static int getTeacherId() {
        return teacherId;
    }

    public static void setTeacherId(int teacherId) {
        ChosenGameMenuController.teacherId = teacherId;
    }
}
