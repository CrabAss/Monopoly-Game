package Cmd;

import Cmd.Game.Game;
import Cmd.Player.PlayerAI;
import Cmd.Player.PlayerUser;
import org.junit.*;

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
        assert game.getPlayerAlive() == 2;
        game.getPlayerList()[0].retired();
        assert game.getPlayerAlive() == 1;
    }

    /**
     * test getPlayerNumber
     */
    @Test
    public void getPlayerNumber() {
        assert game.getPlayerNumber() == 2;
    }

    /**
     * test getMAXLANDNUMBER
     */
    @Test
    public void getMAXLANDNUMBER() {
        final int CORRECT = 20;
        assert game.getMAXLANDNUMBER() == CORRECT;
    }

    /**
     * test getMAXPLAYERNUMBER
     */
    @Test
    public void getMAXPLAYERNUMBER() {
        assert game.getMAXPLAYERNUMBER() == 6;
    }

    /**
     * test getJAILLAND
     */
    @Test
    public void getJAILLAND() {
        assert game.getLandList()[game.getJAILLAND()].getName().equals("Jail");
    }

    /**
     * test getSTARTLAND
     */
    @Test
    public void getSTARTLAND() {
        assert game.getLandList()[game.getSTARTLAND()].getName().equals("Start");
    }


    /**
     * test setPlayerAlive
     */
    @Test
    public void setPlayerAlive() {
        game.setPlayerAlive(2);
        assert game.getPlayerAlive() == 2;
    }

    /**
     * test getCurrentPlayer and setCurrentPlayer
     */
    @Test
    public void getSetCurrentPlayer() {
        assert game.getCurrentPlayer() == 0;
        game.setCurrentPlayer(1);
        assert game.getCurrentPlayer() == 1;
    }

    /**
     * test getSetRounds
     */
    @Test
    public void getSetRounds() {
        assert game.getRounds() == 1;
        game.setRounds(5);
        assert game.getRounds() == 5;
    }

    /**
     * test initGame
     */
    @Test
    public void initGame() {
        String s1 = "Please input the number of players (2-6):";
        String s2 = "Please decide the type of Player 1 (0: human; 1: AI):";
        assert game.getPlayerList()[0] instanceof PlayerAI;
        assert game.getPlayerList()[1] instanceof PlayerUser;
        assert CmdTest.searchOutput(s1);
        assert CmdTest.searchOutput(s2);
    }

    /**
     * test LoadGame and SaveGame
     */
    @Test
    public void saveLoadGame() {
        String input = "text0.txt\ntext0.txt\n";
        CmdTest.redirectInput(input);
        game.saveGame();
        assert CmdTest.searchOutput("Save successfully.");
        CmdTest.redirectOutput();
        game.loadGame();
        assert CmdTest.searchOutput("Load successfully.");
    }

    /**
     * test runGame
     */
    @Test
    public void runGame() {
        String input = "6\n0\n1\n1\n1\n1\n1\n2\n";
        CmdTest.redirectInput(input);
        game.runGame();
        input = "2\n0\n1\n1\n4\ntext0.txt\n5\ntext1.txt\ntext0.txt\n3\n";
        CmdTest.redirectInput(input);
        game.initGame();
        game.runGame();
        assert game.getPlayerAlive() == 1;
    }
}