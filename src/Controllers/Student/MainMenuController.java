package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller that manipulates Student's Main Menu
 */
public class MainMenuController {
    private Stage stage;

    @FXML
    public Button startButton;
    public Button settingsButton;
    public Button statisticsButton;
    public Button quitButton, teamButton;

    /**
     * Switches to Type Of Game Scene, where the student can choose type of game that he wants to play.
     */
    public void toTypeOfGame(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) startButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/chooseTypeOfGame.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /**
     * Switches to Student's Team "Dashboard", initially performing a check whether he has a team or not. By choice
     * is assigned to corresponding scene
     */
    public void toTeam() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        if (LoginController.getStudent().getTeam().getName().length() > 1) {
            stage = (Stage) teamButton.getScene().getWindow();
            VBox root = new VBox();
            try {
                root = FXMLLoader.load(getClass().getResource("/View/userTeamInformation.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        } else {
            stage = (Stage) teamButton.getScene().getWindow();
            VBox root = new VBox();
            try {
                root = FXMLLoader.load(getClass().getResource("/View/userTeamLessOptions.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        }
    }

    /**
     * Switches to Settings Scene
     */
    public void toSettings(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) settingsButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /**
     * Switches to Student's Statistics
     */
    public void toStatistics() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) statisticsButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/statistics.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /**
     * Quits the game
     */
    public void quit() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        System.exit(0);
    }

}
