package Cmd.Land;

import Cmd.Others.Output;
import Cmd.Player.Player;

public class LandOrdinary extends Land {
    public LandOrdinary(String name, int gridNo) {
        super(name, gridNo);
    }

    public int run(Player player) {
        landOn(player);
        Output.println("Nothing happens.");
        return 0;
    }
}
