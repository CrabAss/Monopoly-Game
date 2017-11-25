package Cmd.Others;

import Cmd.Player.Player;

import java.io.Serializable;
import java.util.Random;

/**
 * Contains method for dice of the game.
 */
public class Dice implements Serializable {
    private Random random = new Random();
    private int x, y, sum;

    /**
     * To dice once.
     */
    public void dice() {
        x = random.nextInt(6) + 1;
        y = random.nextInt(6) + 1;
        sum = x + y;
    }

    /**
     * To dice once. Output the message.
     * @param player The player who dices.
     */
    public void dice(Player player) {
        dice();
        Output.println(player + " dices " + x + " and " + y + '.');
    }

    /**
     * @return The number of the first dice.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The number of the second dice.
     */
    public int getY() {
        return y;
    }

    /**
     * @return The total steps that the player should move.
     */
    public int getStep() {
        return sum;
    }

    /**
     * @return True if there's a doubles.
     */
    public boolean isEqual() {
        return x == y;
    }
}
