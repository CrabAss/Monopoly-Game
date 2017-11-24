package Land;

import Others.BankruptException;
import Others.Output;
import Player.Player;

public class LandTax extends Land {
    public LandTax(String name, int gridNo) {
        super(name, gridNo);
    }

    @Override
    public void run(Player player) {
        landOn(player);
        int Tax = player.getMoney() / 10;
        try { player.decMoney(Tax); }
        catch (Exception e) {}
    }
}
