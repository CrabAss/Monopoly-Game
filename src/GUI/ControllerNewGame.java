package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Slider;

/**
 * Controller for the JavaFX Form: FormNewGame.
 */
public class ControllerNewGame {

    private final int WIDTH = 854;
    private final int HEIGHT = 480;

    @FXML
    private Slider NumberOfPlayer;
    @FXML
    private Slider NumberOfAI;

    //adjust the number of ai sliber
    @FXML
    private void HandleNumberOfPlayer(){
        NumberOfAI.setMax(NumberOfPlayer.getValue());
    }
    //handle start game

    @FXML
    private void HandleStartGame(){
        Main.getGame().initGame((int)(NumberOfPlayer.getValue()), (int)(NumberOfAI.getValue()));

        try {
            Parent root = FXMLLoader.load(getClass().getResource("FormGame.fxml"));
            Main.setStage(root, WIDTH, HEIGHT);
            Main.getGame().nextTurn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void HandleBackBtn(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FormWelcome.fxml"));
            Main.setStage(root, WIDTH, HEIGHT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
