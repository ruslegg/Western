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


public class CompetitionIntroSceneController implements Initializable {
    private Stage stage;
    @FXML
    public Button startButton;
    @FXML
    public Button leaderboardButton;
    @FXML
    public Button backButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void toStart(MouseEvent event) throws IOException {
        stage = (Stage) startButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/start.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/start.css");
        stage.setScene(scene);
        System.out.println("ToStart");
    }
    public void toLeaderboard(MouseEvent event) throws  IOException{
        stage = (Stage) leaderboardButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/leaderboard.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/leaderboard.css");
        stage.setScene(scene);
        System.out.println("ToLeaderboard");

    }
    public void toBack(MouseEvent event) throws IOException{
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/back.fxml"));
        Scene scene = new Scene(root,1000,1000);
        scene.getStylesheets().add("/css/generate.css");
        stage.setScene(scene);
        System.out.println("ToBack");
    }
}