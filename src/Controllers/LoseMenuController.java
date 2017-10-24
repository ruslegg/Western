package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoseMenuController{
    private Stage stage;
    @FXML
    public Button restartButton;
    @FXML
    public Button menuButton;

    public void toGame() throws IOException {
        stage = (Stage) restartButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/game.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/game.css");
        stage.setScene(scene);
    }
    public void toMainMenu() throws IOException{
        stage = (Stage) menuButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/mainMenu.css");
        stage.setScene(scene);
    }


}