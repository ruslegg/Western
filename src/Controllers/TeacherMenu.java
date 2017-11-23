package Controllers;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML
        import javafx.scene.control.Button;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;




        public class TeacherMenu implements Initializable{

            @FXML // ResourceBundle that was given to the FXMLLoader
            private ResourceBundle resources;

            @FXML // URL location of the FXML file that was given to the FXMLLoader
            private URL location;

            @FXML // fx:id="exitButton"
            private Button exitButton; // Value injected by FXMLLoader

            @FXML // fx:id="settingsTeacherButton"
            private Button settingsTeacherButton; // Value injected by FXMLLoader

            @FXML // fx:id="createCompClassButton"
            private Button createCompClassButton; // Value injected by FXMLLoader

            @FXML
            void toCreateMenu(MouseEvent event) {

            }

            @FXML
            void toMainMenu(MouseEvent event) {

            }

            @FXML // This method is called by the FXMLLoader when initialization is complete
            void initialize() {
                assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'TeacherMenu.fxml'.";
                assert settingsTeacherButton != null : "fx:id=\"settingsTeacherButton\" was not injected: check your FXML file 'TeacherMenu.fxml'.";
                assert createCompClassButton != null : "fx:id=\"createCompClassButton\" was not injected: check your FXML file 'TeacherMenu.fxml'.";

            }

            @Override
            public void initialize(URL location, ResourceBundle resources) {

            }
        }

    }
}
