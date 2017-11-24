package Land;

import Others.BankruptException;
import Others.Output;
import Player.Player;

public class LandTax extends Land {
    public LandTax(String str) {
        super(str);
    }

    @Override
    public void run(Player player) throws BankruptException {
        Output.println(player + " reaches " + name + " Area.");
        player.decMoney(player.getMoney() / 10);
    }
}
