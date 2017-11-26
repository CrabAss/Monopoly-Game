package Cmd.Player;

import Cmd.Land.Land;
import Cmd.Others.Input;
import Cmd.Others.Output;
import Cmd.Others.Property;

import java.util.List;

/**
 * Players controlled by human.
 */
public class PlayerUser extends Player {

    /**
     * Initialize playerUser.
     * @param name Initialization value.
     * @param position Initialization value.
     */
    public PlayerUser(String name, Land position) {
        super(name, position);
    }

    @Override
    public int getInput(String hint, int limit) {
        Output.println(getName() + " is making decisions...");
        return Input.getInput(hint, limit);
    }
}
