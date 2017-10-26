package Controllers;

import includes.MYSQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GameController {
    Stage stage;
    @FXML
    Button answerButton1,answerButton2,answerButton3,answerButton4,doorButton,exitButton;
    @FXML
    Label answerLabel1,answerLabel2,answerLabel3,answerLabel4,questionNumberLabel,clockLabel,questionLabel;
    @FXML
    ImageView questionPersonImage;



    public static String fieldString = "";

    public void newQuestion() throws SQLException {
        Connection connHandle = MYSQL.getConnection();
        PreparedStatement checkUserQuery = connHandle.prepareStatement("SELECT * FROM `questions` WHERE `field` = `"+fieldString+"`");
        ResultSet rs = checkUserQuery.executeQuery();
        while (rs.next()){

        }

    }
    public void dead() throws IOException {
        stage = (Stage) answerButton1.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/loseMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/loseMenu.css");
        stage.setScene(scene);
    }
    public void exit() throws  IOException {
        stage = (Stage) exitButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/mainMenu.css");
        stage.setScene(scene);
    }
    public void checkAnswer() throws IOException {
    }
    public void results() throws IOException {
            stage = (Stage) exitButton.getScene().getWindow();
            Pane root;
            root = FXMLLoader.load(getClass().getResource("/FXML/quizFinished.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/css/quizfinished.css");
            stage.setScene(scene);
        }


}

