package Controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
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
    public Button leaderBoardButton,restartButton,mainMenuButton;
    public static double correctAnswers=0;
    public static double timePassed=0;
    public static double numberOfQuestions=0;






    public void toLeaderBoard(MouseEvent event) throws IOException{
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) leaderBoardButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/leaderboards.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        answerRateLabel.setText(String.valueOf((double) Math.round(((correctAnswers*100)/numberOfQuestions) * 100d) / 100d)+"%");
    }

    public void restart(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) restartButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/game.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/game.css");
        stage.setScene(scene);

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
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }
}