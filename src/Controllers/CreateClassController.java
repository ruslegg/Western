package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateClassController implements Initializable {
    @FXML
    public TextField numberTextField;
    @FXML
    public TextField letterTextField;
    @FXML
    public Button submitButton;
    @FXML
    public Button backButton;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void toMainMenu(MouseEvent mouseEvent) {
    }

    public void nrOfClassTextfield()throws IOException{

    }
    public void letterOfClassTextfield() throws IOException{

    }

    public void toCreateMenu() throws IOException{
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/createOptionsTeacher.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }
}
