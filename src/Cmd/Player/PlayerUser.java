package Cmd.Player;

import Cmd.Land.Land;
import Cmd.Others.Input;
import Cmd.Others.Output;
import Cmd.Others.Property;

import java.util.List;

public class PlayerUser extends Player {

    public PlayerUser(String str, Land startGrid) {
        super(str, startGrid);
    }

    PlayerUser(String name, int money, int status, int jailDay, List<Property> propertyList, Land position) {
        super(name, money, status, jailDay, propertyList, position);
    }

    @Override
    public int getInput(String hint, int limit) {
        Output.println(name + " is making decisions...");
        return Input.getInput(hint, limit);
    }

    public Player toRobot() {
        return new PlayerAI(name, money, status, jailDay, propertyList, position);
    }
}
