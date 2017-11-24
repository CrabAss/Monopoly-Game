package Player;

import Land.Land;
import Others.Property;

import java.util.List;
import java.util.Random;

public class PlayerAI extends Player {
    Random random;

    public PlayerAI(String str, Land startGrid) {
        super(str, startGrid);
        random = new Random();
    }

    PlayerAI(String name, int money, int status, int jailDay, List<Property> propertyList, Land position) {
        super(name, money, status, jailDay, propertyList, position);
        random = new Random();
    }

    @Override
    public int getInput(String hint, int limit) {
        return random.nextInt(limit);
    }
}
