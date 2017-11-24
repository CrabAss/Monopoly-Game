package Others;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt() {
        int val = -1;
        String invalid;
        try {
            if (scanner.hasNextInt()) val = scanner.nextInt();
            else {invalid = scanner.next(); return -1;}
        }
        catch (Exception e) { return -1; }
        return val;
    }

    public static int getInput(String hint, int lo, int hi) {
        System.out.print(hint);
        int inp = Input.getInt();
        while (!(inp >= lo && inp <= hi)) {
            System.out.println("Invalid Input.");
            System.out.print(hint);
            inp = Input.getInt();
        }
        return inp;
    }
}
