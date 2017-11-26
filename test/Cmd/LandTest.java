package Cmd;

import Cmd.Land.Land;
import Cmd.Land.LandOrdinary;
import Cmd.Player.Player;
import Cmd.Player.PlayerAI;
import org.junit.After;
import org.junit.Assert;
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
        Assert.assertTrue(CmdTest.searchOutput("Player 1 reaches Ordinary Area (Grid 8)."));
    }

    /**
     * test getName
     */
    @Test
    public void getName() {
        Assert.assertEquals(land.getName(), "Ordinary");
    }

    /**
     * test getNextLand and setNextLand
     */
    @Test
    public void getSetNextLand() {
        Assert.assertEquals(land.getNextLand(), nextLand);
        Land newLand = new LandOrdinary("New", 9);
        land.setNextLand(newLand);
        Assert.assertEquals(land.getNextLand(), newLand);
    }

    /**
     * test toString
     */
    @Test
    public void testToString() {
        Assert.assertEquals(land.toString(), "Ordinary Area (Grid 8)");
    }
}