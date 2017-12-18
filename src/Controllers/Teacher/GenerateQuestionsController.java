package Controllers.Teacher;

import Controllers.LoginController;
import Model.Question;
import Model.SchoolClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GenerateQuestionsController implements Initializable {
    private static ArrayList<SchoolClass> classList = new ArrayList();
    private static String subject;
    private static int quizType;
    private static DatePicker fromDatePicker,toDatePicker;
    private static String teacherID;
    int questionNumber=1;
    String correctAnswer;
    int competitionId=-1;
    int contestId=-1;
    private Stage stage;
    boolean finish = false;
    @FXML
    private Label questionNumberLabel;
    @FXML
    private TextField questionTextField,answer1TextField,answer2TextField,answer3TextField,answer4TextField;
    @FXML
    private CheckBox correct1,correct2,correct3,correct4;
    @FXML
    private Button nextQuestionButton,finishButton;

    public void nextQuestion(MouseEvent mouseEvent) throws IOException {
        if (finish && questionTextField.getText().isEmpty() && answer1TextField.getText().isEmpty() && answer2TextField.getText().isEmpty() && answer3TextField.getText().isEmpty() && answer4TextField.getText().isEmpty() && (!correct1.isSelected()||!correct2.isSelected()||!correct3.isSelected()||!correct4.isSelected())) {
                classList.clear();
                subject="";
                quizType=0;
                fromDatePicker= new DatePicker();
                toDatePicker = new DatePicker();
                questionNumberLabel.setText("");
                stage = (Stage) nextQuestionButton.getScene().getWindow();
                VBox root;
                root = FXMLLoader.load(getClass().getResource("/FXML/createOptionsTeacher.fxml"));
                Scene scene = new Scene(root);
                root.getStyleClass().add("scene-background");
                scene.getStylesheets().add("/assets/css/menu.css");
                stage.setScene(scene);
            }
        else {
            boolean questionCorrect = true;
            if (questionTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Field empty");
                alert.setHeaderText("The question field is empty");
                alert.setContentText("Your question field appears to be blank. Please insert a question and try again.");
                alert.showAndWait();
                questionCorrect = false;
            }
            if (answer1TextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Field empty");
                alert.setHeaderText("The answer field is empty");
                alert.setContentText("Your answer 1 field appears to be blank. Please insert an answer and try again.");
                alert.showAndWait();
                questionCorrect = false;
            }
            if (answer2TextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Field empty");
                alert.setHeaderText("The answer field is empty");
                alert.setContentText("Your answer 2 field appears to be blank. Please insert an answer and try again.");
                alert.showAndWait();
                questionCorrect = false;
            }
            if (answer3TextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Field empty");
                alert.setHeaderText("The answer field is empty");
                alert.setContentText("Your answer 3 field appears to be blank. Please insert an answer and try again.");
                alert.showAndWait();
                questionCorrect = false;
            }
            if (answer4TextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Field empty");
                alert.setHeaderText("The answer field is empty");
                alert.setContentText("Your answer 4 field appears to be blank. Please insert an answer and try again.");
                alert.showAndWait();
                questionCorrect = false;
            }
            if (!correct1.isSelected() && !correct2.isSelected() && !correct3.isSelected() && !correct4.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CheckBox empty");
                alert.setHeaderText("The correct answer box is not selected");
                alert.setContentText("Your correct answer check-box appears to be not selected. Please select a correct answer and try again.");
                alert.showAndWait();
                questionCorrect = false;
            } else {
                questionCorrect = true;
            }
            if (questionCorrect) {
                questionNumber++;
                questionNumberLabel.setText("Question #" + questionNumber);
                if (quizType == 0) {
                    Question question = new Question(competitionId + 1, LoginController.teacher.getId(), 0, classList, questionTextField.getText(), answer1TextField.getText(), answer2TextField.getText(), answer3TextField.getText(), answer4TextField.getText(), correctAnswer, getSubject());
                } else {
                    String fromDateString = fromDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    String toDateString = toDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    Question question = new Question(contestId + 1, LoginController.teacher.getId(), 1, classList, questionTextField.getText(), answer1TextField.getText(), answer2TextField.getText(), answer3TextField.getText(), answer4TextField.getText(), correctAnswer, getSubject(), fromDateString, toDateString);
                }
                questionTextField.clear();
                answer1TextField.clear();
                answer2TextField.clear();
                answer3TextField.clear();
                answer4TextField.clear();
                correct1.setSelected(false);
                correct2.setSelected(false);
                correct3.setSelected(false);
                correct4.setSelected(false);
                if (finish) {
                    classList.clear();
                    subject = "";
                    quizType = 0;
                    fromDatePicker = new DatePicker();
                    toDatePicker = new DatePicker();
                    questionNumberLabel.setText("");
                    stage = (Stage) nextQuestionButton.getScene().getWindow();
                    VBox root;
                    root = FXMLLoader.load(getClass().getResource("/FXML/createOptionsTeacher.fxml"));
                    Scene scene = new Scene(root);
                    root.getStyleClass().add("scene-background");
                    scene.getStylesheets().add("/assets/css/menu.css");
                    stage.setScene(scene);
                }


            }
        }


    }

    public void toMainMenu(MouseEvent mouseEvent) throws IOException {
        finish=true;
        nextQuestion(mouseEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionNumberLabel.setText("Question #" + questionNumber);
        for (Question question : LoginController.questionsList
                ) {
            if (question.getQuizType() == 0) {
                if (competitionId == -1 || (question.getQuizID() != competitionId)) {
                    competitionId = question.getQuizID();
                }
            } else if (question.getQuizType() == 1) {
                if (contestId == -1 || (question.getQuizID() != contestId)) {
                    contestId = question.getQuizID();
                }
            }
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


    public static ArrayList<SchoolClass> getClassList() {
        return classList;
    }

    public static void setClassList(ArrayList<SchoolClass> classList) {
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

    public static String getTeacherID() {
        return teacherID;
    }

    public static void setTeacherID(String teacherID) {
        GenerateQuestionsController.teacherID = teacherID;
    }
}
