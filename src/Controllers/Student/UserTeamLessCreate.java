package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Results;
import Model.Team;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller that stands for creating a new team
 */
public class UserTeamLessCreate implements Initializable {

    @FXML
    public TextField teamNameTextField, teamAbbreviationTextField;
    public Button createButton, backButton;
    private Stage stage;

    /**
     * TextFields are being cleared in order to avoid conflicts
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!teamNameTextField.getText().isEmpty() || !teamAbbreviationTextField.getText().isEmpty()) {
            teamNameTextField.setText("");
            teamAbbreviationTextField.setText("");
        }
    }

    /**
     * Switches to Student's Main menu Scene
     */
    public void toMainMenu(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) createButton.getScene().getWindow();
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
     * Several checks are performed before creating a team, such as:
     * Team name/abbreviation is empty
     * Team name/abbreviation is short
     * A new team is created if all checks are made and the student is made a team-leader
     * Switches to Team Information Scene
     * @throws IOException Exception caused by Serialization
     */
    public void createTeam() throws IOException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        boolean teamNameCorrect = true;
        boolean teamAbbreviationCorrect = true;
        int teamExist = 0;
        if (teamNameTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #1");
            alert.setHeaderText("Team name is empty");
            alert.setContentText("Your team name insertion is empty. Please try again");
            alert.showAndWait();
            teamNameCorrect = false;
        } else if (teamNameTextField.getText().length() < 3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #2");
            alert.setHeaderText("Team name is short");
            alert.setContentText("Your team name size must be more than 3 characters");
            alert.showAndWait();
            teamNameCorrect = false;
        } else {
            for (Team team : LoginController.getTeamList()
                    ) {
                if (team.getName().equals(teamNameTextField.getText())) {
                    teamNameTextField.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Team Creation Error #3");
                    alert.setHeaderText("Team name already exists");
                    alert.setContentText("Your team name already exists in our database. Please try another name");
                    alert.showAndWait();
                    teamNameCorrect = false;
                    break;
                }
            }
        }

        if (teamAbbreviationTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #4");
            alert.setHeaderText("Team Abbreviation is empty");
            alert.setContentText("Your team abbreviation insertion is empty. Please try again");
            alert.showAndWait();
            teamAbbreviationCorrect = false;
        } else if (teamAbbreviationTextField.getText().length() != 3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Creation Error #5");
            alert.setHeaderText("Invalid number of characters");
            alert.setContentText("Your team abbreviation must have 3 characters");
            alert.showAndWait();
            teamAbbreviationCorrect = false;
        } else {
            for (Team team : LoginController.getTeamList()
                    ) {
                if (team.getAbbreviation().equals(teamAbbreviationTextField.getText())) {
                    teamAbbreviationTextField.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Team Creation Error #6");
                    alert.setHeaderText("Team abbreviation exists");
                    alert.setContentText("Your team abbreviation already exists in our database");
                    alert.showAndWait();
                    teamAbbreviationCorrect = false;
                    break;
                }
            }
        }

        if (teamNameCorrect && teamAbbreviationCorrect) {
            Team team = new Team(teamNameTextField.getText(), teamAbbreviationTextField.getText());
            try {
                team.serialize();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            LoginController.getStudent().setTeam(team);
            LoginController.getStudent().setTeamLeader(true);
            LoginController.getStudent().createTeamSerialize();
            for (Results results : LoginController.getResultsList()
                    ) {
                if (results.getUserID() == LoginController.getStudent().getId()) {
                    results.setTeamName(team.getName());
                    results.setTeamAbbreviation(team.getAbbreviation());
                }
            }
            try {
                Results.refresh();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creation successful");
            alert.setHeaderText("Team creation successful");
            alert.setContentText("Your team has been successfully created");
            alert.showAndWait();
            stage = (Stage) createButton.getScene().getWindow();
            VBox root;
            root = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        }


    }

}
