package Controllers.Teacher;

import Controllers.LoginController;
import Model.SchoolClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateClassController implements Initializable {
    @FXML
    public TextField classNumber;
    @FXML
    public TextField classLetter;
    @FXML
    public Button submitButton;
    @FXML
    public Button backButton;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        classNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d{0,1}?")) {
                        classNumber.setText(oldValue);
                    }

            }
        });


    }


    public void toCreateMenu() throws IOException{
        stage = (Stage) backButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/createOptionsTeacher.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
    public void createClass(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        int index = 1;
        boolean found = false;

        if (classNumber.getText().length() == 1 && classLetter.getText().length() == 1) {
            for (SchoolClass schoolClass : LoginController.classList) {
                if (schoolClass.getNumber() == Integer.parseInt(classNumber.getText())
                        && schoolClass.getLetter().equals(classLetter.getText().toUpperCase())) {
                    found = true;
                } else {
                    if (index == LoginController.classList.size() && !found) {
                        SchoolClass newClass = new SchoolClass(Integer.valueOf(classNumber.getText()), classLetter.getText().toUpperCase());
                        newClass.serialize();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Create successful");
                        alert.setHeaderText("Class has been created successfully");
                        alert.setContentText("Your class now is in database.");
                        alert.showAndWait();
                        toCreateMenu();
                    } else {
                        index++;
                    }
                }
            }

        }


        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid number");
            alert.setHeaderText("Invalid number of characters");
            alert.setContentText("The class number and letter must have 1 symbol. Please try again.");
            alert.showAndWait();
            classNumber.clear();
            classLetter.clear();
        }
        if (found) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Existing value");
                alert.setHeaderText("Class exists");
                alert.setContentText("This class already exists in the database. Please try again.");
                alert.showAndWait();
                classNumber.clear();
                classLetter.clear();
        }
        if (index==1 && !found  && classNumber.getText().length() == 1 && classLetter.getText().length() == 1){
            SchoolClass newClass = new SchoolClass(Integer.valueOf(classNumber.getText()),classLetter.getText().toUpperCase());
            newClass.serialize();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create successful");
            alert.setHeaderText("Class has been created successfully");
            alert.setContentText("Your class now is in database.");
            alert.showAndWait();
            toCreateMenu();
        }
    }


}
