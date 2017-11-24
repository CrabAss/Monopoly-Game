package Others;

import java.util.InputMismatchException;
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

    public static int getInput(String hint, int lo, int hi) {
        Output.println(hint);
        int inp = Input.getInt();
        while (!(inp >= lo && inp <= hi)) {
            Output.printlnAndDelay("Invalid Input.");
            Output.println(hint);
            inp = Input.getInt();
        }
        return inp;
    }
    public static int getInput(String hint, int limit) {
        return getInput(hint, 0, limit);
    }
}
