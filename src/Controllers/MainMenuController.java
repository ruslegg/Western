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


public class MainMenuController implements Initializable {
    private Stage stage;
    @FXML
    public Button startButton;
    @FXML
    public Button settingsButton;
    @FXML
    public Button statisticsButton;
    @FXML
    public Button quitButton;
    @Override

    public void initialize(URL location, ResourceBundle resources) {

        startButton.getStyleClass().add("quiz-id");

    }

    public void toTypeOfGame() throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) startButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/chooseTypeOfGame.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }

    public void toSettings() throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) settingsButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/settings.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }

    public void toStatistics() throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) statisticsButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/statistics.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }

    public void quit(){
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        System.exit(0);
    }

}
