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
     * Initialize the land.
     * @param name Initialization value.
     * @param gridNo Initialization value.
     */
    public Land(String name, int gridNo) {
        this.name = name;
        this.gridNo = gridNo;
    }

    /**
     * Output the message that player reaches the land.
     * @param player The player who lands.
     */
    public void landOn(Player player) {
        Output.println(player + " reaches " + this + ".");
    }

    /**
     * Conduct the event of the land.
     * @param player The player who lands.
     * @exception BankruptException May throw bankrupt exception.
     */
    abstract public void run(Player player) throws BankruptException;

    /**
     * @return The name of the grid.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The next land of the grid.
     */
    public Land getNextLand() {
        return nextLand;
    }

    /**
     * Set The next land of the grid.
     * @param nextLand the next land to set.
     */
    public void setNextLand(Land nextLand) {
        this.nextLand = nextLand;
    }

    @Override
    public String toString() {
        return name + " Area (Grid " + gridNo + ")";
    }
}
