package Cmd.Player;

import Cmd.Others.Dice;
import Cmd.Others.Property;
import Cmd.Others.BankruptException;
import Cmd.Others.Output;
import Cmd.Land.Land;
import Cmd.Land.LandStart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The superclass of PlayerAI and PlayerUser.
 */
public abstract class Player implements Serializable {
    private static final int JAILPENALTY = 90;
    private static final int STARTGRIDMONEY = 1500;
    private String name;
    private int money;
    private int status; // 0: Normal; 1: InJail; 2: Dead;
    private int jailDay;
    private List<Property> propertyList = new ArrayList<>();
    private Land position;
    private Dice dice = new Dice();

    /**
     * Initialize player.
     * @param name Initialization value.
     * @param position Initialization value.
     */
    public Player(String name, Land position) {
        this.name = name;
        money = STARTGRIDMONEY;
        status = jailDay = 0;
        this.position = position;
    }

    /**
     * Initialize player.
     * @param name Initialization value.
     * @param money Initialization value.
     * @param status Initialization value.
     * @param jailDay Initialization value.
     * @param propertyList Initialization value.
     * @param position Initialization value.
     */
    public Player(String name, int money, int status, int jailDay, List<Property> propertyList, Land position) {
        this(name, position);
        this.money = money;
        this.status = status;
        this.jailDay = jailDay;
        this.propertyList = propertyList;
    }

    /**
     * Get response from the player when he has to make a choice.
     * @param hint The guidance message.
     * @param limit The number of choices.
     * @return An integer as the choice of the player.
     */
    public abstract int getInput(String hint, int limit);

    /**
     * Player moves forwards for some steps.
     * @param step The number of steps the player should move.
     */
    public void move(int step) {
        for (int i = 1; i <= step; i++) {
            position = position.getNextLand();
            if (i < step && position instanceof LandStart)
                ((LandStart) position).pass(this);
        }
    }

    /**
     * @return true if the player is in jail.
     */
    public boolean isInJail() {
        return status == 1;
    }

    /**
     * @return true if the player is retired or bankrupted.
     */
    public boolean isDead() {
        return status == 2;
    }

    /**
     * Conduct a turn of the player.
     */
    public void run() {
        String jailHint = "0: pay to leave; 1: dice.";

        if (isDead()) return;
        Output.println(Output.title(name + "'s Turn"));
        Output.println(name + "'s current money: " + money + " HKD.");

        try {
            int step = -1;

            if (isInJail()) {
                jailDay++;
                Output.println(name + " has stayed in jail for " + jailDay + " turn(s).");
                if (jailDay <= 2) {
                    Output.print(name + " has to decide paid to release or dice. ");
                    Output.println("(will get released if a double is thrown)");
                    int inp = getInput(jailHint, 2);

                    if (inp == 0) {
                        Output.println(name + " decides to pay.");
                        decMoney(JAILPENALTY);
                        release();
                    } else {
                        Output.println(name + " decides to dice.");
                        dice.dice(this);
                        if (dice.isEqual()) {
                            release();
                            step = dice.getStep();
                        }
                    }
                } else {
                    Output.println(name + " must pay to release.");
                    decMoney(JAILPENALTY);
                    release();
                }
            }

            if (!isInJail()) {
                if (step == -1) {
                    dice.dice(this);
                    step = dice.getStep();
                }
                move(step);
                position.run(this);
            }
        } catch (BankruptException e) {
            return;
        }
        Output.println(name + "'s turn ends.");
    }

    /**
     * The player is sent to jail.
     * @param JailGrid the position of jailGrid.
     */
    public void gotoJail(Land JailGrid) {
        Output.println(name + " is sent to jail.");
        status = 1;
        jailDay = 0;
        position = JailGrid;
    }

    /**
     * The player is released from jail.
     */
    public void release() {
        Output.println(name + " is released now.");
        status = 0;
    }

    /**
     * Increase the money of the player.
     * @param val the value of money to be increased.
     */
    public void incMoney(int val) {
        Output.println(name + " earns " + val + " HKD.");
        money += val;
        Output.println(name + " currently has " + money + " HKD.");
    }

    /**
     * Decrease the money of the player.
     * @param val the value of money to be decreased.
     * @throws BankruptException may throws the BankruptException.
     */
    public void decMoney(int val) throws BankruptException {
        Output.println(name + " pays " + val + " HKD.");
        money -= val;
        Output.println(name + " currently has " + money + " HKD.");
        if (money <= 0) {bankrupt(); throw new BankruptException();}
    }

    /**
     * Add the property to the propertyList of the player.
     * @param property The property to be added.
     */
    public void addProperty(Property property) {
        propertyList.add(property);
    }

    /**
     * A player retired.
     * To remove the player out of the game.
     * @throws BankruptException emit the signal of bankrupt to terminate the turn of the player.
     */
    private void bankrupt() throws BankruptException {
        Output.println(name + " runs out of money.");
        Output.println(name + " is bankrupted and eliminated.");
        status = 2;
        for (Property x : propertyList)
            x.setBelongs(null);
    }

    /**
     * A player is retired.
     * To remove the player out of the game.
     */
    public void retired() {
        status = 2;
        for (Property x : propertyList)
            x.setBelongs(null);
    }

    /**
     * Turn a player to robot controlled.
     * @return A class of PlayerAI
     */
    public Player toRobot() {
        return new PlayerAI(name, money, status, jailDay, propertyList, position);
    }

    /**
     * @return The money of the player.
     */
    public int getMoney() {
        return money;
    }

    /**
     * @return The position of the player.
     */
    public Land getPosition() {
        return position;
    }

    /**
     * @return The property list of the player.
     */
    public List<Property> getPropertyList() {
        return propertyList;
    }

    /**
     * @return The number of days the player has been in jail.
     */
    public int getJailDay() {
        return jailDay;
    }

    /**
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the number of days the player has been in jail.
     * @param jailDay The jail days to set.
     */
    public void setJailDay(int jailDay) {
        this.jailDay = jailDay;
    }

    @Override
    public String toString() {
        return name;
    }
}
