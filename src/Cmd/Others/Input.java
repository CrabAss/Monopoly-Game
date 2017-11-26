package Cmd.Others;

import GUI.Main;
import com.sun.xml.internal.fastinfoset.tools.FI_SAX_Or_XML_SAX_DOM_SAX_SAXEvent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt() {
        int val = -1;
        try {
            if (scanner.hasNextInt()) val = scanner.nextInt();
            else { return -1; }
        }
        catch (Exception e) { return -1; }
        return val;
    }
    public static ObjectInputStream getInputStream(String hint) {
        ObjectInputStream ois = null;
        String path;
        while (ois == null) {
            try {
                if (!GUI.Main.isGUI()) Output.println(hint);
                if (GUI.Main.isGUI()) path = Main.getGame().getLoadPath();
                else path = scanner.next();
                FileInputStream fis = new FileInputStream(path);
                ois = new ObjectInputStream(fis);
            } catch (Exception e) {
                Output.println("Invalid input.");
                ois = null;
            }
        }
        return ois;
    }

    public static ObjectOutputStream getOutputStream(String hint) {
        ObjectOutputStream oos = null;
        String path;
        while (oos == null) {
            try {
                if (!GUI.Main.isGUI()) Output.println(hint);
                if (GUI.Main.isGUI()) path = Main.getGame().getSavePath();
                else path = scanner.next();
                FileOutputStream fos = new FileOutputStream(path);
                oos = new ObjectOutputStream(fos);
            } catch (Exception e) {
                Output.println("Invalid input.");
                oos = null;
            }
        }
        return oos;
    }

    public static int getInput(String hint, int lo, int hi) {
        Output.println(hint);
        int inp = Input.getInt();
        while (!(inp >= lo && inp <= hi)) {
            Output.println("Invalid Input.");
            Output.println(hint);
            inp = Input.getInt();
        }
        return inp;
    }
    public static int getInput(String hint, int limit) {
        return getInput(hint, 0, limit);
    }
}
