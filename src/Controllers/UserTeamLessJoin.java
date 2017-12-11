package Controllers;

import Model.TableView.TeamRequest;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.*;

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
    public void requestJoin(){
        boolean selected = false;
        if (joinTeamTableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Join Error");
            alert.setHeaderText("Selection is empty");
            alert.setContentText("You have not selected any team from the list. Please try again");
            alert.showAndWait();
            selected=false;
        }
    }
}
