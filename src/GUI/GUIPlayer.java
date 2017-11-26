package GUI;

import Cmd.Others.BankruptException;
import Cmd.Player.Player;
import Cmd.Others.Dice;
import Cmd.Player.PlayerAI;
import javafx.scene.image.*;

import java.util.Random;

/**
 *  GUI Player contains a player
 */
public class GUIPlayer {

    private Player player;

    /**
     * @param player the player
     */
    GUIPlayer(Player player){
        this.player = player;
    }

    /**
     * @param player set the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the money
     */
    int getMoney() {return player.getMoney();}

    /**
     * @return the player current position
     */
    String getStatus(){
        if (player.isInJail()) return "In Jail";
        else if (player.isDead()) return "Eliminated";
        else return "Playing";
    }

    /**
     * @return the GUI postion of player
     */
    int getPosition(){
        for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++)
            if (player.getPosition() == Main.getGame().getLandList()[i]) return i;
        return 0;
    }

    /**
     * For GUIplayer to run
     * judge if the player is in Jail first, if yes, then adjust menu,
     * if not, then dice and go
     */
    public void run(){
        GUIOutput guiOutput = Main.getGame().getGuiOutput();
        try {
            Main.getGame().getAction().setDisable(true);

            if (player.isDead())
                Main.getGame().getControllerGame().HandleEndTurn();

            else if (player.isInJail()) {
                int jailDay = player.getJailDay();
                player.setJailDay(jailDay + 1);

                guiOutput.Print(player+ " has stayed in jail for " + jailDay + " turn(s).");
                if (jailDay <= 3) {
                    guiOutput.Print(player + " has to decide to whether pay to release or dice. ");
                    guiOutput.Print("(will get released if a double is thrown)");
                    Main.getGame().getAction().setDisable(false);
                    Main.getGame().getEndTurn().setText("Dice");
                    if (player instanceof PlayerAI){
                        Random rand = new Random();
                        if (rand.nextInt(2) == 0)
                            Main.getGame().getControllerGame().HandleAction();
                        else
                            Main.getGame().getControllerGame().HandleEndTurn();
                    }
                }

            } else {
                Dice dice = new Dice();
                dice.dice();
                int step = dice.getStep();
                Main.getGame().getDice1().setImage(new Image("GUI/resources/d" + dice.getX() + ".jpg"));
                Main.getGame().getDice2().setImage(new Image("GUI/resources/d" + dice.getY() + ".jpg"));

                player.move(step);
                Main.getGame().getControllerGame().updateGraph();

                if (player.getPosition() instanceof Cmd.Land.LandProperty){
                    GUILandProperty Guimodule = new GUILandProperty();
                    Guimodule.run(player.getPosition(), player);
                } else {
                    player.getPosition().run(player);
                    if (player instanceof PlayerAI) {
                        Main.getGame().getControllerGame().HandleEndTurn();
                    }
                }
            }
        } catch (BankruptException e) {
            Main.getGame().nextTurn();
        }
    }
}
