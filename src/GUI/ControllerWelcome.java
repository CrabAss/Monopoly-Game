package GUI;

import Cmd.Land.LandProperty;
import Cmd.Others.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Controller for the JavaFX Form: FormWelcome.
 */
public class ControllerWelcome {

    @FXML
    private void HandleNewGame(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("FormNewGame.fxml"));
            final int NEWGAMEWIDTH = 600;
            final int NEWGAMEHEIGHT = 204;
            Main.setStage(root, NEWGAMEWIDTH, NEWGAMEHEIGHT);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void HandleLoadGame(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //System.out.println(pic.getId());

        File file = fileChooser.showOpenDialog(Main.getMainStage());
        if (file != null) {
            Main.newGame();
            Main.getGame().initGame(6, 0);

            try {
                final int WIDTH = 854;
                final int HEIGHT = 480;
                Parent root = FXMLLoader.load(getClass().getResource("FormGame.fxml"));
                Main.setStage(root, WIDTH, HEIGHT);
            } catch (Exception e){
                e.printStackTrace();
            }
            try {
                Main.getGame().setLoadPath(file.getAbsolutePath());
                Main.getGame().loadGame();
            } catch (Exception e) {
                //Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, e);
            }
            for (int i = 0; i < Main.getGame().getPlayerNumber(); i++)
                Main.getGame().getGUIhelper()[i].setPlayer(Main.getGame().getPlayerList()[i]);
            for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++){
                if (Main.getGame().getLandList()[i] instanceof LandProperty){
                    ((LandProperty)Main.getGame().getLandList()[i]).getProperty().setBelongs(null);
                }
            }
            for (int i = 0; i < Main.getGame().getPlayerNumber(); i++) {
                for (Property x : Main.getGame().getPlayerList()[i].getPropertyList()) {
                    x.setBelongs(Main.getGame().getPlayerList()[i]);
                }
            }
            Main.getGame().setCurPlayer(Main.getGame().getCurrentPlayer());
            Main.getGame().setCurPlayer(Main.getGame().getCurPlayer() - 1);
            Main.getGame().nextTurn();
        }
    }

    @FXML
    private void HandleQuit(){
        System.exit(0);
    }

}
