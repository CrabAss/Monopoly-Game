package Land;

import Others.BankruptException;
import Others.Output;
import Player.Player;

public class LandGotoJail extends Land {
    Land JailGrid;

    public LandGotoJail(String str, Land JailGrid) {
        super(str);
        this.JailGrid = JailGrid;
    }

    @Override
    public void run(Player player) throws BankruptException {
        landOn(player);
        player.gotoJail(JailGrid);
    }
}
