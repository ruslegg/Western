package Controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class QuizFinishedController  {
    private Stage stage;
    @FXML
    public Label congratulationLabel;
    @FXML
    public Label scoreLabel;
    @FXML
    public Label answerRateLabel;
    @FXML
    public Button toLeaderboardsButton;
    @FXML



    public void congratulation() throws IOException {
    }

    public void score() throws IOException {

    }

    public void answerRate() throws IOException {

    }

    public void toLeaderboards(MouseEvent event) throws IOException{
        stage = (Stage) toLeaderboardsButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/leaderboards.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/leaderboards.css");
        stage.setScene(scene);
    }
}