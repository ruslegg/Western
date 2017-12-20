package Controllers.Teacher;

import Controllers.LoginController;
import Controllers.SettingsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller that manipulates the Teacher Create Options Scene with 3 buttons
 */
public class CreateOptionsTeachersController {
    @FXML
    public Button gameButton, backButton, classButton;
    private Stage stage;

    /**
     * Switch to Generate Quiz Scene
     */
    public void toGame() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }

        stage = (Stage) gameButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/generateQuiz.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/quiz.css");
        stage.setScene(scene);
    }

    /**
     * Switch to Teacher Main Menu Scene
     */
    public void toMainMenu() {
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/teacherMainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
        System.out.println("Back");
    }

    /**
     * Switch to Creating new School Class Scene
     */
    public void toAddClass() {
        stage = (Stage) classButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/createClass.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/menu.css");
        root.getStyleClass().add("scene-background");
        stage.setScene(scene);


    }

}
