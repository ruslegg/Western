package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Question;
import Model.SchoolClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller that responds for future Normal Game Controller, where the student can choose how many questions
 * he wants to play from available.
 */
public class RandomQuestionsOptionsController implements Initializable {
    private static ObservableList<Question> questions = FXCollections.observableArrayList();
    @FXML
    public Button playButton;
    public Button backButton;
    public TextField numberTextField;
    private Stage stage;
    private int maxQuestions = 0;

    /**
     * Clears questions ObservableList in order to avoid conflicts
     * Performs getMaximumValue() in order to see maximum questions available to the student
     * Change Listener that helps to limit student's choice only to numbers and a limited number of characters.
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questions.clear();
        getMaximumValue();
        numberTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{1,3}?")) {
                    numberTextField.setText(oldValue);
                }
            }
        });
    }

    /**
     * Decrements TextField's value by one
     */
    public void minusOne() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        numberTextField.setText(String.valueOf(Integer.valueOf(numberTextField.getText()) - 1));
    }

    /**
     * Decrements TextField's value by ten
     */
    public void minusTen() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        numberTextField.setText(String.valueOf(Integer.valueOf(numberTextField.getText()) - 10));
    }

    /**
     * Increments TextField's value by one
     */
    public void plusOne() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        numberTextField.setText(String.valueOf(Integer.valueOf(numberTextField.getText()) + 1));
    }

    /**
     * Increments TextField's value by ten
     */
    public void plusTen() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        numberTextField.setText(String.valueOf(Integer.valueOf(numberTextField.getText()) + 10));
    }

    /**
     * Using several for-each loops, checks for maximum questions available for the logged in student specifically
     */
    public void getMaximumValue() {
        for (Question question : LoginController.getQuestionsList()
                ) {
            ArrayList<SchoolClass> classList = question.getClassList();
            for (SchoolClass schoolClass : classList
                    ) {
                if (schoolClass.getNumber() == LoginController.getStudent().getSchoolClass().getNumber() &&
                        schoolClass.getLetter().equals(LoginController.getStudent().getSchoolClass().getLetter())) {
                    questions.add(question);
                    break;
                }
            }

        }
        maxQuestions = questions.size();
    }

    /**
     * Switches to Game Controller, where student can play a Normal Game with the desired number of questions.
     * Check is performed if the value from TextField is bigger than available questions, and sets the TextField's value
     * to maximum possible value.
     */
    public void play(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        boolean isOkay = true;
        GameController.setRandom(true);
        if (Integer.parseInt(numberTextField.getText()) > maxQuestions) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid number");
            alert.setHeaderText("Invalid number of desired questions");
            alert.setContentText("The maximum number of questions is " + maxQuestions + ". Please try again.");
            alert.showAndWait();
            numberTextField.setText(String.valueOf(maxQuestions));
            isOkay = false;
        }
        if (isOkay) {
            GameController.setRandomNumber(Integer.parseInt(numberTextField.getText()));
            stage = (Stage) playButton.getScene().getWindow();
            VBox root = new VBox();
            try {
                root = FXMLLoader.load(getClass().getResource("/View/game.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/game.css");
            stage.setScene(scene);
        }
    }

    /**
     * Switches back to Type Of Game Scene
     */
    public void toChooseGameType(){
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/chooseTypeOfGame.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    public static ObservableList<Question> getQuestions() {
        return questions;
    }

}
