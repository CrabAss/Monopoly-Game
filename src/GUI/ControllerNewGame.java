package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.beans.value.*;

public class ControllerNewGame {
    @FXML
    private Slider NumberOfPlayer;
    @FXML
    private Slider NumberOfAI;
    @FXML
    private Button StartGame;

    @FXML
    private void HandleNumberOfPlayer(){
        NumberOfAI.setMax(NumberOfPlayer.getValue());
        System.out.println(NumberOfPlayer.getValue());
    }

    @FXML
    private void HandleStartGame(ActionEvent event){

    }
}
