package Cmd;

import Cmd.CmdTest;
import Cmd.Land.Land;
import Cmd.Land.LandOrdinary;
import Cmd.Player.Player;
import Cmd.Player.PlayerAI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Land
 */
public class LandTest {

    private Land land, nextLand;
    private Player player;

    /**
     * do before every test
     */
    @Before
    public void before() {
        CmdTest.redirectOutput();
        land = new LandOrdinary("Ordinary", 8);
        nextLand = new LandOrdinary("Ordinary", 9);
        land.setNextLand(nextLand);
        player = new PlayerAI("Player 1", land);
    }

    /**
     * do after every test
     */
    @After
    public void after() {
        CmdTest.resetSystemOut();
    }

    /**
     * test landOn
     */
    @Test
    public void landOn() {
        land.landOn(player);
        assert CmdTest.searchOutput("Player 1 reaches Ordinary Area (Grid 8).");
    }

    /**
     * test getName
     */
    @Test
    public void getName() {
        assert land.getName().equals("Ordinary");
    }

    /**
     * test getNextLand and setNextLand
     */
    @Test
    public void getSetNextLand() {
        assert land.getNextLand().equals(nextLand);
        Land newLand = new LandOrdinary("New", 9);
        land.setNextLand(newLand);
        assert land.getNextLand().equals(newLand);
    }

    /**
     * test toString
     */
    @Test
    public void testToString() {
        assert land.toString().equals("Ordinary Area (Grid 8)");
    }
}