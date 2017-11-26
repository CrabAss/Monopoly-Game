package Cmd;

import Cmd.Others.Input;
import Cmd.Others.Output;
import com.sun.xml.internal.fastinfoset.tools.FI_SAX_Or_XML_SAX_DOM_SAX_SAXEvent;

import java.io.*;

public class CmdTest {
    private static final InputStream stdin = System.in;
    private static final PrintStream stdout = System.out;
    private static final String path = "text.txt";

    public static void redirectInput(String input) {
        resetSystemIn();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Input.resetScanner();
    }

    public static void resetSystemIn() {
        System.setIn(stdin);
        Input.resetScanner();
    }

    public static void redirectOutput() {
        try {
            System.setOut(new PrintStream(new FileOutputStream(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetSystemOut() {
        System.setOut(stdout);
    }

    public static boolean searchOutput(String target) {
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
