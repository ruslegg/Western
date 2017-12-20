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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Controller that holds information about the student's statistics, such as:
 * How much score the student have displayed in a Leaderboard with all students
 * Which rank is the student in each contest that he participated
 * A line chart that represents correct answer percentage by each quiz that he played to have an overview of his results
 */
public class StatisticsController implements Initializable {
    @FXML
    public Label leaderboardButton, contestRankButton;
    public TableView<UserStatisticsLeaderBoard> statisticsLeaderBoardTableView;
    public TableView<UserStatisticsContestRank> statisticsContestRankTableView;
    public TableColumn<UserStatisticsLeaderBoard, String> leaderBoardStudent;
    public TableColumn<UserStatisticsLeaderBoard, Integer> leaderBoardPoints;
    public TableColumn<UserStatisticsContestRank, String> contestSubject;
    public TableColumn<UserStatisticsContestRank, Integer> contestRank;
    public TableColumn<UserStatisticsContestRank, Integer> contestId;
    public LineChart lineChart;
    public Button backButton;
    private Stage stage;
    private ObservableMap<Student, Integer> leaderBoardHashMap = FXCollections.observableHashMap();
    private ObservableList<UserStatisticsLeaderBoard> leaderBoardList = FXCollections.observableArrayList();
    private ObservableList contestList = FXCollections.observableArrayList();
    private ArrayList<Integer> participatedContests = new ArrayList();
    private ArrayList<Results> participatedContestsResults = new ArrayList();

    /**
     * Populates data using getData() method
     * Associates available data with table columns
     * @param location - not used
     * @param resources - not used
     */
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
        lineChart.setLegendVisible(false);

    }

    /**
     * Uses HashMap (to avoid duplicates) and for-each loops, populates Leaderboard tableview
     * Uses a for-each loop in order to populate the list and functionality(used from external sources) populates the contest
     * tableview with participated by users contests and showing the rank of the user
     * Uses a for-each loop to populate correct answer rate LineChart
     */
    public void getData() {
        //LeaderBoard TableView
        for (Results result : LoginController.getResultsList()
                ) {
            for (Student student : LoginController.getStudentList()
                    ) {
                if (result.getUserID() == student.getId()) {
                    if (result.getType() == 0) {
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
            if (LoginController.getStudent().getName().equals(leaderBoardList.get(i).getName())) {
                statisticsLeaderBoardTableView.getSelectionModel().select(leaderBoardList.get(i));
                break;
            }
        }

        //Contest TableView

        for (Results results : LoginController.getResultsList()
                ) {
            if (results.getUserID() == LoginController.getStudent().getId()) {
                if (results.getType() == 1) {
                    participatedContests.add(results.getContestID());
                }
            }
        }
        for (Results results : LoginController.getResultsList()
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
            if (result.getName().equals(LoginController.getStudent().getName())) {
                contestList.add(new UserStatisticsContestRank(result.getContestID(), result.getContestName(), result.getRank()));
            }
        }


        //LineChart data
        XYChart.Series series = new XYChart.Series();
        for (Results result : LoginController.getResultsList()
                ) {
            if (result.getUserID() == LoginController.getStudent().getId()) {
                if (result.getType() == 0) {
                    series.getData().add(new XYChart.Data(result.getContestName(), result.getCorrectAnswerPercent()));
                }
            }
        }
        lineChart.getData().add(series);

    }

    /**
     * Switches to Student's Main Menu
     */
    public void toMainMenu() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

}


