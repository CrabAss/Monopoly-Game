package Cmd.Land;

import Cmd.Others.BankruptException;
import Cmd.Others.Property;
import Cmd.Others.Output;
import Cmd.Player.Player;

public class LandProperty extends Land {
    private final Property property;

    public LandProperty(Property property, int gridNo) {
        super(property.getName(), gridNo);
        this.property = property;
    }

    @Override
    public void run(Player player) throws BankruptException {
        landOn(player);

        String hint = "0: do nothing; 1: rent " + property.toString() + ".";

        if (property.getBelongs() == null) {
            Output.printlnAndDelay("No one owns " + property.getName() + " now.");
            int inp = player.getInput(hint, 2);
            if (inp == 0) Output.printlnAndDelay(player + " decides to do nothing.");
            else {
                Output.printlnAndDelay(player + " decides to rent " + property.toString() + ".");
                player.addProperty(property);
                player.decMoney(property.getPrice());
                property.setBelongs(player);
            }
        }
        else {
            if (property.getBelongs() == player)
                Output.printlnAndDelay(getName() + " belongs to " + player + " himself. Nothing happens");
            else {
                Output.printlnAndDelay(getName() + " belongs to " + property.getBelongs() + " now.");
                Output.printlnAndDelay(player + " has to paid to " + property.getBelongs() + ".");
                player.decMoney(property.getRent());
                property.getBelongs().incMoney(property.getRent());
            }
        }
    }
}
