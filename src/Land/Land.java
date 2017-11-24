package Land;

import Others.BankruptException;
import Others.Output;
import Player.Player;

public class Land {
    private String name;
    private Land nextLand;

    public Land(String str) {
        name = str;
    }
    public String getName() { return name;}

    public Land getNextLand() {
        return nextLand;
    }

    public void setNextLand(Land nextLand) {
        this.nextLand = nextLand;
    }

    public void landOn(Player player){
        Output.println(player + " reaches " + name + " Area.");
    }
    public void run(Player player) throws BankruptException {
        Output.println("Nothing happens.");
    }

    @Override
    public String toString() {
        return name;
    }
}
