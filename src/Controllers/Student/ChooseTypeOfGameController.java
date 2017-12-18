package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseTypeOfGameController implements Initializable {
    private Stage stage;
    @FXML
    public Button competitionButton;
    @FXML
    public Button normalGameButton;
    @FXML
    public Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void toCompetition() throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) competitionButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/school-games.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
        System.out.println("Competition");

    }

    public void toRandom(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) normalGameButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/randomQuestionsOptions.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /*public void toMainMenu(MouseEvent event) throws IOException, InterruptedException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        //root.getStyleClass().add("scene-background");
        Scene scene = new Scene(root);
        //Scene scene = new Scene(root, 1280, 720);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
        System.out.println("Back");
    }*/

    public void toMainMenu(MouseEvent event) throws IOException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
}
