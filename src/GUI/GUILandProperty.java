package GUI;

import Cmd.Others.BankruptException;
import Cmd.Others.Output;
import Cmd.Others.Property;
import Cmd.Player.PlayerAI;

import java.util.Random;

/**
 * The GUILandProperty is used to connect the GUILand and player
 * The Main reason to seperate is the difference of input and output method between GUI and cmd
 */
public class GUILandProperty {
    private static Property curProperty;

    /**
     * @param curProperty it is the property that this class is dealing with
     */
    private static void setCurProperty(Property curProperty) {
        GUILandProperty.curProperty = curProperty;
    }

    /**
     * @return the current property
     */
    static Property getCurProperty() {
        return curProperty;
    }

    /**
     * @param Land Land is the land that player is standing on
     * @param player the current player
     * if the land is availiable, then player can buy this land or not.
     * if not than the player will pay money to the land owner
     */
    public void run(Cmd.Land.Land Land, Cmd.Player.Player player){
        Cmd.Land.LandProperty curLand = (Cmd.Land.LandProperty) Land;
        Cmd.Others.Property property = curLand.getProperty();
        curLand.landOn(player);
        setCurProperty(property);

        if (curLand.getProperty().getBelongs() == null) {
            Output.println("No one owns " + property.getName() + " now.");
            Main.getGame().getAction().setDisable(false);
            if (player instanceof PlayerAI){
                Random rand = new Random();
                if (rand.nextInt(2) == 0) {
                    Main.getGame().getControllerGame().HandleAction(); Main.getGame().getControllerGame().HandleEndTurn();}
                else Main.getGame().getControllerGame().HandleEndTurn();
            }
        }
        else {
            try {
                if (property.getBelongs() == player) {
                    Output.println(curLand.getName() + " belongs to " + player + " himself. \nNothing happens.");
                } else {
                    Output.println(curLand.getName() + " belongs to " + property.getBelongs() + " now.");
                    Output.println(player + " has to pay to " + property.getBelongs() + ".");
                    player.decMoney(property.getRent());
                    property.getBelongs().incMoney(property.getRent());
                }
                if (player instanceof PlayerAI) Main.getGame().getControllerGame().HandleEndTurn();
            } catch (BankruptException ignored) {
                Main.getGame().nextTurn();
            }
        }
    }
}
