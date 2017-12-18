package Controllers;/**
 * Created by Constantine on 10/23/2017.
 */

import Controllers.Student.RegisterController;
import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import org.mindrot.jbcrypt.BCrypt;
import sun.audio.AudioPlayer;
//import sun.jvm.hotspot.asm.Register;

public class LoginController implements Initializable {
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    private Button loginButton;

    private Stage stage;
    private static File musicFile = new File("src/assets/musicTheme.mp3");
    private static final String musicSource = musicFile.toURI().toString();
    static Media musicMedia = new Media(musicSource);
    static MediaPlayer musicPlayer = new MediaPlayer(musicMedia);
    public static final AudioClip soundPlayer = new AudioClip(AudioPlayer.class.getResource("/assets/press.mp3").toString());
    public static int menuID;
    public static Teacher teacher = new Teacher();
    public static Student student = new Student();
    public static ArrayList<Admin> adminList = new ArrayList();
    public static ArrayList<Student> studentList = new ArrayList();
    public static ArrayList<Teacher> teacherList = new ArrayList();
    public static ArrayList<Teacher> requestsList = new ArrayList();
    public static ArrayList<Results> resultsList = new ArrayList();
    public static ArrayList<SchoolClass> classList = new ArrayList();
    public static ArrayList<Question> questionsList = new ArrayList();
    public static ArrayList<Team> teamList = new ArrayList<>();
    public static ArrayList<Student> teamRequests = new ArrayList<>();






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
        }
        else if(result.get() == studentButton){
            RegisterController.isTeacher = false;
        }

        if(result.get() != cancelButton){
            // Get the current stage.
            stage = (Stage) loginButton.getScene().getWindow();
            // Change the current stage.
            GridPane root = FXMLLoader.load(getClass().getResource("/FXML/register.fxml"));
            Scene scene = new Scene(root, 1280, 720);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        }
    }



    public void checkCredentials(MouseEvent event) throws IOException, SQLException {
        boolean foundUser = false;
        String userPassword = "";
        for (Student student: studentList
             ) {
                if(student.getUsername().equals(username.getText())){
                    userPassword = student.getPassword();
                    foundUser=true;
                    menuID=0;
                    this.student = student;
                    break;
                }
        }

        if (!foundUser) {
            for (Teacher teacher: teacherList
                 ) {
                if (teacher.getUsername().equals(username.getText())){
                    userPassword = teacher.getPassword();
                    foundUser=true;
                    menuID=1;
                    this.teacher=teacher;
                    break;
                }
            }

        }
        if (!foundUser) {
            for (Admin admin: adminList
                 ) {
                if (admin.getUsername().equals(username.getText())) {
                    userPassword = admin.getPassword();
                    foundUser = true;
                    menuID = 2;
                    break;
                }
            }
        }
        if(foundUser) {
            if (BCrypt.checkpw(password.getText(), userPassword)) {
                Pane root=new Pane();
                if (menuID==0){root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));}
                else if(menuID==1){root = FXMLLoader.load(getClass().getResource("/FXML/teacherMainMenu.fxml"));
                }else if(menuID==2){root = FXMLLoader.load(getClass().getResource("/FXML/adminMainMenu.fxml"));}
                stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                root.getStyleClass().add("scene-background");
                scene.getStylesheets().add("/assets/css/menu.css");
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

    public void getData(){
        File students = new File("src/data/users/students.ser");
        File teachers = new File("src/data/users/teachers.ser");
        File admins = new File("src/data/users/admins.ser");
        File requests = new File("src/data/requests/teachers.ser");
        File results = new File("src/data/attributes/results.ser");
        File classes = new File("src/data/attributes/classes.ser");
        File questions = new File("src/data/attributes/questions.ser");
        File teams = new File("src/data/attributes/teams.ser");
        File teamRequest = new File("src/data/requests/students.ser");

        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(students);
            in = new ObjectInputStream(fileIn);
            studentList = (ArrayList) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(requests);
            in=new ObjectInputStream(fileIn);
            requestsList = (ArrayList) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            fileIn = new FileInputStream(admins);
            in=new ObjectInputStream(fileIn);
            adminList = (ArrayList) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(teachers);
            in=new ObjectInputStream(fileIn);
            teacherList = (ArrayList) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(results);
            in=new ObjectInputStream(fileIn);
            resultsList = (ArrayList) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(classes);
            in=new ObjectInputStream(fileIn);
            classList = (ArrayList) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(questions);
            in=new ObjectInputStream(fileIn);
            questionsList = (ArrayList) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(teams);
            in=new ObjectInputStream(fileIn);
            teamList = (ArrayList) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(teamRequest);
            in=new ObjectInputStream(fileIn);
            teamRequests = (ArrayList) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    }
