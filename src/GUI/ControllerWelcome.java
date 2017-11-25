package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControllerWelcome {

    private final int NEWGAMEWIDTH = 600;
    private final int NEWGAMEHEIGHT = 204;

    @FXML
    private Button NewGame;
    @FXML
    private Button LoadGame;
    @FXML
    private Button Quit;

    @FXML
    private void HandleNewGame(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("FormNewGame.fxml"));
            Main.setStage(root, NEWGAMEWIDTH, NEWGAMEHEIGHT);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void HandleLoadGame(ActionEvent event){

    }

    @FXML
    private void HandleQuit(ActionEvent event){
        System.exit(0);
    }

}
