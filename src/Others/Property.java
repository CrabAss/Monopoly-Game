package Others;

import Player.Player;

public class Property {
    String name;
    int price, rent;
    Player belongs;

    public Property(String name, int price, int rent) {
        this.name = name;
        this.price = price;
        this.rent = rent;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public Player getBelongs() {
        return belongs;
    }

    public void setBelongs(Player belongs) {
        this.belongs = belongs;
        if (belongs == null) Output.println(name + " belongs to no one now.");
        else Output.println(name + " belongs to " + belongs + " now.");
    }

    @Override
    public String toString() {
        return name + "(price: " + price + ", rent: " + rent + ")";
    }
}
