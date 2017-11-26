package Cmd.Land;

import Cmd.Others.Output;
import Cmd.Player.Player;

/**
 * The start grid.
 */
public class LandStart extends Land {
    private final int BONUSMONEY = 1500;
    /**
     * Initialize landStart.
     * @param name Initialization value.
     * @param gridNo Initialization value.
     */
    public LandStart(String name, int gridNo) {
        super(name, gridNo);
    }

    @Override
    public void run(Player player) {
        landOn(player);
        Output.println("Nothing happens.");
        player.incMoney(BONUSMONEY);
    }

    /**
     * Player passes the start grid and earns money.
     * @param player The player who passes.
     */
    public void pass(Player player) {
        Output.println(player + " passes " + this + ".");
        player.incMoney(BONUSMONEY);
    }
}
