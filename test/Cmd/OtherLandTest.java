package Cmd;

import Cmd.Game.Game;
import Cmd.Land.*;
import Cmd.Player.Player;
import Cmd.Player.PlayerAI;
import Cmd.Player.PlayerUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test other subclasses of Land.
 */
public class OtherLandTest {
    private Game game = new Game();
    private Player player;

    /**
     * do before every test
     */
    @Before
    public void before() {
        player = new PlayerUser("Player 0", game.getLandList()[1]);
        CmdTest.redirectOutput();
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
     * Test LandChance
     */
    @Test
    public void LandChance() {
        final int GRID = 9;
        final int MINIMUMMONEY = 1200;
        final int MAXIMUMMONEY = 1700;
        LandChance land = (LandChance) game.getLandList()[GRID];
        try {
            land.run(player);
        } catch (Exception ignore) {}
        Assert.assertTrue(player.getMoney() >= MINIMUMMONEY);
        Assert.assertTrue(player.getMoney() <= MAXIMUMMONEY);
    }

    /**
     * Test LandGotoJail
     */
    @Test
    public void LandGotoJail() {
        final int GRID = 16;
        LandGotoJail land = (LandGotoJail) game.getLandList()[GRID];
        land.run(player);
        Assert.assertTrue(player.isInJail());
        Assert.assertTrue(player.getPosition().getName().equals("Jail"));
    }

    /**
     * Test LandOrdinary
     */
    @Test
    public void LandOrdinary() {
        final int GRID = 11;
        LandOrdinary land = (LandOrdinary) game.getLandList()[GRID];
        land.run(player);
        Assert.assertTrue(CmdTest.searchOutput("Nothing happens."));
    }

    /**
     * Test LandStart
     */
    @Test
    public void LandStart() {
        final int GRID = 1, LASTGRID = 20;
        final int PASSMONEY = 3000, REACHMONEY = 1500;
        LandStart land = (LandStart) game.getLandList()[GRID];
        land.run(player);
        Assert.assertTrue(CmdTest.searchOutput("Nothing happens."));
        Assert.assertEquals(player.getMoney(), REACHMONEY);
        player = new PlayerAI("Player 0", game.getLandList()[LASTGRID]);
        player.move(2);
        Assert.assertEquals(player.getMoney(), PASSMONEY);
    }


    /**
     * Test LandProperty
     */
    @Test
    public void LandProperty() {
        String input = "1\n";
        CmdTest.redirectInput(input);

        final int GRID = 2;
        final int BUYMONEY = 650;
        LandProperty land = (LandProperty) game.getLandList()[GRID];
        try {
            land.run(player);
        } catch (Exception ignore) {}
        Assert.assertEquals(player.getMoney(), BUYMONEY);
    }
}