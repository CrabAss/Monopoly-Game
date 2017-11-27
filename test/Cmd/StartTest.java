package Cmd;

import org.junit.Test;

/**
 * test Start
 */
public class StartTest {
    private Start start = new Start();

    /**
     * test start
     */
    @Test
    public void main() {
        String input = "1\n2\n0\n0\n4\ntest0.txt\n3\n2\ntest0.txt\n3\n3\n";
        CmdTest.redirectInput(input);
        CmdTest.redirectOutput();
        Start.main(new String[0]);
    }

}