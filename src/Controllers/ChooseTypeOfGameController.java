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
        stage = (Stage) competitionButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/subjectlist.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/subjectlist.css");
        stage.setScene(scene);
        System.out.println("Competition");

    }

    public void toNormal(MouseEvent event) throws IOException {
        stage = (Stage) normalGameButton.getScene().getWindow();
        Button root;
        root = FXMLLoader.load(getClass().getResource("/FXML/gameIntro.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("Normal");
    }

    public void toMainMenu(MouseEvent event) throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root, 1280, 1080);
        scene.getStylesheets().add("/css/mainMenu.css");
        stage.setScene(scene);
        System.out.println("Back");
    }

}
