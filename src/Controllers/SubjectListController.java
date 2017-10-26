package Controllers;
import Controllers.GameController;
import includes.MYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SubjectListController implements Initializable{

    Stage stage;
    @FXML
    TableView tableView;
    @FXML
    Button backButton,nextButton;
    @FXML
    VBox vBox = new VBox();
    ObservableList<String> subjectListArray = FXCollections.observableArrayList();



//    public void toCompetitionIntro(MouseEvent event) throws IOException{
//        stage = (Stage) nextButton.getScene().getWindow();
//        Pane root;
//        root = FXMLLoader.load(getClass().getResource("/FXML/competitionIntroScene.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/css/competitionIntroScene.css");
//        stage.setScene(scene);
//    }

    public void toChooseTypeOfGame(MouseEvent event) throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/chooseTypeOfGame.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/game.css");
        stage.setScene(scene);
    }

    void getSubjects() throws SQLException {
        Connection connHandle = MYSQL.getConnection();
        PreparedStatement checkUserQuery = connHandle.prepareStatement("SELECT * FROM `questions`");
        ResultSet rs = checkUserQuery.executeQuery();
        while (rs.next()) {
            if (subjectListArray.contains(rs.getString("field"))) {}
            else {
                    subjectListArray.add(rs.getString("field"));
                }
            }
        for (int i=0;i<subjectListArray.size();i++){
            Button button = new Button(subjectListArray.get(i));
            vBox.getChildren().add(button);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    GameController.fieldString =  button.getText();
                    stage = (Stage) backButton.getScene().getWindow();
                    Pane root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/FXML/competitionIntroScene.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/css/competitionIntroScene.css");
                    stage.setScene(scene);
                }
            });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getSubjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
