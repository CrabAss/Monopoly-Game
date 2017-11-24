package Land;

import Others.BankruptException;
import Others.Output;
import Player.Player;

public class Land {
    String name;
    private Land nextLand;

    public Land(String str) {
        name = str;
    }

    public Land getNextLand() {
        return nextLand;
    }

    public void setNextLand(Land nextLand) {
        this.nextLand = nextLand;
    }

    public void run(Player player) throws BankruptException {
        Output.println(player + " reaches " + name + " Area.");
        Output.println("Nothing happens.");
    }

    @Override
    public String toString() {
        return name;
    }
}
