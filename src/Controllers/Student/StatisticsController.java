package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Results;
import Model.Student;
import Model.TableView.UserStatisticsContestRank;
import Model.TableView.UserStatisticsLeaderBoard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StatisticsController implements Initializable {
    private Stage stage;
    @FXML
    public Label leaderboardButton;
    @FXML
    public Label contestRankButton;
    @FXML
    public TableView<UserStatisticsLeaderBoard> statisticsLeaderBoardTableView;
    @FXML
    public TableView<UserStatisticsContestRank> statisticsContestRankTableView;
    @FXML
    public TableColumn<UserStatisticsLeaderBoard, String> leaderBoardStudent;
    @FXML
    public TableColumn<UserStatisticsLeaderBoard, Integer> leaderBoardPoints;
    @FXML
    public TableColumn<UserStatisticsContestRank, String> contestSubject;
    @FXML
    public TableColumn<UserStatisticsContestRank, Integer> contestRank;
    @FXML
    public TableColumn<UserStatisticsContestRank, Integer> contestId;
    @FXML
    public LineChart lineChart;
    @FXML
    public Button backButton;


    ObservableMap<Student, Integer> leaderBoardHashMap = FXCollections.observableHashMap();
    ObservableList<UserStatisticsLeaderBoard> leaderBoardList = FXCollections.observableArrayList();
    ObservableList contestList = FXCollections.observableArrayList();
    ArrayList<Integer> participatedContests = new ArrayList();
    ArrayList<Results> participatedContestsResults = new ArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        leaderBoardStudent.setCellValueFactory(new PropertyValueFactory<>("name"));
        leaderBoardPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
        leaderBoardPoints.setSortType(TableColumn.SortType.DESCENDING);
        contestId.setCellValueFactory(new PropertyValueFactory<>("contestId"));
        contestSubject.setCellValueFactory(new PropertyValueFactory<>("contestName"));
        contestRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        statisticsLeaderBoardTableView.setItems(leaderBoardList);
        statisticsContestRankTableView.setItems(contestList);


    }

    public void toMainMenu(MouseEvent event) throws IOException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    public void getData() {
        //LeaderBoard TableView
        for (Results result : LoginController.resultsList
                ) {
            for (Student student : LoginController.studentList
                    ) {
                if (result.getUserID() == student.getId()) {
                    if (result.getType() == 0){
                        leaderBoardHashMap.put(student, leaderBoardHashMap.getOrDefault(student, 0) + result.getScore());

                    }
                }

            }
        }
        for (Map.Entry<Student, Integer> mapEntry : leaderBoardHashMap.entrySet()
                ) {
            leaderBoardList.add(new UserStatisticsLeaderBoard(mapEntry.getKey().getName(), mapEntry.getValue()));
        }
        for (int i = 0; i < leaderBoardList.size(); i++) {
            if (LoginController.student.getName().equals(leaderBoardList.get(i).getName())) {
                statisticsLeaderBoardTableView.getSelectionModel().select(leaderBoardList.get(i));
                break;
            }
        }

        //Contest TableView

        for (Results results : LoginController.resultsList
                ) {
            if (results.getUserID() == LoginController.student.getId()) {
                if (results.getType() == 1) {
                    participatedContests.add(results.getContestID());
                }
            }
        }
        for (Results results : LoginController.resultsList
                ) {
            if (results.getType() == 1) {
                for (int i = 0; i < participatedContests.size(); i++) {
                    if (results.getContestID() == participatedContests.get(i).intValue()) {
                        participatedContestsResults.add(new Results(results.getContestID(), results.getContestName(), results.getName(), results.getScore(), 1));
                    }
                }
            }
        }
        List<Results> testList = participatedContestsResults.stream()
                .sorted(Comparator.comparingInt(Results::getScore).reversed())
                .collect(groupingBy(Results::getContestName, LinkedHashMap::new, toList()))
                .values().stream()
                .flatMap(Collection::stream)
                .collect(toList());
        int tempContestID = -1;
        for (int i = 0; i < testList.size(); i++) {
            if (tempContestID == -1) {
                tempContestID = testList.get(i).getContestID();
            } else {
                if (testList.get(i).getContestID() == tempContestID) {
                    testList.get(i).setRank(testList.get(i).getRank() + 1);
                } else {
                    tempContestID = testList.get(i).getContestID();
                }
            }
        }
        for (Results result : testList
                ) {
            if (result.getName().equals(LoginController.student.getName())) {
                contestList.add(new UserStatisticsContestRank(result.getContestID(), result.getContestName(), result.getRank()));
            }
        }


        //LineChart data
        XYChart.Series series = new XYChart.Series();
        lineChart.getXAxis().setLabel("Class");
        for (Results result : LoginController.resultsList
                ) {
            if (result.getUserID() == LoginController.student.getId()) {
                if (result.getType() == 0){
                    series.getData().add(new XYChart.Data(result.getContestName(), result.getCorrectAnswerPercent()));
                }
            }
        }
        lineChart.getData().add(series);

    }
}


