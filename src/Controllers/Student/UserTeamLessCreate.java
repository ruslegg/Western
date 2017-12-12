package Controllers.Student;

import Controllers.LoginController;
import Model.Team;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserTeamLessCreate  implements Initializable{
    private Stage stage;

    @FXML
    public TextField teamNameTextField,teamAbbreviationTextField;
    public Button createButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!teamNameTextField.getText().isEmpty() || !teamAbbreviationTextField.getText().isEmpty()){
            teamNameTextField.setText("");
            teamAbbreviationTextField.setText("");
        }
    }

    public void createTeam() throws IOException {
        boolean teamNameCorrect = false;
        boolean teamAbbreviationCorrect = false;
        int teamExist = 0;
        if (teamNameTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #1");
            alert.setHeaderText("Team name is empty");
            alert.setContentText("Your team name insertion is empty. Please try again");
            alert.showAndWait();
            teamNameCorrect=false;
        }
        else if (teamNameTextField.getText().length() < 3){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #2");
            alert.setHeaderText("Team name is short");
            alert.setContentText("Your team name size must be more than 3 characters");
            alert.showAndWait();
            teamNameCorrect=false;
        }
        else{
            teamNameCorrect=true;
        }
        if (teamAbbreviationTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #3");
            alert.setHeaderText("Team Abbreviation is empty");
            alert.setContentText("Your team abbreviation insertion is empty. Please try again");
            alert.showAndWait();
            teamAbbreviationCorrect=false;
        }
        else if(teamAbbreviationTextField.getText().length()!=3){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #4");
            alert.setHeaderText("Team abbreviation is short");
            alert.setContentText("Your team abbreviation size must be more than 3 characters");
            alert.showAndWait();
            teamAbbreviationCorrect=false;
        }
        else{
            teamAbbreviationCorrect=true;
        }
        if (teamNameCorrect && teamAbbreviationCorrect){
            for (Team team: LoginController.teamList
                 ) {
                if (team.getName().equals(teamNameTextField.getText())){
                    teamExist++;
                }
                else if(team.getAbbreviation().equals(teamAbbreviationTextField.getText())){
                    teamExist++;
                }

            }
        }
        if (teamExist==0){
            Team team = new Team(teamNameTextField.getText(),teamAbbreviationTextField.getText());
            try {
                team.serialize();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            LoginController.student.setTeam(team);
            LoginController.student.setTeamLeader(true);
        }
        stage = (Stage) createButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/userTeamInformation.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
}
