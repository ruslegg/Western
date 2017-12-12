package Controllers.Student;

import Controllers.LoginController;
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

public class SubjectLeaderBoardController implements Initializable{
    private Stage stage;
    @FXML
    public Label subjectLabel;
    public TableView<UserGameSubjectLeaderBoard> subjectLeaderBoardTableView;
    public TableColumn<UserGameSubjectLeaderBoard,Integer> studentId,studentScore;
    public TableColumn<UserGameSubjectLeaderBoard,String> studentName;
    public Button backButton;

    ObservableList<UserGameSubjectLeaderBoard> studentList = FXCollections.observableArrayList();


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
        for (UserGameSubjectLeaderBoard user: studentList
             ) {
            if (user.getStudentId() == LoginController.student.getId()){
                subjectLeaderBoardTableView.getSelectionModel().select(user);
                break;
            }
        }
    }

    public void getData(){
        for (Results results: LoginController.resultsList
             ) {
            if (results.getType() == ChosenGameMenuController.getQuizType()
                    && results.getContestID() == ChosenGameMenuController.getQuizId()){
                studentList.add(new UserGameSubjectLeaderBoard(results.getUserID(),results.getScore(),results.getName()));
            }
        }
    }
    public void toChosenGameMenu() throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/chosenGameMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
}
