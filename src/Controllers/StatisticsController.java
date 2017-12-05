package Controllers;

import Model.Results;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StatisticsController implements Initializable{
    private Stage stage;
    @FXML
    public Label leaderboardButton;
    @FXML
    public Label contestRankButton;
    @FXML
    public TableView leaderboardTableView;
    @FXML
    public TableView contestRankTableView;
    @FXML
    public LineChart lineChart;
    @FXML
    public Button backButton;
    ArrayList<Double> list = new ArrayList();

    public void addResult() throws IOException {
        Results result = new Results(1, "name", "bla", 2, 3, 4, 5);
        Results result1 = new Results(1, "name", "bla", 2, 3, 4, 6);

        result.addResult();
        result1.addResult();

        Scanner scan = new Scanner(new File ("src/data/attributes/results.txt"));

        while (scan.hasNextLine())
        {
            if (scan.next().equals("1"))
            {
                String test = scan.nextLine();
                String lastWord = test.substring(test.lastIndexOf(" ") + 1);
                double percentage = Double.valueOf(lastWord);
                list.add(percentage);
            }

            else
                scan.nextLine();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        XYChart.Series series = new XYChart.Series();

        for (int i = 0; i < list.size(); i++)
            series.getData().add(new XYChart.Data(String.valueOf(i), list.get(i)));

        lineChart.getData().add(series);
    }

    public void toMainMenu(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root,1280,720);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }
}
