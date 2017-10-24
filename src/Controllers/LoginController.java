package Controllers;/**
 * Created by Constantine on 10/23/2017.
 */

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import includes.MYSQL;

public class LoginController extends Application {

    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    private CheckBox accountType;


    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    }

    public void showRegisterDialog(MouseEvent event) throws IOException, SQLException {
        // Get the current stage.
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();

        // Change the current stage.
        GridPane root = FXMLLoader.load(getClass().getResource("/FXML/register.fxml"));

        Scene scene = new Scene(root);


        stage.setScene(scene);
    }

    public void registerAccount(MouseEvent event) throws IOException, SQLException {
        Connection connHandle = MYSQL.getConnection();

        // Query(escaped) - check if the username already exists in the database.
        PreparedStatement checkUserQuery = connHandle.prepareStatement("SELECT * FROM `users` WHERE `username` = ? LIMIT 1");
        checkUserQuery.setString(1, username.getText());

        // Store the result.
        ResultSet rs = checkUserQuery.executeQuery();

        // If the query returns a row.
        if(rs.next()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error #1");
            alert.setHeaderText("Username is already used.");
            alert.setContentText("This username already exists in the database. Please use another username.");

            alert.showAndWait();

        }
        else{
            // Query(escaped) - insert the account into the database
            PreparedStatement createAccountQuery = connHandle.prepareStatement("INSERT INTO `users` (`username`, `password`) VALUES (?,?)");
            createAccountQuery.setString(1, username.getText());
            createAccountQuery.setString(2, password.getText());

            //String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

            createAccountQuery.executeUpdate();
        }
    }
}
