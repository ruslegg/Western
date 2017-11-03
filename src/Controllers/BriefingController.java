package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

// need timer
public class BriefingController{
    private Stage stage;
    @FXML
    public Label cityLabel;
    @FXML
    public ImageView briefingNPC;
    @FXML
    public Label textBriefing;
    @FXML
    public Button gameButton;



    public void toGame(MouseEvent event) throws IOException, InterruptedException {
        System.out.println("Check");
        stage = (Stage) gameButton.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("/FXML/game.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/game.css");
        stage.setScene(scene);
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
    }
}
