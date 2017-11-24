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
}
