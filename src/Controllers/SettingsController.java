package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller that responds to the music and sound effects in the game. It has 2 choice boxes whether you want music
 * or sound effects while playing.
 */
public class SettingsController implements Initializable {
    public static boolean effects = true;
    @FXML
    public CheckBox soundButton, musicButton;
    public Button backButton;
    private Stage stage;


    /**
     * Selects the choicebox if effects is true and if musicPlayer is playing.
     * @param location - not used
     * @param resources - not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (effects) {
            soundButton.setSelected(true);
        } else {
            soundButton.setSelected(false);
        }
        if (LoginController.musicPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            musicButton.setSelected(true);
        } else {
            musicButton.setSelected(false);
        }
    }

    /**
     * Triggers the sound effects when it is clicked.
     */
    public void soundTrigger()  {

        if (effects) {
            LoginController.soundPlayer.play();
            effects = false;
        } else {
            effects = true;
        }
    }

    /**
     * Triggers the theme music choice box when it is clicked.
     */
    public void musicTrigger() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        if (LoginController.musicPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            LoginController.musicPlayer.stop();
        } else {
            LoginController.musicPlayer.play();
        }
    }

    /**
     * Sends to Main Menu when clicked
     */
    public void toMainMenu(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        if (LoginController.getMenuID() == 0) {
            stage = (Stage) backButton.getScene().getWindow();
            VBox root = new VBox();
            try {
                root = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        } else if (LoginController.getMenuID() == 1) {
            stage = (Stage) backButton.getScene().getWindow();
            VBox root = new VBox();
            try {
                root = FXMLLoader.load(getClass().getResource("/View/teacherMainMenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        } else if (LoginController.getMenuID() == 2) {
            stage = (Stage) backButton.getScene().getWindow();
            VBox root = new VBox();
            try {
                root = FXMLLoader.load(getClass().getResource("/View/adminMainMenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
        }
    }


}