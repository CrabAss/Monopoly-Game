package Cmd.Player;

import Cmd.Others.Dice;
import Cmd.Others.Property;
import Cmd.Others.BankruptException;
import Cmd.Others.Output;
import Cmd.Land.Land;
import Cmd.Land.LandStart;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    String name;
    int money;
    public int status; // 0: Normal; 1:InJail; 2:Dead;
    int jailDay;
    List<Property> propertyList = new ArrayList<>();
    Land position;
    private Dice dice = new Dice();

    public int getJailDay() {
        return jailDay;
    }

    public void setJailDay(int jailDay) {
        this.jailDay = jailDay;
    }

    public Player(String str, Land startGrid) {
        name = str;
        money = 1500;
        status = jailDay = 0;
        position = startGrid;
    }

    public Player(String name, int money, int status, int jailDay, List<Property> propertyList, Land position) {
        this(name, position);
        this.money = money;
        this.status = status;
        this.jailDay = jailDay;
        this.propertyList = propertyList;
    }

    public abstract int getInput(String hint, int limit);

    private void move(int step) {
        for (int i = 1; i <= step; i++) {
            position = position.getNextLand();
            if (i < step && position instanceof LandStart)
                ((LandStart) position).pass(this);
        }
    }

    public boolean isInJail() {
        return status == 1;
    }

    public boolean isDead() {
        return status == 2;
    }

    public void run() {
        String jailHint = "0: pay to leave; 1: dice.";

        if (isDead()) return;
        Output.printlnAndDelay(Output.title(name + "'s Turn"));
        Output.printlnAndDelay(name + "'s current money: " + money + " HKD.");

        try {
            int step = -1;

            if (isInJail()) {
                jailDay++;
                Output.printlnAndDelay(name + " has been stayed in jail for " + jailDay + " turns.");
                if (jailDay <= 3) {
                    Output.print(name + " has to decide paid to release or dice. ");
                    Output.printlnAndDelay("(will get release if doubles is thrown)");
                    int inp = getInput(jailHint, 2);

                    if (inp == 0) {
                        Output.printlnAndDelay(name + " decides to pay.");
                        decMoney(90);
                        release();
                    } else {
                        Output.printlnAndDelay(name + " decides to dice.");
                        dice.dice(this);
                        if (dice.isEqual()) {
                            release();
                            step = dice.getStep();
                        }
                    }
                } else {
                    Output.printlnAndDelay(name + " must pay to release.");
                    decMoney(90);
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
        Output.printlnAndDelay(name + "'s turn ends.");
    }

    public void gotoJail(Land JailGrid) {
        Output.printlnAndDelay(name + " is sent to jail.");
        status = 1;
        jailDay = 0;
        position = JailGrid;
    }

    public void release() {
        Output.printlnAndDelay(name + " is released now.");
        status = 0;
    }

    public void incMoney(int val) {
        Output.printlnAndDelay(name + " earns " + val + " HKD.");
        money += val;
        Output.printlnAndDelay(name + "'s current money: " + money + " HKD.");
    }

    public void decMoney(int val) throws BankruptException {
        Output.printlnAndDelay(name + " pays " + val + " HKD.");
        money -= val;
        Output.printlnAndDelay(name + "'s current money: " + money + " HKD.");
        if (money < 0) bankrupt();
    }

    public void addProperty(Property property) {
        propertyList.add(property);
    }

    public void bankrupt() throws BankruptException {
        Output.printlnAndDelay(name + "'s current money is less than 0.");
        Output.printlnAndDelay(name + " is bankrupted and leaves the game.");
        status = 2;
        for (Property x : propertyList)
            x.setBelongs(null);
    }

    public void retired() {
        status = 2;
        for (Property x : propertyList)
            x.setBelongs(null);
    }

    public int getMoney() {
        return money;
    }

    public Land getPosition() {
        return position;
    }

    public void setPosition(Land position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name;
    }
}
