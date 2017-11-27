package Cmd;

import Cmd.Others.Output;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Output
 */
public class OutputTest {
    private Output output = new Output();

    /**
     * Test println
     */
    @Test
    public void println() {
        CmdTest.redirectOutput();
        Output.print("Test");
        Output.println("Test");
        Assert.assertTrue(CmdTest.searchOutput("TestTest"));
        CmdTest.resetSystemOut();
    }

    /**
     * Test title
     */
    @Test
    public void title() {
        String input = "input";
        final int LENGTH = 41;
        Assert.assertEquals(Output.title(input).length(), LENGTH);
        Assert.assertTrue(Output.title(input).contains(input));
    }

}