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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller that gives an admin a small overview over the teachers, such as: All the teachers,
 * how many competitions and contests has made each teacher, and Teacher's Register requests that admin needs to approve.
 *
 */
public class AdminTeacherSettings implements Initializable {
    @FXML
    public TableView<AdminRequest> teacherRequestsTableView;
    public TableView<AdminStatistics> teacherTableView;
    public TableColumn<AdminRequest, String> name;
    public TableColumn<AdminStatistics, String> teacherName, teacherCompetitions, teacherContests;
    public Button approveButton, backButton, deleteButton;
    private Stage stage;
    private ObservableList<AdminRequest> requests = FXCollections.observableArrayList();
    private ObservableList<AdminStatistics> teachers = FXCollections.observableArrayList();

    /**
     * Retrieves data using getData() method. Associates data with table columns. Disables approve and delete button if
     * none teacher request has been chosen from the TableView.
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        name.setCellValueFactory(new PropertyValueFactory<>("teacherRequestName"));
        teacherName.setCellValueFactory(new PropertyValueFactory<>("teacherTableViewName"));
        teacherCompetitions.setCellValueFactory(new PropertyValueFactory<>("teacherTableViewCompetitionNumber"));
        teacherContests.setCellValueFactory(new PropertyValueFactory<>("teacherTableViewContestNumber"));
        teacherRequestsTableView.setItems(requests);
        teacherTableView.setItems(teachers);
        if (teacherRequestsTableView.getSelectionModel().isEmpty()) {
            approveButton.setDisable(true);
            deleteButton.setDisable(true);
        }
        teacherRequestsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                approveButton.setDisable(false);
                deleteButton.setDisable(false);
            } else {
                approveButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        });
    }

    /**
     * Populates lists through for-loops.
     * Using a for-loop increments how many competitions and contests has generated each teacher.
     */
    public void getData() {
        for (int i = 0; i < LoginController.getRequestsList().size(); i++) {
            requests.add(new AdminRequest(LoginController.getRequestsList().get(i).getName()));
        }
        for (int i = 0; i < LoginController.getTeacherList().size(); i++) {
            teachers.add(new AdminStatistics(LoginController.getTeacherList().get(i).getId(), LoginController.getTeacherList().get(i).getName(), 0, 0));
        }
        for (int i = 0; i < LoginController.getResultsList().size(); i++) {
            for (int x = 0; x < teachers.size(); x++) {
                if (teachers.get(x).getTeacherTableViewId() == LoginController.getResultsList().get(i).getTeacherID()) {
                    if (LoginController.getResultsList().get(i).getType() == 0) {
                        teachers.get(x).incrementCompetition();
                    } else if (LoginController.getResultsList().get(i).getType() == 1) {
                        teachers.get(x).incrementContest();
                    }
                }
            }
        }
    }

    /**
     * Parsing through all Teacher Requests List from LoginController with a for loop to identify what specific request
     * has been chosen from the TableView. After teacher found, Teacher is serialized and request is deleted from Requests List
     * and selected teacher now can login. Refreshes the lists using refresh() method in order to avoid conflicts and exceptions.
     * @throws IOException exception caused by serialization
     * @throws ClassNotFoundException exception cause by serialization
     */
    public void approveRequest() throws IOException, ClassNotFoundException {
        for (int i = 0; i < LoginController.getRequestsList().size(); i++) {
            if (teacherRequestsTableView.getSelectionModel().getSelectedItem().getTeacherRequestName().equals(LoginController.getRequestsList().get(i).getName())) {
                Teacher teacher = new Teacher(LoginController.getTeacherList().size(), LoginController.getRequestsList().get(i).getName(), LoginController.getRequestsList().get(i).getUsername(), LoginController.getRequestsList().get(i).getPassword());
                teacher.serialize();
                teacher.deleteRequest();
            }
        }
        refresh();
    }

    /**
     * Parsing through all Teacher Requests from List with a for loop to identify what specific request has been chosen from the
     * TableView. After teacher found, request is deleted from the Teacher Requests Lists.
     * @throws IOException exception caused by serialization
     * @throws ClassNotFoundException exception caused by serialization
     */
    public void deleteRequest() throws IOException, ClassNotFoundException {
        for (int i = 0; i < LoginController.getRequestsList().size(); i++) {
            if (teacherRequestsTableView.getSelectionModel().getSelectedItem().getTeacherRequestName().equals(LoginController.getRequestsList().get(i).getName())) {
                Teacher teacher = new Teacher(LoginController.getTeacherList().size(), LoginController.getRequestsList().get(i).getName(), LoginController.getRequestsList().get(i).getUsername(), LoginController.getRequestsList().get(i).getPassword());
                teacher.deleteRequest();
            }
        }
        refresh();
    }

    /**
     * Switches to Admin's Main Menu Scene
     */
    public void toAdminMainMenu(){
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/adminMainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /**
     * Clear all the lists and retrieves the data again in order to avoid conflicts.
     */
    public void refresh() {
        teachers.clear();
        requests.clear();
        getData();
        teacherRequestsTableView.setItems(requests);
        teacherTableView.setItems(teachers);
    }


}
