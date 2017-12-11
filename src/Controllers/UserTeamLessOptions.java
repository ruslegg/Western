package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class UserTeamLessOptions {
    private Stage stage;
    @FXML
    public Button createButton,joinButton,backButton;

    public void toCreateTeam() throws IOException {
        stage = (Stage) createButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/userTeamLessCreate.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }
    public void toJoinTeam() throws IOException {
        stage = (Stage) joinButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/userTeamLessJoin.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }
    public void toMainMenu() throws IOException{
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
    }


}
