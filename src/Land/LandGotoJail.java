package Land;

import Others.BankruptException;
import Others.Output;
import Player.Player;

public class LandGotoJail extends Land {
    private Land jailGrid;

    public LandGotoJail(String name, int gridNo, Land jailGrid) {
        super(name, gridNo);
        this.jailGrid = jailGrid;
    }

    @Override
    public void run(Player player) {
        landOn(player);
        player.gotoJail(jailGrid);
    }
}
