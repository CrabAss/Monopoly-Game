package Land;

import Others.Output;
import Player.Player;

public class LandStart extends Land {
    private final int BONUSMONEY = 1500;
    public LandStart(String str) {
        super(str);
    }

    @Override
    public void run(Player player) {
        landOn(player);
        Output.println("Nothing happens.");
    }

    public void pass(Player player) {
        Output.println(player + " passes " + getName() + " Area.");
        player.incMoney(BONUSMONEY);
    }
}
