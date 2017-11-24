package Player;

import Others.Dice;
import Others.Property;
import Others.BankruptException;
import Others.Output;
import Land.Land;
import Land.LandStart;

import java.util.ArrayList;
import java.util.List;

abstract public class Player {
    String name;
    int money;
    int status; // 0: Normal; 1:InJail; 2:Dead;
    int jailDay;
    List<Property> propertyList = new ArrayList<>();
    Land position;
    private Dice dice = new Dice();

    public Player(String str, Land startGrid) {
        name = str;
        money = 1500;
        status = jailDay = 0;
        position = startGrid;
    }

    public abstract int getInput(String hint, int limit);

    private void move(long step) throws BankruptException{
        for (int i = 1; i <= step; i++) {
            position = position.getNextLand();
            if (i < step && position instanceof LandStart)
                ((LandStart)position).pass(this);
        }
    }

    public boolean isInJail() { return status == 1; }
    public boolean isDead() { return status == 2; }

    public void run() {
        String jailHint = "0: pay to leave; 1: dice.";

        if (isDead()) return ;
        Output.printTitle(name + "'s Turn");
        Output.println(name + "'s current money: " + money + " HKD.");

        try {
            int step = -1;

            if (isInJail()) {
                jailDay++;
                Output.println(name + " has been stayed in jail for " + jailDay + " turns.");
                if (jailDay <= 3) {
                    Output.print(name + " has to decide paid to release or dice. ");
                    Output.println("(will get release if doubles is thrown)");
                    int inp = getInput(jailHint, 2);
                    if (inp == 0) {
                        Output.println(name + " decides to pay.");
                        decMoney(90);
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
        } catch (BankruptException e) { return ; }
        Output.println(name + "'s turn ends.");
    }

    public void gotoJail(Land JailGrid) {
        Output.println(name + " is sent to jail.");
        status = 1;
        jailDay = 0;
        position = JailGrid;
    }

    public void release() {
        Output.println(name + " is released now.");
        status = 0;
    }

    public void incMoney(int val) {
        Output.println(name + " earns " + val + " HKD.");
        money += val;
        Output.println(name + "'s current money: " + money + " HKD.");
    }

    public void decMoney(int val) throws BankruptException {
        Output.println(name + " pays " + val + " HKD.");
        money -= val;
        Output.println(name + "'s current money: " + money + " HKD.");
        if (money < 0) bankrupt();
    }

    public void addProperty(Property property) {
        propertyList.add(property);
    }

    public void bankrupt() throws BankruptException {
        Output.println(name + "'s current money is less than 0.");
        Output.println(name + " is bankrupted and leaves the game.");
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

    @Override
    public String toString() {
        return name;
    }
}
