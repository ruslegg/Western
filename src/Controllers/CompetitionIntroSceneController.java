package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class CompetitionIntroSceneController{
    private Stage stage;
    @FXML
    public Button competitionButton;
    @FXML
    public Button leaderboardButton;
    @FXML
    public Button backButton;



    public void toStart(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) competitionButton.getScene().getWindow();
        Button root;
        root = FXMLLoader.load(getClass().getResource("/FXML/gameIntro.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }
    public void toLeaderboard(MouseEvent event) throws  IOException{
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) leaderboardButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/leaderboards.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);

    }
    public void toBack(MouseEvent event) throws IOException{
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/subjectList.fxml"));
        Scene scene = new Scene(root,1280,1080);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }
}