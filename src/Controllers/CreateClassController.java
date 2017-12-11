package Controllers;

import Model.SchoolClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateClassController implements Initializable {
    @FXML
    public TextField classNumber;
    @FXML
    public TextField classLetter;
    @FXML
    public Button submitButton;
    @FXML
    public Button backButton;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    public void createClass(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        SchoolClass schoolClass = new SchoolClass(Integer.valueOf(classNumber.getText()),classLetter.getText());
        toCreateMenu();
    }
}
