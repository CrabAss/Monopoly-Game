package Cmd.Others;

import Cmd.Player.Player;

import java.io.Serializable;

/**
 * Record the property of the property land.
 */
public class Property implements Serializable {
    private String name;
    private int price, rent;
    private Player belongs = null;

    /**
     * Initialize property
     * @param name Initialization value
     * @param price Initialization value
     * @param rent Initialization value
     */
    public Property(String name, int price, int rent) {
        this.name = name;
        this.price = price;
        this.rent = rent;
    }

    /**
     * set the player that property belongs to
     * @param belongs Initialization value
     */
    public void setBelongs(Player belongs) {
        this.belongs = belongs;
        if (belongs == null) Output.println(name + " belongs to no one now.");
        else Output.println(name + " belongs to " + belongs + " now.");
    }

    /**
     * @return the name of property
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price of property
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return the rent of property
     */
    public int getRent() {
        return rent;
    }

    /**
     * @return the player that property belongs to
     */
    public Player getBelongs() {
        return belongs;
    }

    @Override
    public String toString() {
        return name + " (price: " + price + ", rent: " + rent + ")";
    }
}
