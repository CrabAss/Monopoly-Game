package Player;

import Land.Land;
import Others.Input;
import Others.Output;

public class PlayerUser extends Player {

    public PlayerUser(String str, Land startGrid) {
        super(str, startGrid);
    }

    @Override
    public int getInput(String hint, int limit) {
        System.out.println(name + " is deciding what to do...");
        System.out.println(hint);
        int inp = Input.getInt();
        while (!(inp >= 0 && inp < limit)) {
            System.out.println("Invalid Input.");
            System.out.println(hint);
            inp = Input.getInt();
        }
        return inp;
    }

    public Player toRobot() {
        Player player = new PlayerAI(name, money, status, jailDay, propertyList, position);
        return player;
    }
}
