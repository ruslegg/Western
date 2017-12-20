package Controllers.Teacher;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Results;
import Model.TableView.TeacherCompetition;
import Model.TableView.TeacherContest;
import Model.TableView.TeacherLineChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller that is used to retrieve statistics from available data in order to inform the teacher.
 * Statistics is giving information about how many persons from each class has played the games, how many
 * students has played each specific competition and contest.
 */
public class StatisticsTeachersController implements Initializable {
    private Stage stage;

    @FXML
    public TableView<TeacherCompetition> competitionTableView;
    public TableView<TeacherContest> contestsTableView;
    public TableColumn<TeacherCompetition, String> competitionName;
    public TableColumn<TeacherCompetition, Integer> competitionNumber, competitionId;
    public TableColumn<TeacherContest, String> contestName;
    public TableColumn<TeacherContest, Integer> contestNumber, contestId;
    public BarChart barChart;
    public Button backButton;

    private ObservableList<TeacherCompetition> teacherCompetitionsList = FXCollections.observableArrayList();
    private ObservableList<TeacherContest> teacherContestsList = FXCollections.observableArrayList();
    private ArrayList<TeacherLineChart> classList = new ArrayList();


    /**
     * Retrieves required information through getData() method.
     * Associating table columns with the data.
     * Populate tableviews with the lists.
     * Populates barChart with data.
     *
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        competitionId.setCellValueFactory(new PropertyValueFactory<>("competitionId"));
        competitionName.setCellValueFactory(new PropertyValueFactory<>("competitionName"));
        competitionNumber.setCellValueFactory(new PropertyValueFactory<>("competitionNumber"));
        contestId.setCellValueFactory(new PropertyValueFactory<>("contestId"));
        contestName.setCellValueFactory(new PropertyValueFactory<>("contestName"));
        contestNumber.setCellValueFactory(new PropertyValueFactory<>("contestNumber"));
        competitionTableView.getColumns().clear();
        contestsTableView.getColumns().clear();
        competitionTableView.getColumns().addAll(competitionId, competitionName, competitionNumber);
        contestsTableView.getColumns().addAll(contestId, contestName, contestNumber);
        competitionTableView.setItems(teacherCompetitionsList);
        contestsTableView.setItems(teacherContestsList);
        barChart.getYAxis().setLabel("Number of students");
        XYChart.Series series = new XYChart.Series();


        for (int i = 0; i < classList.size(); i++)
            series.getData().add(new XYChart.Data(classList.get(i).getSchoolClass().getNumber() + classList.get(i).getSchoolClass().getLetter(), classList.get(i).getNumber()));

        barChart.getData().add(series);
        barChart.setLegendVisible(false);
    }

    /**
     * Populates ObservableLists using a for-each loop through all results that equals to teacher's id.
     * Using a switch on quizType, chooses in which list should add the result.
     *
     * Populates classList using a for-each loop through all results sorts them by classes and increments whether this class
     * has already participated, that gives a overview how many students from each class has played teacher's quizzes.
     */
    public void getData() {
        for (Results result : LoginController.getResultsList()
                ) {
            if (result.getTeacherID() == LoginController.getTeacher().getId()) {
                int quizType = result.getType();
                switch (quizType) {
                    case 0: {
                        boolean exist = false;
                        for (TeacherCompetition teacherCompetition : teacherCompetitionsList) {
                            if (teacherCompetition.getCompetitionId() == result.getContestID()) {
                                teacherCompetition.increment();
                                exist = true;

                            }
                        }
                        if (!exist) {
                            TeacherCompetition newCompetition = new TeacherCompetition(result.getContestID(), result.getContestName());
                            teacherCompetitionsList.add(newCompetition);
                            newCompetition.increment();
                        }
                        break;
                    }
                    case 1: {
                        boolean exist = false;
                        for (TeacherContest teacherContest : teacherContestsList) {
                            if (teacherContest.getContestId() == result.getContestID()) {
                                teacherContest.increment();
                                exist = true;
                            }
                        }
                        if (!exist) {
                            TeacherContest newContest = new TeacherContest(result.getContestID(), result.getContestName());
                            teacherContestsList.add(newContest);
                            newContest.increment();
                        }
                    }
                }
            }
        }
        for (Results results : LoginController.getResultsList()) {
            if (classList.isEmpty()) {
                TeacherLineChart teacherLineChart = new TeacherLineChart(results.getSchoolClass());
                teacherLineChart.increment();
                classList.add(teacherLineChart);
            } else {
                int exist = 0;
                boolean added = false;
                for (int i = 0; i < classList.size(); i++) {
                    if (!added) {

                        if (results.getSchoolClass().getNumber() == classList.get(i).getSchoolClass().getNumber()
                                && results.getSchoolClass().getLetter().equals(classList.get(i).getSchoolClass().getLetter())) {
                            classList.get(i).increment();
                            exist++;
                            added = true;
                        }
                        if (i == classList.size() - 1 && exist == 0) {
                            TeacherLineChart teacherLineChart = new TeacherLineChart(results.getSchoolClass());
                            teacherLineChart.increment();
                            classList.add(teacherLineChart);
                            added = true;
                        }
                    }
                }
            }

        }

    }

    /**
     * Switch to Teacher's Main Menu
     */
    public void toMainMenu(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/teacherMainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);

    }


}


