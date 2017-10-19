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


public class MainController implements Initializable {
    private Stage stage;
    @FXML
    public Button quizId;
    @FXML
    public Button lessonId;
    @FXML
    public Button generateId;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quizId.getStyleClass().add("quiz-id");
    }


    public void toQuiz() throws IOException {
        stage = (Stage) quizId.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/quiz.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/quiz.css");
        stage.setScene(scene);
        System.out.println("ToQuiz");
        quizId.getStyleClass().add("quiz-id");
    }
    public void toLesson(MouseEvent event) throws  IOException{
        stage = (Stage) lessonId.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/lesson.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/lesson.css");
        stage.setScene(scene);
        System.out.println("ToLesson");

    }
    public void toGenerate(MouseEvent event) throws IOException{
        stage = (Stage) generateId.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/generate.fxml"));
        Scene scene = new Scene(root,1000,1000);
        scene.getStylesheets().add("/css/generate.css");
        stage.setScene(scene);
        System.out.println("ToGenerate");

    }


}
