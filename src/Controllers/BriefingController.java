package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

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
        stage = (Stage) gameButton.getScene().getWindow();
        GameController gameController = new GameController();
        stage.setScene(gameController.scene);
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
    }
}
