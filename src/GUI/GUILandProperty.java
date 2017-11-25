package GUI;

import Cmd.Land.LandProperty;
import Cmd.Others.BankruptException;
import Cmd.Others.Output;
import Cmd.Others.Property;
import sun.swing.BakedArrayList;

public class GUILandProperty {
    private static Property curProperty;

    public static void setCurProperty(Property curProperty) {
        GUILandProperty.curProperty = curProperty;
    }
    public static Property getCurProperty() {
        return curProperty;
    }

    public void run(Cmd.Land.Land Land, Cmd.Player.Player player){
        Cmd.Land.LandProperty curLand = (Cmd.Land.LandProperty)Land;
        Cmd.Others.Property property = curLand.getProperty();
        curLand.landOn(player);
        setCurProperty(property);

        if (curLand.getProperty().getBelongs() == null) {
            Output.printlnAndDelay("No one owns " + property.getName() + " now.");
            Main.getGame().Action.setDisable(false);
        }
        else {
            try {
                if (property.getBelongs() == player){
                    Output.printlnAndDelay(curLand.getName() + " belongs to " + player + " himself. Nothing happens");}
                else {
                    Output.printlnAndDelay(curLand.getName() + " belongs to " + property.getBelongs() + " now.");
                    Output.printlnAndDelay(player + " has to paid to " + property.getBelongs() + ".");
                    player.decMoney(property.getRent());
                    property.getBelongs().incMoney(property.getRent());
                }
            }catch (BankruptException e){
                return;
            }
        }
    }
}