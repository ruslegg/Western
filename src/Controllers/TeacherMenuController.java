package Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherMenuController implements Initializable {
    private Stage stage;

    @FXML
    public Button createButton;
    @FXML
    public Button settingsButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button statisticsButton;
    @FXML
    public Button backButton;


    public void initialize(URL location, ResourceBundle resources) {

    }


    public void toCreateMenu() throws IOException{
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }

        stage = (Stage) createButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/createOptionsTeacher.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }

    public void toStatistics(MouseEvent event) throws IOException, InterruptedException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) statisticsButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/statisticsTeacher.fxml"));
        root.getStyleClass().add("scene-background");
        Scene scene = new Scene(root, 1280, 720);

        stage.setScene(scene);


    }

    public void toSettings() throws IOException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }

        stage = (Stage) settingsButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/settings.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }

    public void toMainMenu(MouseEvent event) throws IOException, InterruptedException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/teacherMenu.fxml"));
        root.getStyleClass().add("scene-background");
        Scene scene = new Scene(root, 1280, 720);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
        System.out.println("Back");

    }


    public void quit(){
        System.exit(0);
    }
}

