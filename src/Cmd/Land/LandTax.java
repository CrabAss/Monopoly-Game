package Cmd.Land;

import Cmd.Player.Player;

/**
 * The gird to pay tax.
 */
public class LandTax extends Land {
    /**
     * Initialize landTax.
     * @param name Initialization value.
     * @param gridNo Initialization value.
     */
    public LandTax(String name, int gridNo) {
        super(name, gridNo);
    }

    @Override
    public int run(Player player) {
        landOn(player);
        int Tax = player.getMoney() / 10;
        try { player.decMoney(Tax); }
        catch (Exception ignored) {}
        return Tax;
    }
}
