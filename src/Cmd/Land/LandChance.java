package Cmd.Land;

import Cmd.Others.BankruptException;
import Cmd.Player.Player;

import java.util.Random;

/**
 * The chance grid.
 */
public class LandChance extends Land {

    /**
     * Initialize landChance.
     * @param name Initialization value.
     * @param gridNo Initialization value.
     */
    public LandChance(String name, int gridNo) {
        super(name, gridNo);
    }

    @Override
    public int run(Player player) throws BankruptException {
        final int MAXINCREASEMONEY = 200;
        final int MAXDECREASEMONEY = 300;
        landOn(player);
        Random random = new Random();
        int incORdec = random.nextInt(1), moneyChange;

        if (incORdec == 0) moneyChange = random.nextInt(MAXINCREASEMONEY + 1);
        else moneyChange = -random.nextInt(MAXDECREASEMONEY + 1);
        player.changeMoney(moneyChange);
        return moneyChange;
    }
}
