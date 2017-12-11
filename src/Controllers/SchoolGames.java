package Controllers;
import Controllers.GameController;
import Model.TableView.UserGameCompetition;
import Model.TableView.UserGameContest;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SchoolGames implements Initializable{

    Stage stage;
    @FXML
    Button backButton,nextButton;
    @FXML
    public TableView<UserGameContest> contestTableView;
    @FXML
    public TableView<UserGameCompetition> competitionTableView;
    @FXML
    public TableColumn<UserGameContest,String> contestSubject,daysLeft;
    @FXML
    public TableColumn<UserGameCompetition,String> competitionSubject;

    ObservableList<UserGameContest> contests = FXCollections.observableArrayList();
    ObservableList<UserGameCompetition> competitions = FXCollections.observableArrayList();






//    public void toCompetitionIntro(MouseEvent event) throws IOException{
//        stage = (Stage) nextButton.getScene().getWindow();
//        Pane root;
//        root = FXMLLoader.load(getClass().getResource("/FXML/competitionIntroScene.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/css/competitionIntroScene.css");
//        stage.setScene(scene);
//    }

    public void toChooseTypeOfGame(MouseEvent event) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/chooseTypeOfGame.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/game.css");
        stage.setScene(scene);
    }

    void getSubjects() throws SQLException, FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(new File("src/data/attributes/questions.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int quizType = Integer.valueOf(line.split("\\s")[0]);
            if (quizType == 0 ){
                competitions.add(new UserGameCompetition(line.split("\\s")[9]));
            }else if(quizType == 1){
                contests.add(new UserGameContest(line.split("\\s")[9],line.split("\\s")[11],line.split("\\s")[12]));
            }
        }
        contestSubject.setCellValueFactory(new PropertyValueFactory<>("contestSubject"));
        daysLeft.setCellValueFactory(new PropertyValueFactory<>("daysLeft"));
        competitionSubject.setCellValueFactory(new PropertyValueFactory<>("competitionSubject"));
        contestTableView.setItems(contests);
        competitionTableView.setItems(competitions);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            getSubjects();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void playGame(MouseEvent event){

    }

    public void toMainMenu(MouseEvent mouseEvent) {
    }
}
