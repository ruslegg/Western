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


public class LoseMenuController implements Initializable {
    private Stage stage;
    @FXML
    public Button restartButton;
    @FXML
    public Button menuButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restartButton.getStyleClass().add("quiz-id");
    }


    public void toQuiz() throws IOException {
        stage = (Stage) restartButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/quiz.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/quiz.css");
        stage.setScene(scene);
        System.out.println("ToQuiz");
       restartButton.getStyleClass().add("quiz-id");
    }


}