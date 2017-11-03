package Controllers;

import Model.Person;
import Model.Question;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import includes.MYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Controllers.GameController.fieldString;


public class LeaderboardsController implements Initializable{
    private Stage stage;
    @FXML
    public Label leaderBoardLabel;
    @FXML
    public TableView<Person> tableView;
    @FXML
    public TableColumn<Person,Integer> rankColumn;
    @FXML
    public TableColumn<Person,String> nameColumn;
    @FXML
    public TableColumn<Person,String> scoreColumn;
    @FXML
    public TableColumn<Person,Integer> timeColumn;
    @FXML
    public TableColumn<Person,String> fieldColumn;
    @FXML
    public Button backButton;

    ObservableList<Person> persons = FXCollections.observableArrayList();
    Person person = new Person();







    public void toCompetitionIntro(MouseEvent event) throws IOException{
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/competitionIntroScene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/competitionIntroScene.css");
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getPersons();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rankColumn.setCellValueFactory(new PropertyValueFactory<Person,Integer>("rank"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Person,Integer>("time"));
        fieldColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("field"));
        tableView.setItems(persons);
    }

    public void getPersons() throws SQLException {
        Connection connHandle = MYSQL.getConnection();


        PreparedStatement fetchUsers = connHandle.prepareStatement("SELECT * FROM `leaderboard` WHERE `field` = ? ORDER BY `score` DESC,`time` ASC LIMIT 20");
        fetchUsers.setString(1, fieldString);

        ResultSet rs = fetchUsers.executeQuery();
        int j = 1;
        while (rs.next()){
            int rank = j;

            PreparedStatement fetchUserName = connHandle.prepareStatement("SELECT `name` FROM `users` WHERE username = ? LIMIT 1");
            fetchUserName.setString(1, rs.getString("username"));

            ResultSet rsUsername = fetchUserName.executeQuery();
            rsUsername.next();

            String name = rsUsername.getString("name");
            String score = rs.getString("score");
            String field = rs.getString("field");
            int time = rs.getInt("time");

            persons.add(new Person(rank, name, score, field, time));

            j++;
        }
    }
}