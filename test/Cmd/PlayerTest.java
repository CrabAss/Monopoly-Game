package Cmd;

import Cmd.Game.Game;
import Cmd.Land.LandProperty;
import Cmd.Player.Player;
import Cmd.Player.PlayerUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * test Player
 */
public class PlayerTest {
    private Game game = new Game();
    private Player player;

    /**
     * do before every test
     */
    @Before
    public void before() {
        CmdTest.redirectOutput();
        player = new PlayerUser("Player 0", game.getLandList()[1]);
        player.addProperty(((LandProperty)game.getLandList()[2]).getProperty());
    }

    /**
     * do after every test
     */
    @After
    public void after() {
        CmdTest.resetSystemOut();
        CmdTest.resetSystemIn();
    }

    /**
     * test getInput
     */
    @Test
    public void getInput() {
        String input = "2\n3\n4\n5\n8\n1\n";
        CmdTest.redirectInput(input);
        assert player.getInput("", 2) == 1;
    }

    /**
     * test getPropertyList
     */
    @Test
    public void getPropertyList() {
        assert player.getPropertyList().size() == 1;
    }

    /**
     * test move
     */
    @Test
    public void move() {
        player.move(5);
        assert player.getPosition().getName().equals("Jail");
    }

    /**
     * test isDead
     */
    @Test
    public void isDead() {
        player.retired();
        assert player.isDead();
    }

    /**
     * test run
     */
    @Test
    public void run() {
        player = player.toRobot();
        player.run();
        assert !player.getPosition().equals(game.getLandList()[1]);
    }

    /**
     * test gotoJail
     */
    @Test
    public void gotoJail() {
        player.gotoJail(game.getLandList()[game.getJAILLAND()]);
        assert player.getPosition().equals(game.getLandList()[game.getJAILLAND()]);
        assert player.isInJail();
    }

    /**
     * test release
     */
    @Test
    public void release() {
        player.gotoJail(game.getLandList()[game.getJAILLAND()]);
        player.release();
        assert !player.isInJail();
    }

    /**
     * test incMoney
     */
    @Test
    public void incMoney() {
        final int ADDMONEY = 500;
        final int STARTMONEY = 1500;
        player.incMoney(ADDMONEY);
        assert player.getMoney() == ADDMONEY + STARTMONEY;
    }

    /**
     * test decMoney
     */
    @Test
    public void decMoney() {
        final int DECMONEY1 = 500;
        final int DECMONEY2 = 1000;
        final int STARTMONEY = 1500;
        try {
            player.decMoney(DECMONEY1);
            assert player.getMoney() == STARTMONEY - DECMONEY1;
            player.decMoney(DECMONEY2);
        } catch (Exception ignore) {}
        assert player.isDead();
    }

    /**
     * test retired
     */
    @Test
    public void retired() {
        player.retired();
        assert player.isDead();
    }

    /**
     * test getMoney
     */
    @Test
    public void getMoney() {
        final int STARTMONEY = 1500;
        assert player.getMoney() == STARTMONEY;
    }

    /**
     * test getPosition
     */
    @Test
    public void getPosition() {
        assert player.getPosition().getName().equals("Start");
    }

    /**
     * test getName
     */
    @Test
    public void getName() {
        assert player.getName().equals("Player 0");
    }

    /**
     * test getJailDay & setJailDay
     */
    @Test
    public void getSetJailDay() {
        player.gotoJail(game.getLandList()[game.getJAILLAND()]);
        player.setJailDay(2);
        assert player.getJailDay() == 2;
    }

    /**
     * test ToString
     */
    @Test
    public void testToString() {
        assert player.toString().equals("Player 0");
    }

}