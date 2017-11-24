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
        landOn(player);
        int Tax = player.getMoney() / 10;
        player.decMoney(Tax);
    }
}
