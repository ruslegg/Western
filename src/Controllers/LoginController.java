package Controllers;/**
 * Created by Constantine on 10/23/2017.
 */

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import includes.MYSQL;
import org.mindrot.jbcrypt.BCrypt;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.*;

public class LoginController implements Initializable {

    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    private CheckBox accountType;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;

    private Stage stage;
    private static File musicFile=new File("src/assets/musicTheme.mp3");
    private static final String musicSource=musicFile.toURI().toString();
    static Media musicMedia = new Media(musicSource);
    static MediaPlayer musicPlayer = new MediaPlayer(musicMedia);
    public static final AudioClip soundPlayer = new AudioClip(AudioPlayer.class.getResource("/assets/press.mp3").toString());



    public void showRegisterDialog(MouseEvent event) throws IOException, SQLException {
        // Get the current stage.
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        // Change the current stage.
        GridPane root = FXMLLoader.load(getClass().getResource("/FXML/register.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void showLoginDialog(MouseEvent event) throws IOException, SQLException {
        // Get the current stage.
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        // Change the current stage.
        GridPane root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void checkCredentials(MouseEvent event) throws IOException, SQLException {
        Connection connHandle = MYSQL.getConnection();

        PreparedStatement checkCredentialsQuery = connHandle.prepareStatement("SELECT `password` FROM `users` WHERE `username` = ? LIMIT 1");
        checkCredentialsQuery.setString(1, username.getText());

        ResultSet rs = checkCredentialsQuery.executeQuery();

        if(rs.next() && username.getText().length() > 4 && password.getText().length() > 4){
            String userPassword = rs.getString("password");

            if (BCrypt.checkpw(password.getText(), userPassword)){
                stage = (Stage) loginButton.getScene().getWindow();
                Pane root;
                root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                if (SettingsController.effects){
                    LoginController.soundPlayer.play();
                }
                SettingsController.username = username.getText();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #3");
                alert.setHeaderText("Invalid credentials.");
                alert.setContentText("Username and password doesn't match.");

                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error #2");
            alert.setHeaderText("Invalid credentials.");
            alert.setContentText("This username doesn't exist in our database.");

            alert.showAndWait();
        }



    }

    public void registerAccount(MouseEvent event) throws IOException, SQLException {
        Connection connHandle = MYSQL.getConnection();

        // Query(escaped) - check if the username already exists in the database.
        PreparedStatement checkUserQuery = connHandle.prepareStatement("SELECT * FROM `users` WHERE `username` = ? LIMIT 1");
        checkUserQuery.setString(1, username.getText());

        // Store the result.
        ResultSet rs = checkUserQuery.executeQuery();

        // If the username already exists in our database the query will return a row.
        if(rs.next()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error #1");
            alert.setHeaderText("Username is already used.");
            alert.setContentText("This username already exists in the database. Please use another username.");

            alert.showAndWait();

        }
        // Insert the account in the database.
        else {
            if(username.getText().length() < 5){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error #7");
                alert.setHeaderText("Username too short.");
                alert.setContentText("The username must have at least 5 characters.");

                alert.showAndWait();
            }
            else{
                if(password.getText().length() < 5){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error #5");
                    alert.setHeaderText("Password too short.");
                    alert.setContentText("The password must have at least 5 characters.");

                    alert.showAndWait();
                }
                else{
                    // Query(escaped) - insert the account into the database
                    String hashedPassword = BCrypt.hashpw(password.getText(), BCrypt.gensalt());

                    PreparedStatement createAccountQuery = connHandle.prepareStatement("INSERT INTO `users` (`username`, `password`, `account_type`, `name`) VALUES (?,?,?,?)");
                    createAccountQuery.setString(1, username.getText());
                    createAccountQuery.setString(2, hashedPassword);
                    createAccountQuery.setInt(3,(accountType.isSelected()) ? '1' : '0');
                    createAccountQuery.setString(4, firstName.getText() + " " + lastName.getText());
                    /*
                    * If the user is a teacher the account type will be 1.
                    * If the user is not a teacher the account type will be 0.
                    * */

                    createAccountQuery.executeUpdate();

                    // Move to the next stage.
                    stage = (Stage) signUpButton.getScene().getWindow();
                    Pane root;
                    root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    if (SettingsController.effects){
                        LoginController.soundPlayer.play();
                    }
                }
            }


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.play();
    }


}
