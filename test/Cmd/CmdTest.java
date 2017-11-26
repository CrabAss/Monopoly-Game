package Cmd;

import Cmd.Others.Input;
import java.io.*;

/**
 * functions for I/O stream redirecting
 */
class CmdTest {
    private static final InputStream stdin = System.in;
    private static final PrintStream stdout = System.out;
    private static final String path = "text.txt";

    /**
     * To redirect the input stream from user's keyboard to a string variable.
     * @param input The string to input.
     */
    static void redirectInput(String input) {
        resetSystemIn();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Input.resetScanner();
    }

    /**
     * To reset the input stream to user's keyboard.
     */
    static void resetSystemIn() {
        System.setIn(stdin);
        Input.resetScanner();
    }

    /**
     * To redirect the output stream from screen to a file.
     */
    static void redirectOutput() {
        try {
            System.setOut(new PrintStream(new FileOutputStream(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To reset the output stream to screen.
     */
    static void resetSystemOut() {
        System.setOut(stdout);
    }

    /**
     * To search a specific target keyword in the output
     * @param target The target keyword to search in the output.
     * @return whether the target keyword exists in the output or not
     */
    static boolean searchOutput(String target) {
        try {
            FileReader fis = new FileReader(path);
            BufferedReader bis = new BufferedReader(fis);
            while (true) {
                String output = bis.readLine();
                if (output.contains(target))
                    return true;
            }
        } catch (Exception e) {
            if (!(e instanceof IOException))
                e.printStackTrace();
            return false;
        }
    }
}
