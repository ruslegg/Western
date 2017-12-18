package Controllers.Teacher;

import Controllers.LoginController;
import Controllers.SettingsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateOptionsTeachersController implements Initializable{
    private Stage stage;
    @FXML
    public Button gameButton;
    @FXML
    public Button backButton;
    @FXML
    public Button classButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }




    public void toGame() throws IOException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }

        stage = (Stage) gameButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/generateQuiz.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/quiz.css");
        stage.setScene(scene);
    }

    public void toMainMenu() throws IOException{
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/teacherMainMenu.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
        System.out.println("Back");
    }

    public void toAddClass() throws IOException{
        stage = (Stage) classButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/createClass.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/menu.css");
        root.getStyleClass().add("scene-background");
        stage.setScene(scene);


    }
}
