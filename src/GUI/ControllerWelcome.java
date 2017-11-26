package GUI;

import Cmd.Land.LandProperty;
import Cmd.Others.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class ControllerWelcome {

    private final int NEWGAMEWIDTH = 600;
    private final int NEWGAMEHEIGHT = 204;

    private final int WIDTH = 854;
    private final int HEIGHT = 480;

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //System.out.println(pic.getId());

        File file = fileChooser.showOpenDialog(Main.getMainStage());
        if (file != null) {
            Main.getGame().initGame(6, 0);

            try{
                Parent root = FXMLLoader.load(getClass().getResource("FormGame.fxml"));
                Main.setStage(root, WIDTH, HEIGHT);
            } catch (Exception e){
                e.printStackTrace();
            }
            try {
                Main.getGame().setLoadPath(file.getAbsolutePath());
                Main.getGame().loadGame();
            } catch (Exception ex) {
                //Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < Main.getGame().getPlayerNumber(); i++)
                Main.getGame().getGUIhelper()[i].setPlayer(Main.getGame().playerList[i]);
            for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++){
                if (Main.getGame().landList[i] instanceof LandProperty){
                    ((LandProperty)Main.getGame().landList[i]).getProperty().setBelongs(null);
                }
            }
            for (int i = 0; i < Main.getGame().getPlayerNumber(); i++) {
                for (Property x : Main.getGame().playerList[i].propertyList) {
                    x.setBelongs(Main.getGame().playerList[i]);
                }
            }
            Main.getGame().setCurPlayer(Main.getGame().getCurrentPlayer());
            Main.getGame().setCurPlayer(Main.getGame().getCurPlayer() - 1);
            Main.getGame().nextTurn();
        }
    }

    @FXML
    private void HandleQuit(ActionEvent event){
        System.exit(0);
    }

}
