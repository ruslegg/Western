import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class Controller {
    @FXML
    private Label projectLabel;
    @FXML
    public void changeText(MouseEvent event) {
        if (projectLabel.getText().equals("P1 Project")){
            projectLabel.setText("Western project");
        }
        else{
            projectLabel.setText("P1 Project");
        }
    }
}
