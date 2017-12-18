package Controllers.Student;
import Controllers.LoginController;
import Controllers.SettingsController;
import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private Stage stage;
    public static boolean isTeacher = false;
    public boolean inRequest=false;
    ArrayList<SchoolClass> classList = new ArrayList();
    ObservableList<Integer> classNumberList = FXCollections.observableArrayList();
    ObservableList<String> classLetterList = FXCollections.observableArrayList();

    @FXML
    private TextField firstName,lastName,username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox classComboBox;
    @FXML
    private ComboBox classLetterComboBox;
    @FXML
    private Button signUpButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        classLetterComboBox.setDisable(true);
        if (isTeacher){
            classComboBox.setDisable(true);
        }
        else {
            for (SchoolClass schoolClass: LoginController.classList
                 ) {
                if (classNumberList.isEmpty()){
                    classNumberList.add(schoolClass.getNumber());
                }
                else{
                    int checkNumber = 0;
                    for(int i=0;i<classNumberList.size();i++){
                        if (classNumberList.get(i) == schoolClass.getNumber()){
                            checkNumber++;
                        }
                        else{

                        }
                        if (i==classNumberList.size()-1 && checkNumber == 0 ){
                            classNumberList.add(schoolClass.getNumber());
                        }
                    }
                }
            }
        }
            classComboBox.setItems(classNumberList);
            classComboBox.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override public void changed(ObservableValue ov, Integer t, Integer t1) {
                    classLetterComboBox.setDisable(true);
                    classLetterComboBox.getItems().clear();
                    if (classComboBox.getSelectionModel().isEmpty()) {

                    } else {
                        classLetterComboBox.setDisable(false);
                        for (SchoolClass schoolClass : LoginController.classList
                                ) {
                            if (schoolClass.getNumber() == (Integer) classComboBox.getSelectionModel().getSelectedItem()) {
                                classLetterList.add(schoolClass.getLetter());
                            }
                        }

                    }
                    classLetterComboBox.setItems(classLetterList);
                }

            });
        }


    public void registerAccount(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        boolean usernameExist = false;
        boolean passwordShort = true;
        boolean usernameShort = true;
        for (Teacher teacher : LoginController.teacherList
                ) {
            if (teacher.getUsername().equals(username.getText())) {
                usernameExist = true;
                break;
            }
        }

        if (!usernameExist) {
            for (Student student : LoginController.studentList
                    ) {
                if (student.getUsername().equals(username.getText())) {
                    usernameExist = true;
                    break;
                }
            }
        }
        if (!usernameExist) {
            for (Admin admin : LoginController.adminList
                    ) {
                if (admin.getUsername().equals(username.getText())) {
                    usernameExist = true;
                    break;
                }

            }
        }

        if (!usernameExist) {
            for (Teacher teacher : LoginController.requestsList
                    ) {
                if (teacher.getUsername().equals(username.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error #6");
                    alert.setHeaderText("Request pending");
                    alert.setContentText("Your account request is still pending.");
                    alert.showAndWait();
                    usernameExist = true;
                    inRequest = true;
                    break;
                }
            }
        }


            if (usernameExist && !inRequest) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #1");
                alert.setHeaderText("Username is already used.");
                alert.setContentText("This username already exists in the database. Please use another username.");
                alert.showAndWait();
                username.clear();
            }
        if (!usernameExist) {
            // Insert the account in the database.
            if (username.getText().length() < 5) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #7");
                alert.setHeaderText("Username too short.");
                alert.setContentText("The username must have at least 5 characters.");
                alert.showAndWait();
                usernameShort = true;
                username.clear();
            } else {
                usernameShort = false;
            }
        }
        if(!usernameShort) {
            if (password.getText().length() < 5) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #5");
                alert.setHeaderText("Password too short.");
                alert.setContentText("The password must have at least 5 characters.");
                alert.showAndWait();
                passwordShort=true;
                password.clear();
            }else {
                passwordShort=false;
            }
        }
        if(!RegisterController.isTeacher) {
            if(classLetterComboBox.isDisabled()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #5");
                alert.setHeaderText("Class not selected.");
                alert.setContentText("You must select the class you belong to.");
                alert.showAndWait();
                passwordShort = true;
            }
            else if(classLetterComboBox.getSelectionModel().getSelectedItem() == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #5");
                alert.setHeaderText("Class letter not selected.");
                alert.setContentText("You must select your class letter.");
                alert.showAndWait();
                passwordShort = true;
            }

        }

        if(!passwordShort){
                String hashedPassword = BCrypt.hashpw(password.getText(), BCrypt.gensalt());
                if (isTeacher){
                    Teacher teacher = new Teacher(0,firstName.getText()+" "+lastName.getText(),username.getText(),hashedPassword);
                    teacher.serializeRequest();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Teacher registration");
                    alert.setHeaderText("Registration successful");
                    alert.setContentText("You are now registered in the system. Administrator needs to approve your request before you can log in.");
                    alert.showAndWait();
                } else{
                    SchoolClass tempSchoolClass = new SchoolClass();
                    for (SchoolClass schoolClass : LoginController.classList) {
                        if (schoolClass.getNumber() == (Integer) classComboBox.getSelectionModel().getSelectedItem() && schoolClass.getLetter().equals(classLetterComboBox.getSelectionModel().getSelectedItem())){
                            tempSchoolClass = schoolClass;
                        }
                    }
                    Student student = new Student(LoginController.studentList.size(),firstName.getText()+" "+lastName.getText(),username.getText(),hashedPassword,new Team("",""),tempSchoolClass);
                    student.serialize();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User registration");
                    alert.setHeaderText("Registration successful");
                    alert.setContentText("You are now registered in the system.");
                    alert.showAndWait();
                }
                    stage = (Stage) signUpButton.getScene().getWindow();
                    GridPane root;
                    root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
                    Scene scene = new Scene(root);
                    root.getStyleClass().add("scene-background");
                    scene.getStylesheets().add("/assets/css/menu.css");
                    stage.setScene(scene);
                    if (SettingsController.effects) {
                        LoginController.soundPlayer.play();
                    }
                }
            }



    public void showLoginDialog(MouseEvent event) throws IOException, SQLException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        GridPane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        stage = (Stage) signUpButton.getScene().getWindow();
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

}

