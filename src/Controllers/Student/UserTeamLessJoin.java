package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Student;
import Model.TableView.TeamRequest;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller that stands for joining already existing teams
 */
public class UserTeamLessJoin implements Initializable {
    @FXML
    public TableView<TeamRequest> joinTeamTableView;
    public TableColumn<TeamRequest, String> teamName, teamAbbreviation;
    public Button requestButton, backButton;
    private Stage stage;
    private ObservableList<TeamRequest> teamList = FXCollections.observableArrayList();

    /**
     * Populates list using getTeams() method
     * Associates table columns with required data
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTeams();
        teamName.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        teamAbbreviation.setCellValueFactory(new PropertyValueFactory<>("teamAbbreviation"));
        joinTeamTableView.setItems(teamList);
    }

    /**
     * Populates the teamList using a for-each loop through all teams
     */
    public void getTeams() {
        for (Team team : LoginController.getTeamList()
                ) {
            teamList.add(new TeamRequest(team.getName(), team.getAbbreviation()));
        }
    }

    /**
     * Switches to Teamless Options Scene
     */
    public void toTeamLessOptions(){
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

    /**
     * Performs a check if selection is empty and an existing request to that specific team made.
     * If checks are passed, a new team request is added to teamRequests list and switched to Student's Main Menu
     * @throws IOException - Exception cause by serialization
     */
    public void requestJoin() throws IOException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        boolean selected = true;
        if (joinTeamTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Join Error");
            alert.setHeaderText("Selection is empty");
            alert.setContentText("You have not selected any team from the list. Please try again.");
            alert.showAndWait();
            selected = false;
        }

        for (Student student : LoginController.getTeamRequests()) {
            if (student.getTeam().getName().equals(joinTeamTableView.getSelectionModel().getSelectedItem().getTeamName()) && student.getName().equals(LoginController.getStudent().getName())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Team Join Failed");
                alert.setHeaderText("Team Join Has Failed");
                alert.setContentText("You have already sent a request to this team.");
                alert.showAndWait();
                selected = false;
                break;
            }
        }

        if (selected) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Join Successful");
            alert.setHeaderText("Team Join successful");
            alert.setContentText("You have successfully requested to join a team. You now need to wait for the team-leader for approval");
            alert.showAndWait();
            Student student = new Student(LoginController.getStudent().getId(), LoginController.getStudent().getName(),
                    LoginController.getStudent().getUsername(), LoginController.getStudent().getPassword(),
                    new Team(joinTeamTableView.getSelectionModel().getSelectedItem().getTeamName(),
                            joinTeamTableView.getSelectionModel().getSelectedItem().getTeamAbbreviation()), LoginController.getStudent().getSchoolClass());
            student.serializeTeamRequest();
            stage = (Stage) backButton.getScene().getWindow();
            VBox root;
            root = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        }
    }
}
