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


public class SettingsController implements Initializable {
    private Stage stage;
    @FXML
    public Button soundButton;
    @FXML
    public Button musicButton;
    @FXML
    public Button backButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void soundTrigger(MouseEvent event) throws IOException {

    }
    public void musicTrigger(MouseEvent event) throws  IOException{

    }
    public void blabla(MouseEvent event) throws IOException{
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/generate.fxml"));
        Scene scene = new Scene(root,1000,1000);
        scene.getStylesheets().add("/css/generate.css");
        stage.setScene(scene);
        System.out.println("ToGenerate");

    }


}