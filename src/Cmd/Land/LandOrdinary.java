package Cmd.Land;

import Cmd.Others.Output;
import Cmd.Player.Player;

/**
 * Grid without event.
 */
public class LandOrdinary extends Land {

    /**
     * Initialize landChance.
     * @param name   Initialization value.
     * @param gridNo Initialization value.
     */
    public LandOrdinary(String name, int gridNo) {
        super(name, gridNo);
    }

    @Override
    public void run(Player player) {
        landOn(player);
        Output.println("Nothing happens.");
    }
}
