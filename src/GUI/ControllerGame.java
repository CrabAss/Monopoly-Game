package GUI;

import com.sun.org.apache.regexp.internal.RE;
import javafx.fxml.FXML;
import javafx.scene.shape.*;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

public class ControllerGame {
    @FXML
    private GridPane Central;
    @FXML
    private Rectangle RectangleRed;

    @FXML
    public void HandleAction(ActionEvent event){
        Rectangle t;
        t = RectangleRed;
        Central.add(t, 0, 0);
    }
}
