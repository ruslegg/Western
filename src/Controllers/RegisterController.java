package Controllers;

import Model.Student;
import Model.Teacher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RegisterController implements Initializable {




    private Stage stage;
    public static boolean isTeacher = false;
    ObservableList<String> classList = FXCollections.observableArrayList();
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
            Scanner classScanner = null;
            try {
                classScanner = new Scanner(new File("src/data/attributes/classes.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (classScanner.hasNextLine()) {
                String temp = classScanner.next();
                if (classList.isEmpty()) {
                    classList.add(temp);
                } else {
                    for (int i = 0; i < classList.size(); i++) {
                        if (classList.get(i).equals(temp)) {

                        } else {
                            classList.add(temp);
                        }
                    }
                }
                classScanner.nextLine();
            }
            classComboBox.setItems(classList);
            classComboBox.valueProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String t1) {
                    classLetterComboBox.setDisable(true);
                    classLetterComboBox.getItems().clear();
                    if (classComboBox.getSelectionModel().isEmpty()) {

                    } else {
                        classLetterComboBox.setDisable(false);
                        Scanner scanner = null;
                        try {
                            scanner = new Scanner(new File("src/data/attributes/classes.txt"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        while (scanner.hasNextLine()) {
                            String classNumber = scanner.next();
                            if (classNumber.equals(classComboBox.getSelectionModel().getSelectedItem())) {
                                String letter = scanner.next();
                                classLetterList.add(letter);
                            }
                        }
                        classLetterComboBox.setItems(classLetterList);
                    }
                }
            });
        }
    }

    public void registerAccount(MouseEvent event) throws IOException, SQLException {

        boolean usernameExist = false;
        boolean passwordShort = true;
        boolean usernameShort = true;
        Scanner scanner = new Scanner(new File("src/data/users/teachers.txt"));
        Scanner scanner1 = new Scanner(new File("src/data/users/students.txt"));
        String usernameTemp;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            usernameTemp = line.split("\\s+")[3];
            if (usernameTemp.equals(username.getText())) {
                usernameExist = true;
                break;
            }
        }
        if (!usernameExist) {
            while (scanner1.hasNextLine()) {
                String line = scanner1.nextLine();
                usernameTemp = line.split("\\s")[3];
                if (usernameTemp.equals(username.getText())) {
                    usernameExist = true;
                    break;
                }

            }
        }

            if (usernameExist) {
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
        if(!passwordShort){
                // Query(escaped) - insert the account into the database
                String hashedPassword = BCrypt.hashpw(password.getText(), BCrypt.gensalt());
                Scanner scanner2 = new Scanner(new File("src/data/users/students.txt"));
                if (isTeacher) {
                    scanner2 = new Scanner(new File("src/data/users/teachers.txt"));
                }
                int count = 0;
                while(scanner2.hasNextLine()) {
                    count++;
                    scanner2.nextLine();
                }
                if (isTeacher){
                    Teacher teacher = new Teacher(Integer.toString(count+1),firstName.getText()+" "+lastName.getText(),username.getText(),hashedPassword);
                    teacher.serialize();
                } else{
                    Student student = new Student(Integer.toString(count+1),firstName.getText()+" "+lastName.getText(),username.getText(),hashedPassword,classComboBox.getSelectionModel().getSelectedItem().toString()+classLetterComboBox.getSelectionModel().getSelectedItem().toString(),"");
                    student.serialize();
                }
                if (!isTeacher) {
                    stage = (Stage) signUpButton.getScene().getWindow();
                    Pane root;
                    root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    Scene scene = new Scene(root);
                    root.getStyleClass().add("scene-background");
                    scene.getStylesheets().add("/css/menu.css");
                    stage.setScene(scene);
                    if (SettingsController.effects) {
                        LoginController.soundPlayer.play();
                    }
                }
                else{
                    stage = (Stage) signUpButton.getScene().getWindow();
                    Pane root;
                    root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
                    Scene scene = new Scene(root);
                    root.getStyleClass().add("scene-background");
                    scene.getStylesheets().add("/css/menu.css");
                    stage.setScene(scene);
                    if (SettingsController.effects) {
                        LoginController.soundPlayer.play();
                    }
                }
            }
    }

    public void showLoginDialog(MouseEvent event) throws IOException, SQLException {
        // Get the current stage.
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        // Change the current stage.
        GridPane root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
