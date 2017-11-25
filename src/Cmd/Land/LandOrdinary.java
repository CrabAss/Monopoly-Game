package Cmd.Land;

import Cmd.Others.Output;
import Cmd.Player.Player;

/**
 * The land that has no event.
 */
public class LandOrdinary extends Land {
    /**
     * Initialize landOrdinary.
     * @param name Initialization value.
     * @param gridNo Initialization value.
     */
    public LandOrdinary(String name, int gridNo) {
        super(name, gridNo);
    }

    @Override
    public int run(Player player) {
        landOn(player);
        Output.println("Nothing happens.");
        return 0;
    }
}
