package GUI;

import Cmd.Land.LandStart;
import Cmd.Others.BankruptException;
import Cmd.Player.Player;
import Cmd.Others.Dice;
import javafx.scene.image.*;
import static java.lang.Thread.sleep;

public class GUIPlayer {

    private Player player;

    public GUIPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getMoney() {return player.getMoney();}

    public String getStatus(){
        if (player.isInJail()) return "In Jail";
        else if (player.isDead()) return "Eliminated";
        else return "Playing";
    }

    public int getPosition(){
        for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++)
            if (player.getPosition() == Main.getGame().landList[i]) return i;
        return 0;
    }

    public void run(){
        GUIOutput guiOutput= Main.getGame().getGuiOutput();
        try {
            int step = -1;
            Main.getGame().Action.setDisable(true);

            if (player.isDead()) {Main.getGame().controllerGame.HandleEndTurn();return;}

            if (player.isInJail()) {
                int jailDay = player.getJailDay();
                player.setJailDay(jailDay + 1);

                guiOutput.Print(player+ " has been stayed in jail for " + jailDay + " turns.");
                if (jailDay <= 3) {
                    guiOutput.Print(player + " has to decide paid to release or dice. ");
                    guiOutput.Print("(will get release if doubles is thrown)");
                    Main.getGame().Action.setDisable(false);
                    Main.getGame().EndTurn.setText("Dice");
                }
            }else if (!player.isInJail()) {
                Dice dice = new Dice();
                if (step == -1) {
                    dice.dice();
                    step = dice.getStep();
                }
                Main.getGame().Dice1.setImage(new Image("GUI/resources/d" + dice.getX() + ".jpg"));
                Main.getGame().Dice2.setImage(new Image("GUI/resources/d" + dice.getY() + ".jpg"));

                player.move(step);

                Main.getGame().controllerGame.updateGraph();
                if (player.getPosition() instanceof Cmd.Land.LandProperty){
                    GUILandProperty Guimodule = new GUILandProperty();
                    Guimodule.run(player.getPosition(), player);
                }else player.getPosition().run(player);
            }
        } catch (BankruptException e) {
            Main.getGame().nextTurn();
        }
    }

}
