package Controllers.Teacher;

import Controllers.LoginController;
import Model.Question;
import Model.SchoolClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller that responds for generating new questions into database to the students
 */
public class GenerateQuestionsController implements Initializable {
    private static String subject;
    private static int quizType;
    private static DatePicker fromDatePicker, toDatePicker;
    private static ArrayList<SchoolClass> classList = new ArrayList();
    @FXML
    public Label questionNumberLabel;
    public TextField questionTextField, answer1TextField, answer2TextField, answer3TextField, answer4TextField;
    public CheckBox correct1, correct2, correct3, correct4;
    public Button nextQuestionButton, finishButton;
    private Stage stage;
    private int questionNumber = 1;
    private String correctAnswer;
    private int competitionId = -1;
    private int contestId = -1;
    private boolean finish = false;


    /**
     * Checks through all the questions what was the last contest or competition ID and assign it to the local variable
     * to use it when a new question will be generated.
     * Choice boxes has listeners when the property has changed. This is due correct answer must be only one, so when
     * one is selected, other ones will be de-selected.
     * @param location - not used
     * @param resources - not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionNumberLabel.setText("Question #" + questionNumber);
        for (Question question : LoginController.getQuestionsList()
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


    /**
     * Checks whether teacher wanted to finish to generate questions to the quiz and switch to main menu.
     * If a new questions is desired, several checks are made before sent them to question ArrayList, such as:
     * "Question field is empty" "One of the answer field is empty" "None of the correct answer checkbox was selected".
     * Checks which kind of quiz question attributes to (competition or contest) therefore uses different constructors.
     * Send to database.
     * Preparing for next question by clearing all the fields.
     * @param mouseEvent next button clicked
     */
    public void nextQuestion(MouseEvent mouseEvent) {
        if (finish && questionTextField.getText().isEmpty() && answer1TextField.getText().isEmpty() && answer2TextField.getText().isEmpty() && answer3TextField.getText().isEmpty() && answer4TextField.getText().isEmpty() && (!correct1.isSelected() || !correct2.isSelected() || !correct3.isSelected() || !correct4.isSelected())) {
            classList.clear();
            subject = "";
            quizType = 0;
            fromDatePicker = new DatePicker();
            toDatePicker = new DatePicker();
            questionNumberLabel.setText("");
            stage = (Stage) nextQuestionButton.getScene().getWindow();
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
        } else {
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
                    Question question = new Question(competitionId + 1, LoginController.getTeacher().getId(), 0, classList, questionTextField.getText(), answer1TextField.getText(), answer2TextField.getText(), answer3TextField.getText(), answer4TextField.getText(), correctAnswer, getSubject());
                } else {
                    String fromDateString = fromDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    String toDateString = toDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    Question question = new Question(contestId + 1, LoginController.getTeacher().getId(), 1, classList, questionTextField.getText(), answer1TextField.getText(), answer2TextField.getText(), answer3TextField.getText(), answer4TextField.getText(), correctAnswer, getSubject(), fromDateString, toDateString);
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


            }
        }


    }

    /**
     * Attribute logical variable finish to true, and nextQuestion method is executed with switching to main menu afterwards.
     *
     * @param mouseEvent finish button click
     */
    public void toMainMenu(MouseEvent mouseEvent) {
        finish = true;
        nextQuestion(mouseEvent);
    }

    /**
     * Returns the class list used in this controller. Used in GenerateQuizController in order to add school classes
     * attributed to this questions
     * @return School Class ArrayList
     */
    public static ArrayList<SchoolClass> getClassList() {
        return classList;
    }

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        GenerateQuestionsController.subject = subject;
    }

    /**
     * Used for differentiating whether it is competition or quiz for usage of different constructors in question model.
     * @param quizType 0 - Competition Quiz ; 1 - Contest Quiz
     */
    public static void setQuizType(int quizType) {
        GenerateQuestionsController.quizType = quizType;
    }

    /**
     *
     * @param fromDatePicker If it is contest, this parameter will be used in constructor and new question will be generated with time-stamp
     */
    public static void setFromDatePicker(DatePicker fromDatePicker) {
        GenerateQuestionsController.fromDatePicker = fromDatePicker;
    }
    /**
     *
     * @param toDatePicker If it is contest, this parameter will be used in constructor and new question will be generated with time-stamp
     */
    public static void setToDatePicker(DatePicker toDatePicker) {
        GenerateQuestionsController.toDatePicker = toDatePicker;
    }
}
