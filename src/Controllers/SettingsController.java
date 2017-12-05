package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class SettingsController implements Initializable {
    private Stage stage;
    @FXML
    public CheckBox soundButton;
    @FXML
    public CheckBox musicButton;
    @FXML
    public Button backButton;

    public static boolean effects = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            if (effects){
                soundButton.setSelected(true);
            }
            else{
                soundButton.setSelected(false);
            }
            if (LoginController.musicPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
                musicButton.setSelected(true);
            }
            else {
                musicButton.setSelected(false);
            }
    }


    public void soundTrigger(MouseEvent event) throws IOException {

        if (effects){
            LoginController.soundPlayer.play();
            effects=false;
        }
        else{
            effects=true;
        }
    }

    public void musicTrigger(MouseEvent event) throws IOException, UnsupportedAudioFileException, SQLException, LineUnavailableException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        if (LoginController.musicPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
            LoginController.musicPlayer.stop();
        }
        else{
            LoginController.musicPlayer.play();
        }
    }

    public void toMainMenu(MouseEvent event) throws IOException{
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        if (LoginController.menuID==0) {
            stage = (Stage) backButton.getScene().getWindow();
            Pane root;
            root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
            Scene scene = new Scene(root, 1280, 720);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/css/menu.css");
            stage.setScene(scene);
        }
        else if(LoginController.menuID==1){
            stage = (Stage) backButton.getScene().getWindow();
            Pane root;
            root = FXMLLoader.load(getClass().getResource("/FXML/teacherMainMenu.fxml"));
            Scene scene = new Scene(root, 1280, 720);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/css/menu.css");
            stage.setScene(scene);
        } else if (LoginController.menuID==2){
            stage = (Stage) backButton.getScene().getWindow();
            Pane root;
            root = FXMLLoader.load(getClass().getResource("/FXML/adminMainMenu.fxml"));
            Scene scene = new Scene(root, 1280, 720);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/css/menu.css");
            stage.setScene(scene);
        }
    }


}