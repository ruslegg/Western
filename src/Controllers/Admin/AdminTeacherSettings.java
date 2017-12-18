package Controllers.Admin;
import Controllers.LoginController;
import Model.TableView.AdminRequest;
import Model.TableView.AdminStatistics;
import Model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminTeacherSettings implements Initializable {
    @FXML
    private TableView<AdminRequest> teacherRequestsTableView;
    @FXML
    private TableView<AdminStatistics> teacherTableView;
    @FXML
    private TableColumn<AdminRequest, String> name;
    @FXML
    private TableColumn<AdminStatistics, String> teacherName, teacherCompetitions, teacherContests;
    @FXML
    private Button approveButton, backButton, deleteButton;

    ObservableList<AdminRequest> requests = FXCollections.observableArrayList();
    ObservableList<AdminStatistics> teachers = FXCollections.observableArrayList();

    private Stage stage;


    public void approveRequest(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        for (int i = 0; i < LoginController.requestsList.size(); i++) {
            if (teacherRequestsTableView.getSelectionModel().getSelectedItem().getTeacherRequestName().equals(LoginController.requestsList.get(i).getName())) {
                Teacher teacher = new Teacher(LoginController.teacherList.size(), LoginController.requestsList.get(i).getName(), LoginController.requestsList.get(i).getUsername(), LoginController.requestsList.get(i).getPassword());
                teacher.serialize();
                teacher.deleteRequest();
            }
        }
        refresh();
    }

    public void deleteRequest(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        for (int i = 0; i < LoginController.requestsList.size(); i++) {
            if (teacherRequestsTableView.getSelectionModel().getSelectedItem().getTeacherRequestName().equals(LoginController.requestsList.get(i).getName())) {
                Teacher teacher = new Teacher(LoginController.teacherList.size(), LoginController.requestsList.get(i).getName(), LoginController.requestsList.get(i).getUsername(), LoginController.requestsList.get(i).getPassword());
                teacher.deleteRequest();
            }
        }
        refresh();
    }


    public void toAdminMainMenu(MouseEvent mouseEvent) throws IOException {
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/adminMainMenu.fxml"));
        stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(root, 1280, 720);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        name.setCellValueFactory(new PropertyValueFactory<>("teacherRequestName"));
        teacherName.setCellValueFactory(new PropertyValueFactory<>("teacherTableViewName"));
        teacherCompetitions.setCellValueFactory(new PropertyValueFactory<>("teacherTableViewCompetitionNumber"));
        teacherContests.setCellValueFactory(new PropertyValueFactory<>("teacherTableViewContestNumber"));
        teacherRequestsTableView.setItems(requests);
        teacherTableView.setItems(teachers);
        if (teacherRequestsTableView.getSelectionModel().isEmpty()){
            approveButton.setDisable(true);
            deleteButton.setDisable(true);
        }
        teacherRequestsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                approveButton.setDisable(false);
                deleteButton.setDisable(false);
            }
            else{
                approveButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        });
    }





    public void getData() {
        for (int i = 0; i < LoginController.requestsList.size(); i++) {
            requests.add(new AdminRequest(LoginController.requestsList.get(i).getName()));
        }
        for (int i = 0; i < LoginController.teacherList.size(); i++) {
            teachers.add(new AdminStatistics(LoginController.teacherList.get(i).getId(), LoginController.teacherList.get(i).getName(), 0, 0));
        }
        for (int i = 0; i < LoginController.resultsList.size(); i++) {
            for (int x = 0; x < teachers.size(); x++) {
                if (teachers.get(x).getTeacherTableViewId() == LoginController.resultsList.get(i).getTeacherID()) {
                    if (LoginController.resultsList.get(i).getType() == 0) {
                        teachers.get(x).incrementCompetition();
                    } else if (LoginController.resultsList.get(i).getType() == 1) {
                        teachers.get(x).incrementContest();
                    }
                }
            }
        }
    }
    public void refresh(){
        teachers.clear();
        requests.clear();
        getData();
        teacherRequestsTableView.setItems(requests);
        teacherTableView.setItems(teachers);
    }

}
