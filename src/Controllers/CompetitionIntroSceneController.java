package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CompetitionIntroSceneController{
    private Stage stage;
    @FXML
    public Button startButton;
    @FXML
    public Button leaderboardButton;
    @FXML
    public Button backButton;



    public void toStart(MouseEvent event) throws IOException {
        stage = (Stage) startButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/game.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/game.css");
        stage.setScene(scene);
    }
    public void toLeaderboard(MouseEvent event) throws  IOException{
        stage = (Stage) leaderboardButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/leaderboard.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/leaderboard.css");
        stage.setScene(scene);

    }
    public void toBack(MouseEvent event) throws IOException{
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/subjectList.fxml"));
        Scene scene = new Scene(root,1280,1080);
        scene.getStylesheets().add("/css/subjectList.css");
        stage.setScene(scene);
    }
}