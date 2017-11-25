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
    public void move(int step){
        for (int i = 1; i <= step; i++) {
            player.setPosition(player.getPosition().getNextLand());
            if (i < step && player.getPosition() instanceof LandStart)
                ((LandStart) player.getPosition()).pass(player);

        }
    }
    public void run(){
        GUIOutput guiOutput= Main.getGame().getGuiOutput();
        try {
            int step = -1;

            /*if (player.isInJail()) {
                int jailDay = player.getJailDay();
                player.setJailDay(jailDay + 1);

                guiOutput.Print(player+ " has been stayed in jail for " + jailDay + " turns.");
                if (jailDay <= 3) {
                    guiOutput.Print(player + " has to decide paid to release or dice. ");
                    guiOutput.Print("(will get release if doubles is thrown)");
                    int inp = getInput(jailHint, 2);

                    if (inp == 0) {
                        Output.printlnAndDelay(name + " decides to pay.");
                        decMoney(90);
                        release();
                    } else {
                        Output.printlnAndDelay(name + " decides to dice.");
                        dice.dice(this);
                        if (dice.isEqual()) {
                            release();
                            step = dice.getStep();
                        }
                    }
                } else {
                    Output.printlnAndDelay(name + " must pay to release.");
                    decMoney(90);
                    release();
                }
            }*/

            if (!player.isInJail()) {
                Main.getGame().Action.setDisable(true);
                Dice dice = new Dice();
                if (step == -1) {
                    dice.dice();
                    step = dice.getStep();
                }
                Main.getGame().Dice1.setImage(new Image("GUI/resources/d" + dice.getX() + ".jpg"));
                Main.getGame().Dice2.setImage(new Image("GUI/resources/d" + dice.getY() + ".jpg"));
                System.out.print(step);
                move(step);
                Main.getGame().controllerGame.updateGraph();
                player.getPosition().run(player);
            }
        } catch (BankruptException e) {
            return;
        }
        guiOutput.Print(player + "'s turn ends.");
    }

}
