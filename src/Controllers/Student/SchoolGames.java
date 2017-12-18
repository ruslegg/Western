package Controllers.Student;
import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Question;
import Model.SchoolClass;
import Model.TableView.UserGameCompetition;
import Model.TableView.UserGameContest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class SchoolGames implements Initializable {

    Stage stage;
    @FXML
    Button backButton, playButton;
    public TableView<UserGameContest> contestTableView;
    public TableView<UserGameCompetition> competitionTableView;
    public TableColumn<UserGameContest, String> contestSubject, daysLeft;
    public TableColumn<UserGameContest, Integer> contestId;
    public TableColumn<UserGameCompetition, String> competitionSubject;
    public TableColumn<UserGameCompetition, Integer> competitionId;

    ObservableList<UserGameContest> contests = FXCollections.observableArrayList();
    ObservableList<UserGameCompetition> competitions = FXCollections.observableArrayList();



    public void toChooseTypeOfGame(MouseEvent event) throws IOException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/chooseTypeOfGame.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/game.css");
        stage.setScene(scene);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        competitionId.setCellValueFactory(new PropertyValueFactory<>("id"));
        competitionSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        contestId.setCellValueFactory(new PropertyValueFactory<>("id"));
        contestSubject.setCellValueFactory(new PropertyValueFactory<>("contestSubject"));
        daysLeft.setCellValueFactory(new PropertyValueFactory<>("daysLeft"));
        competitionTableView.setItems(competitions);
        contestTableView.setItems(contests);
        competitionTableView.getSelectionModel().selectedItemProperty().addListener((obs,oldItem,newItem) -> {
            if (newItem != null){
                contestTableView.getSelectionModel().clearSelection();
                playButton.setDisable(false);
            }
            else{
                playButton.setDisable(true);
            }
        });
        contestTableView.getSelectionModel().selectedItemProperty().addListener((obs,oldItem,newItem) -> {
            if (newItem!=null){
                competitionTableView.getSelectionModel().clearSelection();
                playButton.setDisable(false);
            }
            else{
                playButton.setDisable(true);
            }
        });
        playButton.setDisable(true);

    }

    public void getData() throws ParseException {
        int competitionId = -1;
        int contestId = -1;
        int classCorrect = 0;
        for (Question question : LoginController.questionsList) {
            classCorrect=0;
            ArrayList<SchoolClass> classList = question.getClassList();
            for (SchoolClass schoolClass: classList) {
                if (schoolClass.getNumber() == LoginController.student.getSchoolClass().getNumber() && schoolClass.getLetter().equals(LoginController.student.getSchoolClass().getLetter())){
                    classCorrect=1;
                }
            }
            if (classCorrect==1) {
                if (question.getQuizType() == 0) {
                    if (competitionId == -1 || (question.getQuizID() != competitionId)) {
                        competitions.add(new UserGameCompetition(question.getSubject(), question.getQuizID(),question.getTeacherID()));
                        competitionId = question.getQuizID();
                    }
                } else if (question.getQuizType() == 1) {
                    if (available(question.getFromDate(), question.getToDate())) {
                        if (contestId == -1 || (question.getQuizID() != contestId)) {
                            contests.add(new UserGameContest(question.getQuizID(), question.getSubject(), question.getToDate(),question.getTeacherID()));
                            contestId = question.getQuizID();
                        }
                    }
                }
            }
        }
    }

    public void playGame(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        if (!competitionTableView.getSelectionModel().isEmpty()){
            ChosenGameMenuController.setQuizType(0);
            ChosenGameMenuController.setQuizId(competitionTableView.getSelectionModel().getSelectedItem().getId());
            ChosenGameMenuController.setQuizName(competitionTableView.getSelectionModel().getSelectedItem().getSubject());
            ChosenGameMenuController.setTeacherId(competitionTableView.getSelectionModel().getSelectedItem().getTeacherId());

        }else{
            ChosenGameMenuController.setQuizType(1);
            ChosenGameMenuController.setQuizId(contestTableView.getSelectionModel().getSelectedItem().getId());
            ChosenGameMenuController.setQuizName(contestTableView.getSelectionModel().getSelectedItem().getContestSubject());
            ChosenGameMenuController.setTeacherId(contestTableView.getSelectionModel().getSelectedItem().getTeacherId());

        }

        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/chosenGameMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    public void toMainMenu(MouseEvent mouseEvent) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/chooseTypeOfGame.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    public boolean available(String fromDateString, String toDateString) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = dateFormatter.parse(fromDateString);
        Date toDate = dateFormatter.parse(toDateString);
        Date currentDate = new Date();
        long fromDateLong = fromDate.getTime();
        long toDateLong = toDate.getTime();
        long currentLong = currentDate.getTime();
        long fromNumber = (currentLong - fromDateLong) / (1000 * 60 * 60 * 24);
        long toNumber = (toDateLong - currentLong) / (1000 * 60 * 60 * 24);
        if (fromNumber >= 0 && toNumber >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
