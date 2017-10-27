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
    public Button toChooseTypeOfGameButton;
    ObservableList<Person> persons = FXCollections.observableArrayList();
    Person person = new Person();







    public void toChooseTypeOfGame(MouseEvent event) throws IOException{
        stage = (Stage) toChooseTypeOfGameButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/leaderboards.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/leaderboards.css");
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getPersons();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        fieldColumn.setCellValueFactory(new PropertyValueFactory<>("field"));
        tableView.setItems(persons);
    }

    public void getPersons() throws SQLException {
        Connection connHandle = MYSQL.getConnection();
        PreparedStatement checkUserQuery = connHandle.prepareStatement("SELECT * FROM `leaderboard`");
        ResultSet rs = checkUserQuery.executeQuery();
        while (rs.next()){
            int rank = rs.getInt("rank");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String score = rs.getString("score");
            String field = rs.getString("field");
            int time = rs.getInt("time");
            person= new Person(rank,firstName,lastName,score,field,time);
            persons.add(person);
            for (int i =0;i<persons.size();i++){
                System.out.println(persons.get(i));
            }
        }
    }
}