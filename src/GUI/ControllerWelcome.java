package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ControllerWelcome {
    @FXML
    private Button NewGame;
    @FXML
    private Button LoadGame;
    @FXML
    private Button Quit;

    @FXML
    public void HandleNewGame(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("FormNewGame.fxml"));
            Main.setStage(root);
        } catch (Exception e){
            System.out.print(e);
        }
    }

    @FXML
    public void HandleLoadGame(ActionEvent event){

    }

    @FXML
    public void HandleQuit(ActionEvent event){
        System.exit(0);
    }
}
