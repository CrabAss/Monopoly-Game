package Others;

import static java.lang.Thread.sleep;

public class Output {
    public static void println(String oup) {
        System.out.println(oup);
        try {
            sleep(500);
        } catch (Exception e) {}
    }

    public static void print(String oup) {
        System.out.print(oup);
    }

    public static void printTitle(String oup) {
        int l = (40 - oup.length()) / 2;
        int r = 40 - l - oup.length();
        for (int i = 0; i < l; i++) oup = '-' + oup;
        for (int i = 0; i < r; i++) oup = oup + '-';
        println(oup);
    }
}
