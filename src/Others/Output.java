package Others;

import static java.lang.Thread.sleep;

public class Output {
    private final static int MAXTITLELENGTH = 40;
    public static void println(String oup) {
        System.out.println(oup);
        try {
            sleep(100);
        } catch (Exception e) {}
    }

    public static void print(String oup) {
        System.out.print(oup);
    }

    public static void printTitle(String oup) {
        int l = (MAXTITLELENGTH - oup.length()) / 2;
        int r = MAXTITLELENGTH - l - oup.length();
        for (int i = 0; i < l; i++) oup = '-' + oup;
        for (int i = 0; i < r; i++) oup = oup + '-';
        println(oup);
    }
}
