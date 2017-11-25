package Cmd.Land;

import Cmd.Player.Player;

public class LandGotoJail extends Land {
    private Land jailGrid;

    public LandGotoJail(String name, int gridNo, Land jailGrid) {
        super(name, gridNo);
        this.jailGrid = jailGrid;
    }

    @Override
    public int run(Player player) {
        landOn(player);
        player.gotoJail(jailGrid);
        return 0;
    }
}
