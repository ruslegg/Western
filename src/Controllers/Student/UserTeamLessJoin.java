package Controllers.Student;

import Controllers.LoginController;
import Model.Student;
import Model.TableView.TeamRequest;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserTeamLessJoin implements Initializable {
    private Stage stage;

    @FXML
    public TableView<TeamRequest> joinTeamTableView;
    public TableColumn<TeamRequest, String> teamName,teamAbbreviation;
    public Button requestButton,backButton;

    ObservableList<TeamRequest> teamList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTeams();
        teamName.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        teamAbbreviation.setCellValueFactory(new PropertyValueFactory<>("teamAbbreviation"));
        joinTeamTableView.setItems(teamList);
    }

    public void getTeams(){
        for (Team team: LoginController.teamList
             ) {
            teamList.add(new TeamRequest(team.getName(),team.getAbbreviation()));
        }
    }
    public void requestJoin() throws IOException {
        boolean selected = true;
        if (joinTeamTableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Join Error");
            alert.setHeaderText("Selection is empty");
            alert.setContentText("You have not selected any team from the list. Please try again.");
            alert.showAndWait();
            selected = false;
        }

        for (Student student : LoginController.teamRequests) {
            if (student.getTeam().getName().equals(joinTeamTableView.getSelectionModel().getSelectedItem().getTeamName()) && student.getName().equals(LoginController.student.getName())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Team Join Failed");
                alert.setHeaderText("Team Join Has Failed");
                alert.setContentText("You have already sent a request to this team.");
                alert.showAndWait();
                selected = false;
                break;
            }
        }

        if (selected){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Join Successful");
            alert.setHeaderText("Team Join successful");
            alert.setContentText("You have successfully requested to join a team. You now need to wait for the team-leader for approval");
            alert.showAndWait();
            Student student = new Student(LoginController.student.getId(),LoginController.student.getName(),
                    LoginController.student.getUsername(),LoginController.student.getPassword(),
                    new Team(joinTeamTableView.getSelectionModel().getSelectedItem().getTeamName(),
                            joinTeamTableView.getSelectionModel().getSelectedItem().getTeamAbbreviation()),LoginController.student.getSchoolClass());
            student.serializeTeamRequest();
            stage = (Stage) backButton.getScene().getWindow();
            VBox root;
            root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        }
    }
    public void toTeamLessOptions() throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
}
