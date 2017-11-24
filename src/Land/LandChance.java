package Land;

import Others.BankruptException;
import Others.Output;
import Player.Player;

import java.util.Random;

public class LandChance extends Land {
    private final int MAXINCREASEMONEY = 200;
    private final int MAXDECREASEMONEY = 300;
    public LandChance(String str) {
        super(str);
    }

    @Override
    public void run(Player player) throws BankruptException {
        landOn(player);
        Random random = new Random();
        int incORdec = random.nextInt(1);

        if (incORdec == 0) player.incMoney(random.nextInt(MAXINCREASEMONEY + 1));
        else player.decMoney(random.nextInt(MAXDECREASEMONEY + 1));
    }
}
