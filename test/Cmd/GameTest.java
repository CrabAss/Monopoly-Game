package Cmd;

import Cmd.Game.Game;
import Cmd.Player.PlayerAI;
import Cmd.Player.PlayerUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Test Game
 */
public class GameTest {
    private Game game;

    /**
     * do before every test
     */
    @Before
    public void before() {
        String input = "2\n1\n0";
        CmdTest.redirectInput(input);
        CmdTest.redirectOutput();
        game = new Game();
        game.initGame();
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
     * test getPlayerAlive
     */
    @Test
    public void getPlayerAlive() {
        CmdTest.resetSystemOut();
        Assert.assertEquals(game.getPlayerAlive(), 2);
        game.getPlayerList()[0].retired();
        Assert.assertEquals(game.getPlayerAlive(), 1);
    }

    /**
     * test getPlayerNumber and setPlayerNumber
     */
    @Test
    public void getPlayerNumber() {
        Assert.assertEquals(game.getPlayerNumber(), 2);
        game.setPlayerNumber(6);
        Assert.assertEquals(game.getPlayerNumber(), 6);
    }

    /**
     * test getMAXLANDNUMBER
     */
    @Test
    public void getMAXLANDNUMBER() {
        final int CORRECT = 20;
        Assert.assertEquals(game.getMAXLANDNUMBER(), CORRECT);
    }

    /**
     * test getMAXPLAYERNUMBER
     */
    @Test
    public void getMAXPLAYERNUMBER() {
        Assert.assertEquals(game.getMAXPLAYERNUMBER(), 6);
    }

    /**
     * test getJAILLAND
     */
    @Test
    public void getJAILLAND() {
        Assert.assertEquals(game.getLandList()[game.getJAILLAND()].getName(), "Jail");
    }

    /**
     * test getSTARTLAND
     */
    @Test
    public void getSTARTLAND() {
        Assert.assertEquals(game.getLandList()[game.getSTARTLAND()].getName(), "Start");
    }


    /**
     * test setPlayerAlive
     */
    @Test
    public void setPlayerAlive() {
        game.setPlayerAlive(2);
        Assert.assertEquals(game.getPlayerAlive(), 2);
    }

    /**
     * test getCurrentPlayer and setCurrentPlayer
     */
    @Test
    public void getSetCurrentPlayer() {
        Assert.assertEquals(game.getCurrentPlayer(), 0);
        game.setCurrentPlayer(1);
        Assert.assertEquals(game.getCurrentPlayer(), 1);
    }

    /**
     * test getSetRounds
     */
    @Test
    public void getSetRounds() {
        Assert.assertEquals(game.getRounds(), 1);
        game.setRounds(5);
        Assert.assertEquals(game.getRounds(), 5);
    }

    /**
     * test initGame
     */
    @Test
    public void initGame() {
        String s1 = "Please input the number of players (2-6):";
        String s2 = "Please decide the type of Player 1 (0: human; 1: AI):";
        Assert.assertTrue(game.getPlayerList()[0] instanceof PlayerAI);
        Assert.assertTrue(game.getPlayerList()[1] instanceof PlayerUser);
        Assert.assertTrue(CmdTest.searchOutput(s1));
        Assert.assertTrue(CmdTest.searchOutput(s2));
    }

    /**
     * test LoadGame and SaveGame
     */
    @Test
    public void saveLoadGame() {
        String input = "test0.txt\ntest0.txt\n";
        CmdTest.redirectInput(input);
        game.saveGame();
        Assert.assertTrue(CmdTest.searchOutput("Save successfully."));
        CmdTest.redirectOutput();
        game.loadGame();
        Assert.assertTrue(CmdTest.searchOutput("Load successfully."));
    }

    /**
     * test runGame
     */
    @Test
    public void runGame() {
        String input = "6\n0\n0\n1\n1\n1\n1\n2\n3\n";
        CmdTest.redirectInput(input);
        game.initGame();
        game.runGame();
        try {
            FileOutputStream fos = new FileOutputStream("test2.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject("Invalid.");
        } catch (Exception ignore) {}
        input = "2\n0\n1\n1\n4\ntest0.txt\n5\ntest2.txt\ntest1.txt\ntest0.txt\n3\n";
        CmdTest.redirectInput(input);
        game.initGame();
        game.runGame();
        Assert.assertEquals(game.getPlayerAlive(), 1);
    }
}