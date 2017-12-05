package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatisticsTeachersController implements Initializable {
    private Stage stage;
    @FXML
    public Label competitionButton;
    @FXML
    public Label contestantsButton;
    @FXML
    public TableView competitionTableView;
    @FXML
    public TableView contestantsTableView;
    @FXML
    public LineChart lineChart;
    @FXML
    public Button backButton;
    ArrayList<Double> list = new ArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void toMainMenu(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/teacherMainMenu.fxml"));
        Scene scene = new Scene(root,1280,720);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);

    }

}
