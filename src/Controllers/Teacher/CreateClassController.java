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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller that responds for the create School class scene and functionality
 */
public class CreateClassController implements Initializable {
    @FXML
    public TextField classNumber, classLetter;
    public Button submitButton, backButton;
    private Stage stage;

    /**
     * Using regex and change listener making the classNumber TextField to be only numbers and only 1 character
     * @param location - not used
     * @param resources - not used
     */
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

    /**
     * Changes scene to Teacher Main Menu
     */
    public void toCreateMenu(){
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/createOptionsTeacher.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /** When create button is clicked, the Invalid number of characters and Existing value check is made.
     * If checks are passed, system will create a new school class.
     *
     * @throws IOException exception caused by serialization
     * @throws ClassNotFoundException exception caused by serialization
     */
    public void createClass() throws IOException, ClassNotFoundException {
        int index = 1;
        boolean found = false;
        if (classNumber.getText().length() == 1 && classLetter.getText().length() == 1) {
            for (SchoolClass schoolClass : LoginController.getClassList()) {
                if (schoolClass.getNumber() == Integer.parseInt(classNumber.getText())
                        && schoolClass.getLetter().equals(classLetter.getText().toUpperCase())) {
                    found = true;
                } else {
                    if (index == LoginController.getClassList().size() && !found) {
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

        } else {
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
        if (index == 1 && !found && classNumber.getText().length() == 1 && classLetter.getText().length() == 1) {
            SchoolClass newClass = new SchoolClass(Integer.valueOf(classNumber.getText()), classLetter.getText().toUpperCase());
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
