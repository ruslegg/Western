package Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class QuizController {
    private Stage stage;
    @FXML Button returnButton;

    public void returnToMainMenu(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        stage = (Stage) returnButton.getScene().getWindow();
        Pane root;
        root = (GridPane) FXMLLoader.load(getClass().getResource("/FXML/main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());
        stage.setScene(scene);
        System.out.println("ToMainMenu");
    }
}
