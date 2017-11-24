package Land;

import Others.BankruptException;
import Others.Output;
import Player.Player;

import java.util.Random;

public class LandChance extends Land {
    public LandChance(String str) {
        super(str);
    }

    @Override
    public void run(Player player) throws BankruptException {
        Output.println(player + " reaches " + name + " Area.");
        Random random = new Random();
        int money = random.nextInt(501) - 300;
        if (money >= 0) player.incMoney(money);
        else player.decMoney(-money);
    }
}
