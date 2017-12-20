package Controllers.Admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller that holds up Admin's Main Menu Scene, with 3 buttons: Teacher's Settings, Settings and Quit.
 */
public class AdminMainMenuController {
    @FXML
    public Button teacherButton;

    private Stage stage;

    /**
     * Switches to Teacher's Settings scene
     */
    public void toTeacherSettings(){
        stage = (Stage) teacherButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/adminTeacherSettings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /**
     * Switches to Settings scene
     */
    public void toSettings(){
        stage = (Stage) teacherButton.getScene().getWindow();
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
