package Controllers;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;


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
    ObservableList<String> classList = FXCollections.observableArrayList();
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
        Scanner classScanner = null;
        try {
            classScanner = new Scanner(new File("src/data/attributes/classes.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (classScanner.hasNextLine()) {
            String temp = classScanner.next();
            if (classList.isEmpty()) {
                classList.add(temp);
            } else {
                for (int i = 0; i < classList.size(); i++) {
                    if (classList.get(i).equals(temp)) {

                    } else {
                        classList.add(temp);
                    }
                }
            }
            classScanner.nextLine();
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
                    try {
                        Scanner letterScanner = new Scanner(new File("src/data/attributes/classes.txt"));
                        while (letterScanner.hasNextLine()){
                            String temp = letterScanner.next();
                            if (temp.equals(classComboBox.getSelectionModel().getSelectedItem())){
                                String choice = letterScanner.next();
                                switch (choice){
                                    case "A" : aCheck.setDisable(false);break;
                                    case "B" : bCheck.setDisable(false);break;
                                    case "C" : cCheck.setDisable(false);break;
                                    case "D" : dCheck.setDisable(false);break;
                                    case "E" : eCheck.setDisable(false);break;
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
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
            }
        });
        fromDatePicker.setValue(LocalDate.now());
        toDatePicker.setValue(LocalDate.now());

    }

    public void toQuestions(MouseEvent mouseEvent) throws IOException {
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        if (SettingsController.effects){
            LoginController.soundPlayer.play();
        }
        stage = (Stage) nextButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/generateQuestions.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);
        if (aCheck.isSelected()) GenerateQuestionsController.getClassList().add(classComboBox.getSelectionModel().getSelectedItem().toString()+"A");
        if (bCheck.isSelected()) GenerateQuestionsController.getClassList().add(classComboBox.getSelectionModel().getSelectedItem().toString()+"B");
        if (cCheck.isSelected()) GenerateQuestionsController.getClassList().add(classComboBox.getSelectionModel().getSelectedItem().toString()+"C");
        if (dCheck.isSelected()) GenerateQuestionsController.getClassList().add(classComboBox.getSelectionModel().getSelectedItem().toString()+"D");
        if (eCheck.isSelected()) GenerateQuestionsController.getClassList().add(classComboBox.getSelectionModel().getSelectedItem().toString()+"E");
        GenerateQuestionsController.setSubject(subjectTextField.getText());
        GenerateQuestionsController.setQuizType(quizType);
        GenerateQuestionsController.setFromDatePicker(fromDatePicker);
        GenerateQuestionsController.setToDatePicker(toDatePicker);

    }
}
