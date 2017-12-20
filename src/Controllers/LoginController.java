package Controllers;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import assets.jbcrypt.BCrypt;
import sun.audio.AudioPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller that responds for the student,teacher or admin login to the system.
 */
public class LoginController implements Initializable {
    private Stage stage;

    private static File musicFile = new File("src/assets/sounds/musicTheme.mp3");
    private static final String musicSource = musicFile.toURI().toString();
    static Media musicMedia = new Media(musicSource);
    static MediaPlayer musicPlayer = new MediaPlayer(musicMedia);
    public static final AudioClip soundPlayer = new AudioClip(AudioPlayer.class.getResource("/assets/sounds/press.mp3").toString());

    @FXML
    public TextField username;
    public PasswordField password;
    public Button loginButton;


    private static int menuID;
    private static Teacher teacher = new Teacher();
    private static Student student = new Student();
    private static ArrayList<Admin> adminList = new ArrayList();
    private static ArrayList<Student> studentList = new ArrayList();
    private static ArrayList<Teacher> teacherList = new ArrayList();
    private static ArrayList<Teacher> requestsList = new ArrayList();
    private static ArrayList<Results> resultsList = new ArrayList();
    private static ArrayList<SchoolClass> classList = new ArrayList();
    private static ArrayList<Question> questionsList = new ArrayList();
    private static ArrayList<Team> teamList = new ArrayList<>();
    private static ArrayList<Student> teamRequests = new ArrayList<>();

    /**
     * Inserts into ArrayLists information from serialized files and starts to play music theme before the GUI has loaded.
     * @param location - not used
     * @param resources - not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.play();
    }

    /**
     * Retrieves information from serialized file into local ArrayList in order to get the information
     */
    public void getData() {
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
            in = new ObjectInputStream(fileIn);
            requestsList = (ArrayList) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            fileIn = new FileInputStream(admins);
            in = new ObjectInputStream(fileIn);
            adminList = (ArrayList) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(teachers);
            in = new ObjectInputStream(fileIn);
            teacherList = (ArrayList) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(results);
            in = new ObjectInputStream(fileIn);
            resultsList = (ArrayList) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(classes);
            in = new ObjectInputStream(fileIn);
            classList = (ArrayList) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(questions);
            in = new ObjectInputStream(fileIn);
            questionsList = (ArrayList) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(teams);
            in = new ObjectInputStream(fileIn);
            teamList = (ArrayList) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(teamRequest);
            in = new ObjectInputStream(fileIn);
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

    /**
     * Checks if credentials from username and the password textfields matches with one of the Student, Admin or Teacher in ArrayLists.
     *
     */
    public void checkCredentials(){
        boolean foundUser = false;
        String userPassword = "";
        for (Student student : studentList
                ) {
            if (student.getUsername().equals(username.getText())) {
                userPassword = student.getPassword();
                foundUser = true;
                menuID = 0;
                LoginController.student = student;
                break;
            }
        }

        if (!foundUser) {
            for (Teacher teacher : teacherList
                    ) {
                if (teacher.getUsername().equals(username.getText())) {
                    userPassword = teacher.getPassword();
                    foundUser = true;
                    menuID = 1;
                    LoginController.teacher = teacher;
                    break;
                }
            }

        }
        if (!foundUser) {
            for (Admin admin : adminList
                    ) {
                if (admin.getUsername().equals(username.getText())) {
                    userPassword = admin.getPassword();
                    foundUser = true;
                    menuID = 2;
                    break;
                }
            }
        }
        if (foundUser) {
            if (BCrypt.checkpw(password.getText(), userPassword)) {
                VBox root = new VBox();
                if (menuID == 0) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (menuID == 1) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/View/teacherMainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (menuID == 2) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/View/adminMainMenu.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                root.getStyleClass().add("scene-background");
                scene.getStylesheets().add("/assets/css/menu.css");
                stage.setScene(scene);
                if (SettingsController.effects) {
                    LoginController.soundPlayer.play();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #3");
                alert.setHeaderText("Invalid credentials.");
                alert.setContentText("Username and password doesn't match.");
                alert.showAndWait();
                foundUser = false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error #2");
            alert.setHeaderText("Invalid credentials.");
            alert.setContentText("This username doesn't exist in our database.");
            alert.showAndWait();
            foundUser = false;
        }


    }

    /**
     * Pop-up an information alert whether you want to register as a student or as a teacher. The choice is forwarded to RegisterController
     * and changes scene to Register Scene.
     */
    public void showRegisterDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("User type");
        alert.setHeaderText("");
        alert.setContentText("Are you a teacher or a student?");
        ButtonType teacherButton = new ButtonType("Teacher");
        ButtonType studentButton = new ButtonType("Student");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(teacherButton, studentButton, cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == teacherButton) {
            RegisterController.setTeacher(true);
        } else if (result.get() == studentButton) {
            RegisterController.setTeacher(false);
        }

        if (result.get() != cancelButton) {
            // Get the current stage.
            stage = (Stage) loginButton.getScene().getWindow();
            // Change the current stage.
            VBox root = new VBox();
            try {
                root = FXMLLoader.load(getClass().getResource("/View/register.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        }
    }


    /**
     *
     * @return Returns the ArrayList with all admins.
     */
    public static ArrayList<Admin> getAdminList() {
        return adminList;
    }

    /**
     * Replace current admin ArrayList with new one.
     * @param adminList New Admin List
     */
    public static void setAdminList(ArrayList<Admin> adminList) {
        LoginController.adminList = adminList;
    }

    /**
     *
     * @return Returns the ArrayList with all students.
     */
    public static ArrayList<Student> getStudentList() {
        return studentList;
    }

    /**
     * Replace current student ArrayList with new one.
     * @param studentList New Student List
     */
    public static void setStudentList(ArrayList<Student> studentList) {
        LoginController.studentList = studentList;
    }

    /**
     *
     * @return Returns the ArrayList with all teachers.
     */
    public static ArrayList<Teacher> getTeacherList() {
        return teacherList;
    }

    /**
     * Replace current teacher ArrayList with new one.
     * @param teacherList New Teacher List
     */
    public static void setTeacherList(ArrayList<Teacher> teacherList) {
        LoginController.teacherList = teacherList;
    }
    /**
     *
     * @return Returns the ArrayList with all teachers requests sent to admin.
     */
    public static ArrayList<Teacher> getRequestsList() {
        return requestsList;
    }
    /**
     * Replace current teacher request ArrayList with new one.
     * @param requestsList New Teacher Request List
     */
    public static void setRequestsList(ArrayList<Teacher> requestsList) {
        LoginController.requestsList = requestsList;
    }
    /**
     *
     * @return Returns the ArrayList with all student results.
     */
    public static ArrayList<Results> getResultsList() {
        return resultsList;
    }
    /**
     * Replace current student results ArrayList with new one.
     * @param resultsList New Results List
     */
    public static void setResultsList(ArrayList<Results> resultsList) {
        LoginController.resultsList = resultsList;
    }
    /**
     *
     * @return Returns the ArrayList with all school classes created by teacher.
     */
    public static ArrayList<SchoolClass> getClassList() {
        return classList;
    }
    /**
     * Replace current school classes ArrayList with new one.
     * @param classList New School Class List
     */
    public static void setClassList(ArrayList<SchoolClass> classList) {
        LoginController.classList = classList;
    }
    /**
     *
     * @return Returns the ArrayList with all questions generated by teachers.
     */
    public static ArrayList<Question> getQuestionsList() {
        return questionsList;
    }
    /**
     * Replace current generated questions ArrayList with new one.
     * @param questionsList New Question List
     */
    public static void setQuestionsList(ArrayList<Question> questionsList) {
        LoginController.questionsList = questionsList;
    }
    /**
     *
     * @return Returns the ArrayList with all teams created by students.
     */
    public static ArrayList<Team> getTeamList() {
        return teamList;
    }
    /**
     * Replace current student teams ArrayList with new one.
     * @param teamList New Team List
     */
    public static void setTeamList(ArrayList<Team> teamList) {
        LoginController.teamList = teamList;
    }
    /**
     *
     * @return Returns the ArrayList with all student requests to team.
     */
    public static ArrayList<Student> getTeamRequests() {
        return teamRequests;
    }
    /**
     * Replace current student team requests ArrayList with new one.
     * @param teamRequests New Team Requests List
     */
    public static void setTeamRequests(ArrayList<Student> teamRequests) {
        LoginController.teamRequests = teamRequests;
    }
    /**
     *
     * @return Returns the teacher that is logged in.
     */
    public static Teacher getTeacher() {
        return teacher;
    }
    /**
     * Replace current teacher with the one logged in.
     * @param teacher New Teacher
     */
    public static void setTeacher(Teacher teacher) {
        LoginController.teacher = teacher;
    }
    /**
     *
     * @return Returns the student that is logged in.
     */
    public static Student getStudent() {
        return student;
    }
    /**
     * Replace current student with the one logged in.
     * @param student New Teacher List
     */
    public static void setStudent(Student student) {
        LoginController.student = student;
    }
    /**
     *
     * @return  Returns the MenuID that LoginController will use to check which scene to switch after checking the credentials.
     * Whether is Admin, Teacher or Student Main Menu.
     */
    public static int getMenuID() {
        return menuID;
    }

}
