package Others;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt() {
        int val = -1;
        try {
            val = scanner.nextInt();
        }
        catch (Exception e) { return -1; }
        return val;
    }
    public static int getInput(String hint, int lo, int hi) {
        System.out.println(hint);
        int inp = Input.getInt();
        while (!(inp >= lo && inp < hi)) {
            System.out.println("Invalid Input.");
            System.out.println(hint);
            inp = Input.getInt();
        }
        return inp;
    }
}
