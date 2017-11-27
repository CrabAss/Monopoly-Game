package Cmd.Player;

import Cmd.Land.Land;
import Cmd.Others.Property;

import java.util.List;
import java.util.Random;

/**
 * Players controlled by AI.
 */
public class PlayerAI extends Player {
    private Random random;

    /**
     * Initialize playerAI.
     * @param name      Initialization value.
     * @param position  Initialization value.
     */
    public PlayerAI(String name, Land position) {
        super(name, position);
        random = new Random();
    }

    /**
     * Initialize playerAI.
     * @param name          Initialization value.
     * @param money         Initialization value.
     * @param status        Initialization value.
     * @param jailDay       Initialization value.
     * @param propertyList  Initialization value.
     * @param position      Initialization value.
     */
    PlayerAI(String name, int money, int status, int jailDay, List<Property> propertyList, Land position) {
        super(name, money, status, jailDay, propertyList, position);
        random = new Random();
    }

    @Override
    public int getInput(String hint, int limit) {
        return random.nextInt(limit);
    }
}
