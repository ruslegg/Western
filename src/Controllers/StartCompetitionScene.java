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

public class StartCompetitionScene implements Initializable {
    private Stage stage;
    @FXML
    public Button startCompetitionButton;
    @FXML
    public Button leaderBoardButton;
    @FXML
    public Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void toGame() throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) startCompetitionButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/game.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/game.css");
        stage.setScene(scene);
        System.out.println(" Start Competition");

    }

    public void toLeaderBoards(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) leaderBoardButton.getScene().getWindow();
        Button root;
        root = FXMLLoader.load(getClass().getResource("/FXML/leadboards.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("LeaderBoard");
    }

    public void toSubjectList(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/subjectlist.fxml"));
        Scene scene = new Scene(root, 1280, 1080);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
        System.out.println("Back");
    }

}

