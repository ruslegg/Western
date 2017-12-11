package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
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

    public void createTeam(){
        boolean teamNameCorrect = false;
        boolean teamAbbreviation = false;
        if (teamNameTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #1");
            alert.setHeaderText("Team name is empty");
            alert.setContentText("Your team name insertion is empty. Please try again");
            alert.showAndWait();
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
        }
        else if(teamAbbreviationTextField.getText().length()<3){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #4");
            alert.setHeaderText("Team name is empty");
            alert.setContentText("Your team name insertion is empty. Please try again");
            alert.showAndWait();
        }
    }
}
