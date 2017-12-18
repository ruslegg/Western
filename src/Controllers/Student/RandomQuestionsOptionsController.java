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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RandomQuestionsOptionsController implements Initializable {
    private Stage stage;

    @FXML
    Button playButton;
    @FXML
    Button backButton;
    @FXML
    public TextField numberTextField;

    public int maxQuestions = 0;

    public static ObservableList<Question> questions = FXCollections.observableArrayList();

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

    public void minusOne(){
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        numberTextField.setText(String.valueOf(Integer.valueOf(numberTextField.getText())-1));
    }
    public void minusTen(){
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        numberTextField.setText(String.valueOf(Integer.valueOf(numberTextField.getText())-10));
    }
    public void plusOne(){
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        numberTextField.setText(String.valueOf(Integer.valueOf(numberTextField.getText())+1));
    }
    public void plusTen(){
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        numberTextField.setText(String.valueOf(Integer.valueOf(numberTextField.getText())+10));
    }
    public void play() throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        boolean isOkay = true;
        GameController.isRandom=true;
        if (Integer.parseInt(numberTextField.getText()) > maxQuestions){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid number");
            alert.setHeaderText("Invalid number of desired questions");
            alert.setContentText("The maximum number of questions is "+maxQuestions+". Please try again.");
            alert.showAndWait();
            numberTextField.setText(String.valueOf(maxQuestions));
            isOkay=false;
        }
        if (isOkay){
            GameController.randomNumber=Integer.parseInt(numberTextField.getText());
            stage = (Stage) playButton.getScene().getWindow();
            Pane root;
            root = FXMLLoader.load(getClass().getResource("/FXML/game.fxml"));
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
            System.out.println("Normal");
        }

    }
    public void getMaximumValue(){
        for (Question question: LoginController.questionsList
                ) {
            ArrayList<SchoolClass> classList = question.getClassList();
            for (SchoolClass schoolClass: classList
                    ) {
                if (schoolClass.getNumber() == LoginController.student.getSchoolClass().getNumber() &&
                        schoolClass.getLetter().equals(LoginController.student.getSchoolClass().getLetter())){
                        questions.add(question);
                        break;
                }
            }

        }
        maxQuestions = questions.size();
    }

    public void toChooseGameType(MouseEvent event) throws IOException {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) backButton.getScene().getWindow();
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/FXML/chooseTypeOfGame.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
}
