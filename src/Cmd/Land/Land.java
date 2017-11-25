package Cmd.Land;

import Cmd.Others.BankruptException;
import Cmd.Others.Output;
import Cmd.Player.Player;

import java.io.Serializable;

/**
 * The superclass of all grids.
 */
abstract public class Land implements Serializable {
    private final String name;
    private final int gridNo;
    private Land nextLand;

    /**
     * Initialize land.
     * @param name Initialization value.
     * @param gridNo Initialization value.
     */
    public Land(String name, int gridNo) {
        this.name = name;
        this.gridNo = gridNo;
    }

    /**
     * Print message that the player lands on a grid.
     * @param player The player who is on the grid.
     */
    public void landOn(Player player) {
        Output.println(player + " reaches " + this + ".");
    }

    /**
     * Call the event of the current grid.
     * @param player The player who is on the grid.
     * @throws BankruptException May throws BankruptException.
     */
    abstract public int run(Player player) throws BankruptException;

    /**
     * @return The name of the land.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The next grid of the land.
     */
    public Land getNextLand() {
        return nextLand;
    }

    /**
     * Set the next grid of the land.
     * @param nextLand The next grid to set.
     */
    public void setNextLand(Land nextLand) {
        this.nextLand = nextLand;
    }

    @Override
    public String toString() {
        return name + " Area (Grid " + gridNo + ")";
    }
}
