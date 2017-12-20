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

/**
 *  Controller that takes care of the showing the available competitions and contests to specific (logged in) student,
 *  by populating the TableViews through getData() method.
 */
public class SchoolGamesController implements Initializable {

    @FXML
    public Button backButton, playButton;
    public TableView<UserGameContest> contestTableView;
    public TableView<UserGameCompetition> competitionTableView;
    public TableColumn<UserGameContest, String> contestSubject, daysLeft;
    public TableColumn<UserGameContest, Integer> contestId;
    public TableColumn<UserGameCompetition, String> competitionSubject;
    public TableColumn<UserGameCompetition, Integer> competitionId;
    private Stage stage;
    private ObservableList<UserGameContest> contests = FXCollections.observableArrayList();
    private ObservableList<UserGameCompetition> competitions = FXCollections.observableArrayList();

    /**
     * Calls getData() method to populate TableViews
     * Associate table columns to required data
     * Disables play button if no choice in both TableView has been made
     * Listener to avoid double selection
     * @param location - not used
     * @param resources - not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        competitionId.setCellValueFactory(new PropertyValueFactory<>("id"));
        competitionSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        contestId.setCellValueFactory(new PropertyValueFactory<>("id"));
        contestSubject.setCellValueFactory(new PropertyValueFactory<>("contestSubject"));
        daysLeft.setCellValueFactory(new PropertyValueFactory<>("daysLeft"));
        competitionTableView.setItems(competitions);
        contestTableView.setItems(contests);
        competitionTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                contestTableView.getSelectionModel().clearSelection();
                playButton.setDisable(false);
            } else {
                playButton.setDisable(true);
            }
        });
        contestTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                competitionTableView.getSelectionModel().clearSelection();
                playButton.setDisable(false);
            } else {
                playButton.setDisable(true);
            }
        });
        playButton.setDisable(true);

    }

    /**
     * Populates lists by using a for-each loop through all questions and choosing only the questions available to
     * specific(logged in) student.
     */
    public void getData() {
        int competitionId = -1;
        int contestId = -1;
        int classCorrect = 0;
        for (Question question : LoginController.getQuestionsList()) {
            classCorrect = 0;
            ArrayList<SchoolClass> classList = question.getClassList();
            for (SchoolClass schoolClass : classList) {
                if (schoolClass.getNumber() == LoginController.getStudent().getSchoolClass().getNumber() && schoolClass.getLetter().equals(LoginController.getStudent().getSchoolClass().getLetter())) {
                    classCorrect = 1;
                }
            }
            if (classCorrect == 1) {
                if (question.getQuizType() == 0) {
                    if (competitionId == -1 || (question.getQuizID() != competitionId)) {
                        competitions.add(new UserGameCompetition(question.getSubject(), question.getQuizID(), question.getTeacherID()));
                        competitionId = question.getQuizID();
                    }
                } else if (question.getQuizType() == 1) {
                    if (available(question.getFromDate(), question.getToDate())) {
                        if (contestId == -1 || (question.getQuizID() != contestId)) {
                            contests.add(new UserGameContest(question.getQuizID(), question.getSubject(), question.getToDate(), question.getTeacherID()));
                            contestId = question.getQuizID();
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks whether is a contest or a competition and forwards variables to Chosen Game Controller
     * Switches to Chosen Game Scene
     */
    public void playGame() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        if (!competitionTableView.getSelectionModel().isEmpty()) {
            ChosenGameMenuController.setQuizType(0);
            ChosenGameMenuController.setQuizId(competitionTableView.getSelectionModel().getSelectedItem().getId());
            ChosenGameMenuController.setQuizName(competitionTableView.getSelectionModel().getSelectedItem().getSubject());
            ChosenGameMenuController.setTeacherId(competitionTableView.getSelectionModel().getSelectedItem().getTeacherId());

        } else {
            ChosenGameMenuController.setQuizType(1);
            ChosenGameMenuController.setQuizId(contestTableView.getSelectionModel().getSelectedItem().getId());
            ChosenGameMenuController.setQuizName(contestTableView.getSelectionModel().getSelectedItem().getContestSubject());
            ChosenGameMenuController.setTeacherId(contestTableView.getSelectionModel().getSelectedItem().getTeacherId());

        }

        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/chosenGameMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /**
     * Switches back to Choose Type Game Scene
     */
    public void toChooseTypeOfGame() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/chooseTypeOfGame.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/game.css");
        stage.setScene(scene);
    }

    /**
     * Switches to Student's Main Menu Scene
     */
    public void toMainMenu(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/chooseTypeOfGame.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /**
     * Performing a check whether the contest is available to the student by time in order to add it to the contestList
     * @param fromDateString Date that contest is available from
     * @param toDateString Date that contest is available to
     * @return if the contest is available or not
     */
    public boolean available(String fromDateString, String toDateString){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = null;
        try {
            fromDate = dateFormatter.parse(fromDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date toDate = null;
        try {
            toDate = dateFormatter.parse(toDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = new Date();
        long fromDateLong = fromDate.getTime();
        long toDateLong = toDate.getTime();
        long currentLong = currentDate.getTime();
        long fromNumber = (currentLong - fromDateLong) / (1000 * 60 * 60 * 24);
        long toNumber = (toDateLong - currentLong) / (1000 * 60 * 60 * 24);
        return fromNumber >= 0 && toNumber >= 0;
    }
}
