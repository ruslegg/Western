package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Results;
import Model.Student;
import Model.TableView.TeamLeaderBoard;
import Model.TableView.TeamMembers;
import Model.TableView.TeamMembersRequest;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Holds the team information for the students that are already in a team
 * Additional "Team Requests" functionality for the TeamLeader
 */
public class UserTeamInformation implements Initializable {
    @FXML
    public TableView<TeamLeaderBoard> teamLeaderBoardTableView;
    public TableView<TeamMembers> teamMembersTableView;
    public TableView<TeamMembersRequest> teamRequestsTableView;
    public TableColumn<TeamLeaderBoard, String> teamName;
    public TableColumn<TeamLeaderBoard, Integer> teamPoints;
    public TableColumn<TeamMembers, String> teamMember;
    public TableColumn<TeamMembers, Integer> memberPoints;
    public TableColumn<TeamMembersRequest, Integer> requestId;
    public TableColumn<TeamMembersRequest, String> requestName;
    public Button backButton, approveButton;
    public Label requestLabel;
    private Stage stage;
    private ObservableList<TeamLeaderBoard> leaderBoardList = FXCollections.observableArrayList();
    private ObservableList<TeamMembers> membersList = FXCollections.observableArrayList();
    private ObservableList<TeamMembersRequest> requestsList = FXCollections.observableArrayList();
    private ObservableMap<String, Integer> membersHashMap = FXCollections.observableHashMap();

    /**
     * Associates table columns with required data
     * Calls getLeaderBoardData() getMembersData() and getRequestsData() methods in order to populate the lists
     * Sorts tablecolumns by specific terms
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!LoginController.getStudent().isTeamLeader()) {
            teamRequestsTableView.setVisible(false);
            requestLabel.setVisible(false);
            approveButton.setVisible(false);
        }
        getLeaderBoardData();
        getMembersData();
        getRequestsData();
        teamName.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
        teamMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        memberPoints.setCellValueFactory(new PropertyValueFactory<>("memberPoints"));
        requestId.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        requestName.setCellValueFactory(new PropertyValueFactory<>("requestName"));

        teamLeaderBoardTableView.setItems(leaderBoardList);
        teamMembersTableView.setItems(membersList);
        teamRequestsTableView.setItems(requestsList);
        memberPoints.setSortType(TableColumn.SortType.DESCENDING);
        teamPoints.setSortType(TableColumn.SortType.DESCENDING);
        teamMembersTableView.getSortOrder().clear();
        teamMembersTableView.getSortOrder().add(memberPoints);
        teamLeaderBoardTableView.getSortOrder().clear();
        teamLeaderBoardTableView.getSortOrder().add(teamPoints);

    }

    /**
     * Uses  for-each loops through all the teams and results in order to gain information about team scores
     */
    public void getLeaderBoardData() {
        for (Team team : LoginController.getTeamList()
                ) {
            leaderBoardList.add(new TeamLeaderBoard(team.getName(), 0));
        }
        for (Results result : LoginController.getResultsList()
                ) {
            if (result.getTeamName().length() > 1) {
                for (TeamLeaderBoard team : leaderBoardList
                        ) {
                    if (result.getTeamName().equals(team.getName())) {
                        team.addPoints(result.getScore());
                    }
                }
            }
        }
        for (TeamLeaderBoard team : leaderBoardList
                ) {
            if (team.getName().equals(LoginController.getStudent().getTeam().getName())) {
                teamLeaderBoardTableView.getSelectionModel().select(team);
                break;
            }
        }

    }

    /**
     * Uses for-each loops through all students and results in order to gain information about all team members
     */
    public void getMembersData() {
        for (Student student : LoginController.getStudentList()
                ) {
            if (student.getTeam().getName().equals(LoginController.getStudent().getTeam().getName())) {
                membersHashMap.put(student.getName(), 0);
            }
        }
        for (Results result : LoginController.getResultsList()
                ) {
            if (result.getTeamName().equals(LoginController.getStudent().getTeam().getName())) {
                membersHashMap.put(result.getName(), membersHashMap.getOrDefault(result.getName(), 0) + result.getScore());
            }

        }
        for (Map.Entry<String, Integer> mapEntry : membersHashMap.entrySet()
                ) {
            membersList.add(new TeamMembers(mapEntry.getKey(), mapEntry.getValue()));
        }
        for (TeamMembers member : membersList) {
            if (member.getMemberName().equals(LoginController.getStudent().getName())) {
                teamMembersTableView.getSelectionModel().select(member);
                break;
            }
        }

    }

    /**
     * Uses for-each loops through students in order to gain information about Team Requests from Students
     */
    public void getRequestsData() {
        Set<Student> studentSet = new HashSet<>();
        ArrayList<Student> studentArrayList = new ArrayList<>();
        for (Student student : LoginController.getTeamRequests()
                ) {
            if (student.getTeam().getName().equals(LoginController.getStudent().getTeam().getName())) {
                studentArrayList.add(student);
            }
        }
        studentSet.addAll(studentArrayList);
        studentArrayList.clear();
        studentArrayList.addAll(studentSet);
        for (Student student : studentArrayList
                ) {
            requestsList.add(new TeamMembersRequest(student.getId(), student.getName()));
        }
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
     * Uses for-each loops to assign a student to a team.
     * A no selection check is performed to avoid null-pointer exception
     * When team members is added, student's team request is deleted from team requests list using deleteTeamRequests() method
     */
    public void approveRequest() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        boolean selected = true;
        if (teamRequestsTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team assign error");
            alert.setHeaderText("No selection");
            alert.setContentText("You have not selected any member from the list. Please try again");
            alert.showAndWait();
            selected = false;
        }
        if (selected) {
            for (Student student : LoginController.getStudentList()
                    ) {
                if (student.getId() == teamRequestsTableView.getSelectionModel().getSelectedItem().getRequestId()) {
                    student.setTeam(LoginController.getStudent().getTeam());
                    for (Results results : LoginController.getResultsList()
                            ) {
                        if (student.getId() == results.getUserID()) {
                            results.setTeamName(LoginController.getStudent().getTeam().getName());
                            results.setTeamAbbreviation(LoginController.getStudent().getTeam().getAbbreviation());
                        }
                    }
                    try {
                        student.deleteTeamRequest();
                        Results.refresh();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team assign successful");
            alert.setHeaderText("Team member added");
            alert.setContentText("You have successfully added this member to your team");
            alert.showAndWait();
            leaderBoardList.clear();
            membersList.clear();
            requestsList.clear();
            getLeaderBoardData();
            getMembersData();
            getRequestsData();
        }
    }

}