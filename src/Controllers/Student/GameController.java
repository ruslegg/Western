package Controllers.Student;

import Controllers.LoginController;
import Controllers.SettingsController;
import Model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Controller that stands for Game Functionality
 */
public class GameController implements Initializable {
    private static boolean random = false;
    private static int randomNumber = 0;
    @FXML
    public Button answerButton1, answerButton2, answerButton3, answerButton4, exitButton;
    public Label questionNumberLabel, questionLabel;
    public ImageView questionPersonImage;
    private Stage stage;
    private Question question;
    private int questionNumber = 0;
    private int correctAnswers = 0;
    private AudioClip correctAnswer = new AudioClip(AudioPlayer.class.getResource("/assets/sounds/correct.mp3").toString());
    private AudioClip wrongAnswer = new AudioClip(AudioPlayer.class.getResource("/assets/sounds/wrong.mp3").toString());

    private ObservableList<Question> questions = FXCollections.observableArrayList();


    /**
     * Retrieves data for question list using getQuestions() method
     * Receives a question into the game using newQuestion() method
     * Listens to all buttons whether the button is the correct answer
     * @param location - not used
     * @param resources - not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/assets/img/sheriff.png");
        Image image = new Image(file.toURI().toString());
        questionPersonImage.setImage(image);
        exitButton.getStyleClass().add("exitButton");
        questionNumber = 0;
        getQuestions();
        newQuestion();
        answerButton1.setOnMouseClicked(event -> {
            checkAnswer(answerButton1.getText());
        });
        answerButton2.setOnMouseClicked(event -> {
            checkAnswer(answerButton2.getText());
        });
        answerButton3.setOnMouseClicked(event -> {
            checkAnswer(answerButton3.getText());
        });
        answerButton4.setOnMouseClicked(event -> {
            checkAnswer(answerButton4.getText());
        });

    }

    /**
     * Uses a for-each loop to parse through all the questions on the subject chosen by student
     * If it is a random (normal) game, shuffles the questions got from RandomQuestions Controller
     * Populates questionList
     */
    public void getQuestions() {
        if (random) {
            Collections.shuffle(RandomQuestionsOptionsController.getQuestions());
            for (int i = 0; i < randomNumber; i++) {
                questions.add(RandomQuestionsOptionsController.getQuestions().get(i));
            }
        } else {
            for (Question question : LoginController.getQuestionsList()
                    ) {
                if (question.getQuizType() == ChosenGameMenuController.getQuizType() && question.getQuizID() == ChosenGameMenuController.getQuizId()) {
                    questions.add(question);
                }
            }
        }
    }

    /**
     * Check whether student has pressed the correct answer or not
     * @param answer - answer from button pressed
     */
    public void checkAnswer(String answer) {

        if (answer.equals(question.getCorrectAnswer())) {
            correctAnswer.play();
            questionNumber++;
            correctAnswers++;
            newQuestion();
        } else {
            wrongAnswer.play();
            questionNumber++;
            newQuestion();
        }
    }

    /**
     * Getting a new question and inserts them into fields
     * Finishes the quiz if no questions left
     */
    public void newQuestion() {
        if (questionNumber == questions.size()) {
            QuizFinishedController.setNumberOfQuestions(questions.size());
            QuizFinishedController.setCorrectAnswers(correctAnswers);
            results();
        } else {
            question = questions.get(questionNumber);
            questionNumberLabel.setText("Question number: " + String.valueOf(questionNumber + 1));
            questionLabel.setText(question.getQuestion());
            answerButton1.setText(question.getAnswer1());
            answerButton2.setText(question.getAnswer2());
            answerButton3.setText(question.getAnswer3());
            answerButton4.setText(question.getAnswer4());
        }
    }

    /**
     * Switches to Quiz Finished Controller
     */
    public void results() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) exitButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/quizFinished.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }

    /**
     * Switches to Student's Main Menu
     */
    public void exit() {
        if (SettingsController.effects) {
            LoginController.soundPlayer.play();
        }
        stage = (Stage) exitButton.getScene().getWindow();
        VBox root = new VBox();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        root.getStyleClass().add("scene-background");
        scene.getStylesheets().add("/assets/css/menu.css");
        stage.setScene(scene);
    }


    public static boolean isRandom() {
        return random;
    }

    public static void setRandom(boolean random) {
        GameController.random = random;
    }

    public static void setRandomNumber(int randomNumber) {
        GameController.randomNumber = randomNumber;
    }
}


