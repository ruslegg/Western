package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SettingsController implements Initializable {
    private Stage stage;
    @FXML
    public CheckBox soundButton;
    @FXML
    public CheckBox musicButton;
    @FXML
    public Button backButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void soundTrigger(MouseEvent event) throws IOException {

    }
    public void musicTrigger(MouseEvent event) throws  IOException{

    }
    public void toMainMenu(MouseEvent event) throws IOException{
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root,1280,1080);
        scene.getStylesheets().add("/css/mainMenu.css");
        stage.setScene(scene);

    }


}