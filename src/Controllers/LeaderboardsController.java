package Controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LeaderboardsController{
    private Stage stage;
    @FXML
    public Label leaderboardsLabel;
    @FXML
    public TableView leaderboardsTableView;
    @FXML
    public TableColumn rankTableColumn;
    @FXML
    public TableColumn nameTableColumn;
    @FXML
    public TableColumn scoreTableColumn;
    @FXML
    public Button toChooseTypeOfGameButton;
    @FXML



    public void setLeaderboardsLabel() throws IOException {
    }

    public void setLeaderboardsTableView () throws IOException {

    }

    public void setRankTableColumn() throws IOException {

    }

    public void setNameTableColumn() throws IOException {

    }

    public void setScoreTableColumn() throws IOException {

    }

    public void toChooseTypeOfGame(MouseEvent event) throws IOException{
        stage = (Stage) toChooseTypeOfGameButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/leaderboards.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/leaderboards.css");
        stage.setScene(scene);
    }
}