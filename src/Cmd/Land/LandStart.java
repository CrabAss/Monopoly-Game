package Cmd.Land;

import Cmd.Others.Output;
import Cmd.Player.Player;

public class LandStart extends Land {
    private final int BONUSMONEY = 1500;
    public LandStart(String name, int gridNo) {
        super(name, gridNo);
    }

    @Override
    public void run(Player player) {
        landOn(player);
        Output.printlnAndDelay("Nothing happens.");
    }

    public void pass(Player player) {
        Output.printlnAndDelay(player + " passes " + this + ".");
        player.incMoney(BONUSMONEY);
    }
}
