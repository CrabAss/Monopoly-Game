package Land;

import Others.BankruptException;
import Others.Property;
import Others.Output;
import Player.Player;

public class LandProperty extends Land {
    private final Property property;

    public LandProperty(Property x) {
        super(x.getName());
        this.property = x;
    }

    @Override
    public void run(Player player) throws BankruptException {
        landOn(player);

        String hint = "0: do nothing; 1: rent " + property.toString() + ".";

        if (property.getBelongs() == null) {
            Output.println("No one owns " + property.getName() + " now.");
            int inp = player.getInput(hint, 2);
            if (inp == 0) Output.println(player + " decides to do nothing.");
            else {
                Output.println(player + " decides to rent " + property.toString() + ".");
                player.addProperty(property);
                player.decMoney(property.getPrice());
                property.setBelongs(player);
            }
        }
        else {
            if (property.getBelongs() == player)
                Output.println(name + " belongs to " + player + " himself. Nothing happens");
            else {
                Output.println(name + " belongs to " + property.getBelongs() + " now.");
                Output.println(player + " has to paid to " + property.getBelongs() + ".");
                player.decMoney(property.getRent());
                property.getBelongs().incMoney(property.getRent());
            }
        }
    }
}
