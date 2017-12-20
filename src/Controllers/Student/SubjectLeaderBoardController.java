package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Results;
import Model.TableView.UserGameSubjectLeaderBoard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller that responds for the specific subject's leader board
 */
public class SubjectLeaderBoardController implements Initializable {
    @FXML
    public Label subjectLabel;
    public TableView<UserGameSubjectLeaderBoard> subjectLeaderBoardTableView;
    public TableColumn<UserGameSubjectLeaderBoard, Integer> studentId, studentScore;
    public TableColumn<UserGameSubjectLeaderBoard, String> studentName;
    public Button backButton;
    private Stage stage;
    private ObservableList<UserGameSubjectLeaderBoard> studentList = FXCollections.observableArrayList();

    /**
     * Associated Table columns with required data
     * Setting sort columns to the tableview
     * Selects the user in the tableview in order to better see his place in the leaderboard
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        subjectLabel.setText(ChosenGameMenuController.getQuizName());
        studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentScore.setCellValueFactory(new PropertyValueFactory<>("studentScore"));
        subjectLeaderBoardTableView.setItems(studentList);
        studentScore.setSortType(TableColumn.SortType.DESCENDING);
        subjectLeaderBoardTableView.getSortOrder().clear();
        subjectLeaderBoardTableView.getSortOrder().add(studentScore);
        for (UserGameSubjectLeaderBoard user : studentList
                ) {
            if (user.getStudentId() == LoginController.getStudent().getId()) {
                subjectLeaderBoardTableView.getSelectionModel().select(user);
                break;
            }
        }
    }

    /**
     * Uses a for-each loop through all the results in order to find results for this specific competition and populates
     * the list with data
     */
    public void getData() {
        for (Results results : LoginController.getResultsList()
                ) {
            if (results.getType() == ChosenGameMenuController.getQuizType()
                    && results.getContestID() == ChosenGameMenuController.getQuizId()) {
                studentList.add(new UserGameSubjectLeaderBoard(results.getUserID(), results.getScore(), results.getName()));
            }
        }
    }

    /**
     * Switches back to Chosen Game Menu
     */
    public void toChosenGameMenu(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
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
}
