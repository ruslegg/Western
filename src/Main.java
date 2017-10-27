import Controllers.LoginController;
import Controllers.MusicController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            GridPane root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));
            Scene scene = new Scene(root,1280, 1080);
            scene.getStylesheets().add("/css/login.css");
            primaryStage.setScene(scene);
            primaryStage.show();
            MusicController startMusic = new MusicController();
            startMusic.playMusic(true); // change to true to start music
        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
