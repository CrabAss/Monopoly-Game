package Cmd.Others;

public class Output {
    private final static int CMD_MAXTITLELENGTH = 40;
    private final static int GUI_MAXTITLELENGTH = 22;

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
        final int MAXTITLELENGTH = GUI.Main.isGUI() ? GUI_MAXTITLELENGTH : CMD_MAXTITLELENGTH;
        int l = (MAXTITLELENGTH - oup.length()) / 2;
        int r = MAXTITLELENGTH - l - oup.length();
        for (int i = 0; i < l; i++) oup = '-' + oup;
        for (int i = 0; i < r; i++) oup = oup + '-';
        oup = "\n" + oup;
        return oup;
    }
}
