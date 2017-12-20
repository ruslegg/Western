package Controllers;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import assets.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller that responds for the registration of users and teachers using several checks to avoid duplicates.
 */
public class RegisterController implements Initializable {
    private Stage stage;

    @FXML
    public TextField firstName, lastName, username;
    public PasswordField password;
    public ComboBox classComboBox, classLetterComboBox;
    public Button signUpButton;

    private static boolean teacher = false;
    private boolean inRequest = false;
    private ObservableList<Integer> classNumberList = FXCollections.observableArrayList();
    private ObservableList<String> classLetterList = FXCollections.observableArrayList();


    /**
     * Checks whether the user that is registering is a teacher or not, and disabling some fields if is a teacher and
     * add school classes if not.
     * Uses a listener whenever the user desire to change the value from School Class number, so the School Class letters
     * can be updated to new chosen School Class number.
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        classLetterComboBox.setDisable(true);
        if (teacher) {
            classComboBox.setDisable(true);
        } else {
            for (SchoolClass schoolClass : LoginController.getClassList()
                    ) {
                if (classNumberList.isEmpty()) {
                    classNumberList.add(schoolClass.getNumber());
                } else {
                    int checkNumber = 0;
                    for (int i = 0; i < classNumberList.size(); i++) {
                        if (classNumberList.get(i) == schoolClass.getNumber()) {
                            checkNumber++;
                        }
                        if (i == classNumberList.size() - 1 && checkNumber == 0) {
                            classNumberList.add(schoolClass.getNumber());
                        }
                    }
                }
            }
        }
        classComboBox.setItems(classNumberList);
        classComboBox.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue ov, Integer t, Integer t1) {
                classLetterComboBox.setDisable(true);
                classLetterComboBox.getItems().clear();
                if (classComboBox.getSelectionModel().isEmpty()) {

                } else {
                    classLetterComboBox.setDisable(false);
                    for (SchoolClass schoolClass : LoginController.getClassList()
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

    /**
     * Several checks are performed in order to avoid conflicts and duplicates to database, such as:
     * If username is too short
     * If username exists
     * If password is too short
     * If teacher request is already sent
     * If fields not completed
     * Whether is a teacher or a student, that will decide which user should be created and serialized to the system.
     * @throws IOException - caused by serialization
     */
    public void registerAccount() throws IOException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        boolean usernameExist = false;
        boolean passwordShort = true;
        boolean usernameShort = true;
        for (Teacher teacher : LoginController.getTeacherList()
                ) {
            if (teacher.getUsername().equals(username.getText())) {
                usernameExist = true;
                break;
            }
        }

        if (!usernameExist) {
            for (Student student : LoginController.getStudentList()
                    ) {
                if (student.getUsername().equals(username.getText())) {
                    usernameExist = true;
                    break;
                }
            }
        }
        if (!usernameExist) {
            for (Admin admin : LoginController.getAdminList()
                    ) {
                if (admin.getUsername().equals(username.getText())) {
                    usernameExist = true;
                    break;
                }

            }
        }
        if (!usernameExist) {
            for (Teacher teacher : LoginController.getRequestsList()
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
        if (!usernameShort) {
            if (password.getText().length() < 5) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #5");
                alert.setHeaderText("Password too short.");
                alert.setContentText("The password must have at least 5 characters.");
                alert.showAndWait();
                passwordShort = true;
                password.clear();
            } else {
                passwordShort = false;
            }
        }
        if (!RegisterController.teacher) {
            if (classLetterComboBox.isDisabled()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #5");
                alert.setHeaderText("Class not selected.");
                alert.setContentText("You must select the class you belong to.");
                alert.showAndWait();
                passwordShort = true;
            } else if (classLetterComboBox.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #5");
                alert.setHeaderText("Class letter not selected.");
                alert.setContentText("You must select your class letter.");
                alert.showAndWait();
                passwordShort = true;
            }

        }

        if (!passwordShort) {
            String hashedPassword = BCrypt.hashpw(password.getText(), BCrypt.gensalt());
            if (teacher) {
                Teacher teacher = new Teacher(0, firstName.getText() + " " + lastName.getText(), username.getText(), hashedPassword);
                try {
                    teacher.serializeRequest();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Teacher registration");
                alert.setHeaderText("Registration successful");
                alert.setContentText("You are now registered in the system. Administrator needs to approve your request before you can log in.");
                alert.showAndWait();
            } else {
                SchoolClass tempSchoolClass = new SchoolClass();
                for (SchoolClass schoolClass : LoginController.getClassList()) {
                    if (schoolClass.getNumber() == (Integer) classComboBox.getSelectionModel().getSelectedItem() && schoolClass.getLetter().equals(classLetterComboBox.getSelectionModel().getSelectedItem())) {
                        tempSchoolClass = schoolClass;
                    }
                }
                Student student = new Student(LoginController.getStudentList().size(), firstName.getText() + " " + lastName.getText(), username.getText(), hashedPassword, new Team("", ""), tempSchoolClass);
                student.serialize();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User registration");
                alert.setHeaderText("Registration successful");
                alert.setContentText("You are now registered in the system.");
                alert.showAndWait();
            }
            stage = (Stage) signUpButton.getScene().getWindow();
            VBox root;
            root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
            if (SettingsController.effects) {
                LoginController.soundPlayer.play();
            }
        }
    }

    /**
     * Switches Login Scene
     */
    public void showLoginDialog(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) signUpButton.getScene().getWindow();
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }



    public static boolean isTeacher() {
        return teacher;
    }

    public static void setTeacher(boolean teacher) {
        RegisterController.teacher = teacher;
    }
}

