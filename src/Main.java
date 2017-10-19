import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            GridPane root = FXMLLoader.load(getClass().getResource("FXML/main.fxml"));
            Scene scene = new Scene(root,626, 590);
            scene.getStylesheets().add("/css/main.css");
            root.getStyleClass().add("mainbackground");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
