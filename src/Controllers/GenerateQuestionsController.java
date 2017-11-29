package Controllers;

import Model.Question;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GenerateQuestionsController implements Initializable {
    private static ArrayList<String> classList = new ArrayList<>();
    private static String subject;
    private static int quizType;
    private static DatePicker fromDatePicker,toDatePicker;
    int questionNumber=1;
    String correctAnswer;
    int competitionId=0;
    int contestId=0;
    private Stage stage;
    @FXML
    private Label questionNumberLabel;
    @FXML
    private TextField questionTextField,answer1TextField,answer2TextField,answer3TextField,answer4TextField;
    @FXML
    private CheckBox correct1,correct2,correct3,correct4;
    @FXML
    private Button nextQuestionButton,finishButton;







    public void nextQuestion(MouseEvent mouseEvent) throws IOException {
        questionNumber++;
        questionNumberLabel.setText("Question #" + questionNumber);

        if (quizType == 0) {
            Question question = new Question(String.valueOf(quizType),String.valueOf(competitionId+1),questionTextField.getText(),answer1TextField.getText(),answer2TextField.getText(),answer3TextField.getText(),answer4TextField.getText(),correctAnswer ,getSubject(),getClassList().toString());
            question.serialize();
        }
        else{
            String fromDateString=fromDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String toDateString = toDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Question question = new Question(String.valueOf(quizType),String.valueOf(contestId+1),questionTextField.getText(),answer1TextField.getText(),answer2TextField.getText(),answer3TextField.getText(),answer4TextField.getText(),correctAnswer,getSubject(),getClassList().toString(),fromDateString,toDateString);
            question.serialize();
        }
        correct1.setSelected(false);
        correct2.setSelected(false);
        correct3.setSelected(false);
        correct4.setSelected(false);
        questionTextField.clear();
        answer1TextField.clear();
        answer2TextField.clear();
        answer3TextField.clear();
        answer4TextField.clear();

    }

    public void toMainMenu(MouseEvent mouseEvent) throws IOException {
        if (questionTextField.getText().isEmpty() || answer1TextField.getText().isEmpty() || answer2TextField.getText().isEmpty() || answer3TextField.getText().isEmpty() || answer4TextField.getText().isEmpty()) {
        }
        else {
            if (quizType == 0) {
                Question question = new Question(String.valueOf(quizType),String.valueOf(competitionId+1),questionTextField.getText(),answer1TextField.getText(),answer2TextField.getText(),answer3TextField.getText(),answer4TextField.getText(),correctAnswer ,getSubject(),getClassList().toString());
                question.serialize();
            }
            else{
                String fromDateString=fromDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String toDateString = toDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Question question = new Question(String.valueOf(quizType),String.valueOf(contestId+1),questionTextField.getText(),answer1TextField.getText(),answer2TextField.getText(),answer3TextField.getText(),answer4TextField.getText(),correctAnswer,getSubject(),getClassList().toString(),fromDateString,toDateString);
                question.serialize();
            }
        }
        classList.clear();
        subject="";
        quizType=0;
        fromDatePicker= new DatePicker();
        toDatePicker = new DatePicker();
        questionNumberLabel.setText("");
        questionTextField.clear();
        answer1TextField.clear();
        answer2TextField.clear();
        answer3TextField.clear();
        answer4TextField.clear();
        correct1.setSelected(false);
        correct2.setSelected(false);
        correct3.setSelected(false);
        correct4.setSelected(false);
        stage = (Stage) nextQuestionButton.getScene().getWindow();
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/css/menu.css");
        stage.setScene(scene);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Scanner idScanner=null;
        try {
            idScanner = new Scanner(new File("src/data/attributes/questions.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> competitionList = new ArrayList<>();
        ArrayList<Integer> contestList = new ArrayList<>();


        while (idScanner.hasNextLine()){
            String fileQuizType = idScanner.next();
            if (Integer.valueOf(fileQuizType)==0){
                int competitionTempId = idScanner.nextInt();
                if (competitionList.isEmpty()) {
                    competitionList.add(competitionTempId);
                }
                else{
                    for (int i=0;i<competitionList.size();i++){
                        if (competitionList.get(i).equals(competitionTempId)){

                        }
                        else{
                            competitionList.add(competitionTempId);
                        }
                    }
                }
                competitionId=competitionList.get(competitionList.size()-1);
            }
            else if(Integer.valueOf(fileQuizType)==1){
                int contestTempId = idScanner.nextInt();
                if (contestList.isEmpty()) {
                    contestList.add(contestTempId);
                }
                else{
                    for (int i=0;i<contestList.size();i++){
                        if (contestList.get(i).equals(contestTempId)){

                        }
                        else{
                            contestList.add(contestTempId);
                        }
                    }
                }
                contestId=contestList.get(contestList.size()-1);
            }
            idScanner.nextLine();
        }

        correct1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                correct2.setSelected(false);
                correct3.setSelected(false);
                correct4.setSelected(false);
                correctAnswer = answer1TextField.getText();
            }
        });
        correct2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                correct1.setSelected(false);
                correct3.setSelected(false);
                correct4.setSelected(false);
                correctAnswer = answer2TextField.getText();
            }
        });
        correct3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                correct1.setSelected(false);
                correct2.setSelected(false);
                correct4.setSelected(false);
                correctAnswer = answer3TextField.getText();
            }
        });
        correct4.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                correct1.setSelected(false);
                correct2.setSelected(false);
                correct3.setSelected(false);
                correctAnswer = answer4TextField.getText();
            }
        });



    }










    public static ArrayList<String> getClassList() {
        return classList;
    }

    public static void setClassList(ArrayList<String> classList) {
        GenerateQuestionsController.classList = classList;
    }

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        GenerateQuestionsController.subject = subject;
    }

    public static int getQuizType() {
        return quizType;
    }

    public static void setQuizType(int quizType) {
        GenerateQuestionsController.quizType = quizType;
    }

    public static DatePicker getFromDatePicker() {
        return fromDatePicker;
    }

    public static void setFromDatePicker(DatePicker fromDatePicker) {
        GenerateQuestionsController.fromDatePicker = fromDatePicker;
    }

    public static DatePicker getToDatePicker() {
        return toDatePicker;
    }

    public static void setToDatePicker(DatePicker toDatePicker) {
        GenerateQuestionsController.toDatePicker = toDatePicker;
    }



}
