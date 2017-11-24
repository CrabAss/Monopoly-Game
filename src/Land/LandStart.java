package Land;

import Others.Output;
import Player.Player;

public class LandStart extends Land {
    public LandStart(String str) {
        super(str);
    }

    @Override
    public void run(Player player) {
        Output.println(player + " reaches " + name + " Area.");
        Output.println("Nothing happens.");
    }

    public void pass(Player player) {
        Output.println(player + " passes " + name + " Area.");
        player.incMoney(1500);
    }
}
