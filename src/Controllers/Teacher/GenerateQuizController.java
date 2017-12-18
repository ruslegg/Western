package Controllers.Teacher;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.SchoolClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class GenerateQuizController implements Initializable{
    @FXML
    private ComboBox classComboBox;
    @FXML
    private CheckBox aCheck,bCheck,cCheck,dCheck,eCheck,competitionCheck,contestCheck;
    @FXML
    private TextField subjectTextField;
    @FXML
    private DatePicker fromDatePicker,toDatePicker;
    @FXML
    private Button backButton,nextButton;
    ObservableList<Integer> classList = FXCollections.observableArrayList();
    int quizType;
    public Stage stage;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDatePicker.setDisable(true);
        toDatePicker.setDisable(true);
        aCheck.setDisable(true);
        bCheck.setDisable(true);
        cCheck.setDisable(true);
        dCheck.setDisable(true);
        eCheck.setDisable(true);
        for(int i = 0; i< LoginController.classList.size(); i++){
                if (classList.isEmpty()){
                    classList.add(LoginController.classList.get(i).getNumber());
                }
                else{
                    int checkNumber = 0;
                    for (int j = 0; j < classList.size() ; j++) {
                        if (classList.get(j) == LoginController.classList.get(i).getNumber()) {
                            checkNumber++;
                        }

                        if (j == classList.size() - 1 && checkNumber == 0) {
                            classList.add(LoginController.classList.get(i).getNumber());
                        }
                    }
                }
            }

        classComboBox.setItems(classList);
        classComboBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                aCheck.setDisable(true);
                bCheck.setDisable(true);
                cCheck.setDisable(true);
                dCheck.setDisable(true);
                eCheck.setDisable(true);
                if (!classComboBox.getSelectionModel().isEmpty()) {
                    for (int i=0;i<LoginController.classList.size();i++){
                        if ((Integer) classComboBox.getSelectionModel().getSelectedItem() == LoginController.classList.get(i).getNumber()){
                            String choice = LoginController.classList.get(i).getLetter();
                            switch(choice){
                                case "A" :aCheck.setDisable(false);break;
                                case "B" :bCheck.setDisable(false);break;
                                case "C" :cCheck.setDisable(false);break;
                                case "D" :dCheck.setDisable(false);break;
                                case "E" :eCheck.setDisable(false);break;
                            }
                        }
                    }
                }
            }
        });
        competitionCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(competitionCheck.isSelected()){
                    quizType=0;
                    contestCheck.setSelected(false);
                    fromDatePicker.setDisable(true);
                    toDatePicker.setDisable(true);
                }
            }
        });
        contestCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(contestCheck.isSelected()){
                    quizType=1;
                    competitionCheck.setSelected(false);
                    fromDatePicker.setDisable(false);
                    toDatePicker.setDisable(false);
                }
                else{
                    fromDatePicker.setDisable(true);
                    toDatePicker.setDisable(true);
                }
            }
        });
        fromDatePicker.setValue(LocalDate.now());
        toDatePicker.setValue(LocalDate.now());

    }

    public void toQuestions(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        boolean quizCorrect = true;
        if (classComboBox.getSelectionModel().isEmpty() || (!aCheck.isSelected() && !bCheck.isSelected() && !cCheck.isSelected() && !dCheck.isSelected())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Field not selected");
            alert.setHeaderText("The class choice is not selected");
            alert.setContentText("You haven't selected any class. Please select a class and try again");
            alert.showAndWait();
            quizCorrect = false;
        }
        if (!competitionCheck.isSelected() && !contestCheck.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Type not selected");
            alert.setHeaderText("The quiz type is not selected");
            alert.setContentText("You haven't selected the type of quiz. Please select a class and try again");
            alert.showAndWait();
            quizCorrect = false;
        }
        if (subjectTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Field empty");
            alert.setHeaderText("The subject field is empty");
            alert.setContentText("You haven't insert the name of quiz. Please insert the name try again");
            alert.showAndWait();
            quizCorrect = false;
        }
        if (contestCheck.isSelected() && (fromDatePicker.getValue() == null || toDatePicker.getValue() == null)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Time not selected");
            alert.setHeaderText("The contest time is not selected");
            alert.setContentText("You haven't selected the time of the contest. Please select the time and try again");
            alert.showAndWait();
            quizCorrect = false;
        }
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        if (quizCorrect) {
            stage = (Stage) nextButton.getScene().getWindow();
            VBox root;
            root = FXMLLoader.load(getClass().getResource("/FXML/generateQuestions.fxml"));
            Scene scene = new Scene(root);
            root.getStyleClass().add("scene-background");
            scene.getStylesheets().add("/assets/css/menu.css");
            stage.setScene(scene);
            if (aCheck.isSelected())
                GenerateQuestionsController.getClassList().add(new SchoolClass((Integer) classComboBox.getSelectionModel().getSelectedItem(), "A"));
            if (bCheck.isSelected())
                GenerateQuestionsController.getClassList().add(new SchoolClass((Integer) classComboBox.getSelectionModel().getSelectedItem(), "B"));
            if (cCheck.isSelected())
                GenerateQuestionsController.getClassList().add(new SchoolClass((Integer) classComboBox.getSelectionModel().getSelectedItem(), "C"));
            if (dCheck.isSelected())
                GenerateQuestionsController.getClassList().add(new SchoolClass((Integer) classComboBox.getSelectionModel().getSelectedItem(), "D"));
            if (eCheck.isSelected())
                GenerateQuestionsController.getClassList().add(new SchoolClass((Integer) classComboBox.getSelectionModel().getSelectedItem(), "E"));
            GenerateQuestionsController.setSubject(subjectTextField.getText());
            GenerateQuestionsController.setQuizType(quizType);
            GenerateQuestionsController.setFromDatePicker(fromDatePicker);
            GenerateQuestionsController.setToDatePicker(toDatePicker);
        }
    }

    public void toCreateOptions(MouseEvent mouseEvent) throws IOException {
        stage = (Stage) nextButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/createOptionsTeacher.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }
}
