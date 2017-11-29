package Controllers;/**
 * Created by Constantine on 10/23/2017.
 */

import Model.Student;
import Model.Teacher;
import com.mysql.jdbc.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import includes.MYSQL;
import org.mindrot.jbcrypt.BCrypt;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.*;

public class LoginController implements Initializable {
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    private Button loginButton;

    private Stage stage;
    private static File musicFile=new File("src/assets/musicTheme.mp3");
    private static final String musicSource=musicFile.toURI().toString();
    static Media musicMedia = new Media(musicSource);
    static MediaPlayer musicPlayer = new MediaPlayer(musicMedia);
    public static final AudioClip soundPlayer = new AudioClip(AudioPlayer.class.getResource("/assets/press.mp3").toString());
    public int userID;
    public int menuID;




    public void showRegisterDialog(MouseEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("User type");
        alert.setHeaderText("");
        alert.setContentText("Are you a teacher or a student?");
        ButtonType teacherButton = new ButtonType("Teacher");
        ButtonType studentButton = new ButtonType("Student");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(teacherButton, studentButton, cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == teacherButton){
            RegisterController.isTeacher=true;
        } else if (result.get() == studentButton){
        } else {}
        // Get the current stage.
        stage = (Stage) loginButton.getScene().getWindow();
        // Change the current stage.
        GridPane root = FXMLLoader.load(getClass().getResource("/FXML/register.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }



    public void checkCredentials(MouseEvent event) throws IOException, SQLException {
        boolean foundUser = false;
        String userPassword = "";
        String line, usernameCheck;
        Scanner studentScanner = new Scanner(new File("src/data/users/students.txt"));
        Scanner teacherScanner = new Scanner(new File("src/data/users/teachers.txt"));
        Scanner adminScanner = new Scanner(new File("src/data/users/admins.txt"));
        while (studentScanner.hasNextLine()) {
            line = studentScanner.nextLine();
            usernameCheck = line.split("\\s")[3];
            if (usernameCheck.equals(username.getText())) {
                userPassword = line.split("\\s")[4];
                foundUser = true;
                menuID=0;
                break;
            }

        }
        if (!foundUser) {
            while (teacherScanner.hasNextLine()) {
                line = teacherScanner.nextLine();
                usernameCheck = line.split("\\s")[3];
                if (usernameCheck.equals(username.getText())) {
                    userPassword = line.split("\\s")[4];
                    userID=Integer.valueOf(line.split("\\s")[0]);
                    menuID=1;
                    foundUser = true;
                    break;
                }
            }
        }
        if (!foundUser) {
            while (adminScanner.hasNextLine()) {
                line = adminScanner.nextLine();
                usernameCheck = line.split("\\s")[3];
                if (usernameCheck.equals(username.getText())) {
                    userPassword = line.split("\\s")[4];
                    foundUser = true;
                    menuID=2;
                    break;
                }
            }
        }
        if(foundUser) {
            if (BCrypt.checkpw(password.getText(), userPassword)) {
                Pane root=new Pane();
                if (menuID==0){root = FXMLLoader.load(getClass().getResource("/FXML/studentMainMenu.fxml"));}
                else if(menuID==1){root = FXMLLoader.load(getClass().getResource("/FXML/teacherMainMenu.fxml"));
                }else if(menuID==2){root = FXMLLoader.load(getClass().getResource("/FXML/adminMainMenu.fxml"));}
                GenerateQuestionsController.setTeacherID(String.valueOf(userID));
                stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                root.getStyleClass().add("scene-background");
                scene.getStylesheets().add("/css/menu.css");
                stage.setScene(scene);
                if (SettingsController.effects) {
                    LoginController.soundPlayer.play();
                }
            } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #3");
                alert.setHeaderText("Invalid credentials.");
                alert.setContentText("Username and password doesn't match.");
                alert.showAndWait();
                foundUser=false;
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error #2");
            alert.setHeaderText("Invalid credentials.");
            alert.setContentText("This username doesn't exist in our database.");
            alert.showAndWait();
            foundUser=false;
        }



    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.play();
    }

    }
