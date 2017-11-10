package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
public class GameIntroController{
    private Stage stage;
    @FXML
    public Button gameIntroButton,gameButton;



    public void toBriefing(MouseEvent event) throws IOException, InterruptedException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) gameIntroButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/briefing.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);

    }


}