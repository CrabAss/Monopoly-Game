package Cmd.Others;

import Cmd.Player.Player;

import java.util.Random;

public class Dice {
    private Random random = new Random();
    private int x, y, sum;

    public void dice() {
        x = random.nextInt(6) + 1;
        y = random.nextInt(6) + 1;
        sum = x + y;
    }
    public void dice(Player player) {
        dice();
        Output.printlnAndDelay(player + " dices " + x + " and " + y + '.');
    }
    public int getX() {return x;}

    public int getY() {
        return y;
    }

    public int getStep() {
        return sum;
    }

    public boolean isEqual() {
        return x == y;
    }
}
