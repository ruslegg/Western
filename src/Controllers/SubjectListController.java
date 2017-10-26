package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SubjectListController {
    Stage stage;
    @FXML
    TableView tableView;
    @FXML
    Button backButton,nextButton;



    public void toCompetitionIntro(MouseEvent event) throws IOException{
        stage = (Stage) nextButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/competitionIntroScene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/competitionIntroScene.css");
        stage.setScene(scene);
    }

    public void toChooseTypeOfGame(MouseEvent event) throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/chooseTypeOfGame.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/game.css");
        stage.setScene(scene);
    }
}
