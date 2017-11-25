package Cmd.Land;

import Cmd.Others.BankruptException;
import Cmd.Others.Output;
import Cmd.Player.Player;

abstract public class Land {
    private final String name;
    private final int gridNo;
    private Land nextLand;
    public Land(String name, int gridNo) {
        this.name = name;
        this.gridNo = gridNo;
    }

    public void landOn(Player player) {
        Output.printlnAndDelay(player + " reaches " + this + ".");
    }

    abstract public void run(Player player) throws BankruptException;

    public String getName() {
        return name;
    }

    public Land getNextLand() {
        return nextLand;
    }

    public void setNextLand(Land nextLand) {
        this.nextLand = nextLand;
    }

    @Override
    public String toString() {
        return name + "Area (Grid " + gridNo + ")";
    }
}
