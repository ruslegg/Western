package Controllers.Teacher;

import Controllers.LoginController;
import Controllers.SettingsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller that holds the scene with Teacher's Main Menu, with 4 buttons: Create, Statistics, Settings and Quit.
 */
public class TeacherMenuController {
    @FXML
    public Button createButton, settingsButton, exitButton, statisticsButton, backButton;
    private Stage stage;

    /**
     * Switches to Teacher's Create Options Scene
     */
    public void toCreateMenu(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }

        stage = (Stage) createButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/createOptionsTeacher.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
    /**
     * Switches to Teacher's Statistics Scene
     */
    public void toStatistics() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) statisticsButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/statisticsTeacher.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getStyleClass().add("scene-background");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/menu.css");


        stage.setScene(scene);


    }
    /**
     * Switches to Settings Scene
     */
    public void toSettings() {
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
     * Exit the game
     */
    public void quit() {
        System.exit(0);
    }
}

