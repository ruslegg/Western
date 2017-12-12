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
import javafx.scene.chart.*;
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

public class StatisticsTeachersController implements Initializable {
    private Stage stage;
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    @FXML
    public TableView<TeacherCompetition> competitionTableView;
    @FXML
    public TableView<TeacherContest> contestsTableView;
    @FXML
    public TableColumn<TeacherCompetition,String> competitionName;
    @FXML
    public TableColumn<TeacherCompetition,Integer> competitionNumber,competitionId;
    @FXML
    public TableColumn<TeacherContest,String> contestName;
    @FXML
    public TableColumn<TeacherContest,Integer> contestNumber,contestId;
    @FXML
    public BarChart barChart;
    @FXML
    public Button backButton;


    ObservableList<TeacherCompetition> teacherCompetitionsList = FXCollections.observableArrayList();
    ObservableList<TeacherContest> teacherContestsList = FXCollections.observableArrayList();
    ArrayList<TeacherLineChart> classList = new ArrayList();




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        competitionId.setCellValueFactory(new PropertyValueFactory<>("competitionId"));
        competitionName.setCellValueFactory(new PropertyValueFactory<>("competitionName"));
        competitionNumber.setCellValueFactory(new PropertyValueFactory<>("competitionNumber"));
        contestId.setCellValueFactory(new PropertyValueFactory<>("contestId"));
        contestName.setCellValueFactory(new PropertyValueFactory<>("contestName"));
        contestNumber.setCellValueFactory(new PropertyValueFactory<>("contestNumber"));
        competitionTableView.getColumns().clear();
        contestsTableView.getColumns().clear();
        competitionTableView.getColumns().addAll(competitionId,competitionName,competitionNumber);
        contestsTableView.getColumns().addAll(contestId,contestName,contestNumber);
        competitionTableView.setItems(teacherCompetitionsList);
        contestsTableView.setItems(teacherContestsList);
        barChart.getXAxis().setLabel("Class");
        barChart.getYAxis().setLabel("Number of students");
        XYChart.Series series = new XYChart.Series();


        for (int i = 0; i < classList.size(); i++)
            series.getData().add(new XYChart.Data(classList.get(i).getSchoolClass().getNumber()+classList.get(i).getSchoolClass().getLetter(), classList.get(i).getNumber()));

        barChart.getData().add(series);
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
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);

    }
    public void getData() throws IOException, ClassNotFoundException {
        for (Results result : LoginController.resultsList
                ) {
            if (result.getTeacherID() == LoginController.teacher.getId()) {
                int quizType = result.getType();
                switch (quizType) {
                    case 0: {
                        boolean exist = false;
                        for (TeacherCompetition teacherCompetition : teacherCompetitionsList) {
                            if (teacherCompetition.getCompetitionId() == result.getContestID()) {
                                teacherCompetition.increment();
                                exist=true;

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
                                exist=true;
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
        for (Results results: LoginController.resultsList){
            if (classList.isEmpty()){
                TeacherLineChart teacherLineChart = new TeacherLineChart(results.getSchoolClass());
                teacherLineChart.increment();
                classList.add(teacherLineChart);
            }
            else{
                int exist=0;
                boolean added= false;
                for (int i=0;i<classList.size();i++){
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

    }


