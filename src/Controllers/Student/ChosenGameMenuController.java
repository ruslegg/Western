package Controllers.Student;

import Controllers.LoginController;
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

public class ChosenGameMenuController implements Initializable {
    private Stage stage;
    private static int quizType;
    private static int quizId;
    private static String quizName;
    private static int teacherId;

    @FXML
    public Button playButton,leaderBoardButton,backButton;






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Results results: LoginController.resultsList
             ) {
            if (results.getType() == quizType && results.getContestID() == quizId && results.getUserID() == LoginController.student.getId()){
                playButton.setDisable(true);
                break;
            }
        }
    }

    public void toSchoolGames() throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/school-games.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
    public void playGame() throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/game.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/game.css");
        stage.setScene(scene);
    }
    public void toSubjectLeaderBoard() throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/subjectLeaderBoard.fxml"));
        Scene scene = new Scene(root);
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
