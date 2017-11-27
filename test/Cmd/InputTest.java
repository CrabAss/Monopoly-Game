package Cmd;

import Cmd.Others.Input;
import org.junit.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Test Input
 */
public class InputTest {
    private Input input = new Input();

    /**
     * do before every test
     */
    @Before
    public void before() {
        CmdTest.redirectOutput();
    }

    /**
     * do after every test
     */
    @After
    public void after() {
        CmdTest.resetSystemIn();
        CmdTest.resetSystemOut();
    }

    /**
     * Test getInt
     */
    @Test
    public void getInt() {
        String input = "invalid\n5\n";
        CmdTest.redirectInput(input);
        Assert.assertEquals(Input.getInt(), -1);
        Assert.assertEquals(Input.getInt(), 5);
    }


    /**
     * Test getInputStream and getOutputStream
     */
    @Test
    public void getInputOutputStream() {
        String input = "test1.txt\ntest-1.txt\ntest1.txt\n";
        CmdTest.redirectInput(input);
        try {
            ObjectOutputStream oos = Input.getOutputStream("");
            oos.writeObject("Testing.");
            ObjectInputStream ois = Input.getInputStream("");
            Assert.assertEquals(ois.readObject(), "Testing.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test getInput
     */
    @Test
    public void getInput() {
        String input = "5\n7\n2\n3\n";
        CmdTest.redirectInput(input);
        Assert.assertEquals(Input.getInput("", 3, 4), 3);
        CmdTest.redirectInput(input);
        Assert.assertEquals(Input.getInput("", 4), 2);
    }
}