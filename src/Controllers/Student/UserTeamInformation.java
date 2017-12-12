package Controllers.Student;

import Controllers.LoginController;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class UserTeamInformation implements Initializable {
    private Stage stage;

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

    ObservableList<TeamLeaderBoard> leaderBoardList = FXCollections.observableArrayList();
    ObservableList<TeamMembers> membersList = FXCollections.observableArrayList();
    ObservableList<TeamMembersRequest> requestsList = FXCollections.observableArrayList();
    ObservableMap<String, Integer> membersHashMap = FXCollections.observableHashMap();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!LoginController.student.isTeamLeader()){
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

    public void getLeaderBoardData() {
        for (Team team : LoginController.teamList
                ) {
            leaderBoardList.add(new TeamLeaderBoard(team.getName(), 0));
        }
        for (Results result : LoginController.resultsList
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
            if (team.getName().equals(LoginController.student.getTeam().getName())) {
                teamLeaderBoardTableView.getSelectionModel().select(team);
                break;
            }
        }

    }

    public void getMembersData() {
        for (Student student: LoginController.studentList
             ) {
            if (student.getTeam().getName().equals(LoginController.student.getTeam().getName())){
                membersHashMap.put(student.getName(),0);
            }
        }
        for (Results result : LoginController.resultsList
                ) {
            if (result.getTeamName().equals(LoginController.student.getTeam().getName())) {
                membersHashMap.put(result.getName(), membersHashMap.getOrDefault(result.getName(), 0) + result.getScore());
            }

        }
        for (Map.Entry<String, Integer> mapEntry : membersHashMap.entrySet()
                ) {
            membersList.add(new TeamMembers(mapEntry.getKey(), mapEntry.getValue()));
        }
        for (TeamMembers member : membersList) {
            if (member.getMemberName().equals(LoginController.student.getName())) {
                teamMembersTableView.getSelectionModel().select(member);
                break;
            }
        }

    }

    public void getRequestsData() {
        Set<Student> studentSet = new HashSet<>();
        ArrayList<Student> studentArrayList = new ArrayList<>();
        for (Student student : LoginController.teamRequests
                ) {
            if (student.getTeam().getName().equals(LoginController.student.getTeam().getName())) {
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

    public void toMainMenu() throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    public void approveRequest() {
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
            for (Student student : LoginController.studentList
                    ) {
                if (student.getId() == teamRequestsTableView.getSelectionModel().getSelectedItem().getRequestId()) {
                    student.setTeam(LoginController.student.getTeam());
                    try {
                        student.deleteTeamRequest();
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

