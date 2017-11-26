package Cmd.Land;

import Cmd.Player.Player;

/**
 * The go to jail land.
 */
public class LandGotoJail extends Land {
    private Land jailGrid;

    /**
     * Initialize landGotoJail.
     * @param name Initialization value.
     * @param gridNo Initialization value.
     * @param jailGrid Initialization value.
     */
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
