package Cmd.Others;

import static java.lang.Thread.sleep;

public class Output {
    private final static int MAXTITLELENGTH = 40;

    public static void print(String oup) {
        if (GUI.Main.isGUI()) GUI.Main.getGame().getGuiOutput().Print(oup);
        else {
            try {
                System.out.print(oup);
//                sleep(100);
            } catch (Exception ignored) {}
        }
    }

    public static void println(String oup) {
        if (GUI.Main.isGUI()) GUI.Main.getGame().getGuiOutput().Print(oup);
        else {
            try {
                System.out.println(oup);
//                sleep(100);
            } catch (Exception ignored) {}
        }
    }

    public static String title(String oup) {
        int l = (MAXTITLELENGTH - oup.length()) / 2;
        int r = MAXTITLELENGTH - l - oup.length();
        for (int i = 0; i < l; i++) oup = '-' + oup;
        for (int i = 0; i < r; i++) oup = oup + '-';
        oup = "\n" + oup;
        return oup;
    }
}
