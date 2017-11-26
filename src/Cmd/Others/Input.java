package Cmd.Others;

import GUI.Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

//import static java.lang.Thread.sleep;

/**
 * contains the methods for input in the game
 */
public class Input {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Get an integer from cmd.
     * @return The input integer or -1 for invalid input.
     */
    public static int getInt() {
        int val;
        try {
            if (scanner.hasNextInt()) val = scanner.nextInt();
            else { return -1; }
        }
        catch (Exception e) { return -1; }
        return val;
    }

    /**
     * Get the datapath from cmd and return the input stream.
     * @param hint The guidance message to be printed.
     * @return The input stream.
     */
    public static ObjectInputStream getInputStream(String hint) {
        ObjectInputStream ois = null;
        boolean readSuccessfully = false;
        String path;
        while (!readSuccessfully) {
            readSuccessfully = true;
            try {
                if (!GUI.Main.isGUI()) Output.println(hint);
                if (GUI.Main.isGUI()) path = Main.getGame().getLoadPath();
                else path = scanner.next();
                FileInputStream fis = new FileInputStream(path);
                ois = new ObjectInputStream(fis);
            } catch (Exception e) {
                Output.println("Invalid input.");
                readSuccessfully = false;
                if (GUI.Main.isGUI()) {System.out.println("Error"); System.exit(0);}
            }
        }
        return ois;
    }

    /**
     * Get the datapath from cmd and return the output stream.
     * @param hint The guidance message to be printed.
     * @return The output stream.
     */
    public static ObjectOutputStream getOutputStream(String hint) {
        ObjectOutputStream oos = null;
        boolean readSuccessfully = false;
        String path;
        while (!readSuccessfully) {
            readSuccessfully = true;
            try {
                if (!GUI.Main.isGUI()) Output.println(hint);
                if (GUI.Main.isGUI()) path = Main.getGame().getSavePath();
                else path = scanner.next();
                FileOutputStream fos = new FileOutputStream(path);
                oos = new ObjectOutputStream(fos);
            } catch (Exception e) {
                Output.println("Invalid input.");
                readSuccessfully = false;
                if (GUI.Main.isGUI()) {System.out.println("Error"); System.exit(0);}
            }
        }
        return oos;
    }

    /**
     * Get a valid integer from cmd. Won't stop until valid input is received.
     * @param hint The guidance message to be printed.
     * @param lo The lowerbound of the integer.
     * @param hi The upperbound of the integer.
     * @return The valid integer.
     */
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

    /**
     * Get a valid integer from cmd. Won't stop until valid input is received.
     * @param hint The guidance message to be printed.
     * @param limit The upperbound of the integer.
     * @return The valid integer.
     */
    public static int getInput(String hint, int limit) {
        return getInput(hint, 0, limit - 1);
    }

    /**
     * Used for junit test.
     */
    public static void resetScanner() {
        scanner = new Scanner(System.in);
    }
}
