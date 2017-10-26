package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class GameIntroController{
    private Stage stage;
    @FXML
    public Button gameIntroButton,gameButton;



    public void toBriefing(MouseEvent event) throws IOException, InterruptedException {
        stage = (Stage) gameIntroButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/briefing.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/briefing.css");
        stage.setScene(scene);

    }


}