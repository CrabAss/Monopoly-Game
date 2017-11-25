package Cmd.Others;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * contains the methods for input in the game
 */
public class Input {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @return an integer from cmd, returns -1 if input is invalid.
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
     * Get a valid input stream.
     * @param hint the guidance message.
     * @return the input stream.
     */
    public static ObjectInputStream getInputStream(String hint) {
        ObjectInputStream ois = null;
        boolean inputSucess = false;
        String path;
        while (!inputSucess) {
            inputSucess = true;
            try {
                Output.println(hint);
                path = scanner.next();
                FileInputStream fis = new FileInputStream(path);
                ois = new ObjectInputStream(fis);
            } catch (Exception e) {
                Output.println("Invalid input.");
                inputSucess = false;
            }
        }
        return ois;
    }

    /**
     * Get a valid output stream.
     * @param hint the guidance message.
     * @return the output stream.
     */
    public static ObjectOutputStream getOutputStream(String hint) {
        ObjectOutputStream oos = null;
        boolean inputSucess = false;
        String path;
        while (!inputSucess) {
            inputSucess = true;
            try {
                Output.println(hint);
                path = scanner.next();
                FileOutputStream fos = new FileOutputStream(path);
                oos = new ObjectOutputStream(fos);
            } catch (Exception e) {
                Output.println("Invalid input.");
                inputSucess = false;
            }
        }
        return oos;
    }

    /**
     * Get a valid integer from cmd.
     * @param hint The guidance message.
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
     * Get a valid integer from cmd.
     * @param hint The guidance message.
     * @param limit The upperbound of the integer.
     * @return The valid integer.
     */
    public static int getInput(String hint, int limit) {
        return getInput(hint, 0, limit);
    }
}
