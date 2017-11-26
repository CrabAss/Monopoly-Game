package Cmd.Others;

/**
 * contains the methods for output in the game
 */
public class Output {
    private final static int CMD_MAXTITLELENGTH = 40;
    private final static int GUI_MAXTITLELENGTH = 22;

    /**
     * to print the message
     * delay after print is cancelled
     * @param oup the message to print out
     */
    public static void print(String oup) {
        if (GUI.Main.isGUI()) GUI.Main.getGame().getGuiOutput().Print(oup);
        else {
            try {
                System.out.print(oup);
//                sleep(100);
            } catch (Exception ignored) {}
        }
    }

    /**
     * to println the message
     * delay after print is cancelled
     * @param oup the message to print out
     */
    public static void println(String oup) {
        try {
            if (GUI.Main.isGUI())
                GUI.Main.getGame().getGuiOutput().Print(oup);
            else {
                System.out.println(oup);
                //sleep(100);
            }
        } catch (Exception ignored) {}
    }

    /**
     * to create a decorated string as a title
     * @param oup the title message to be outputed
     * @return the decorated title
     */
    public static String title(String oup) {
        final int MAXTITLELENGTH = GUI.Main.isGUI() ? GUI_MAXTITLELENGTH : CMD_MAXTITLELENGTH;
        StringBuilder str = new StringBuilder(oup);

        int l = (MAXTITLELENGTH - oup.length()) / 2;
        int r = MAXTITLELENGTH - l - oup.length();

        str.insert(0, ' ');
        str.append(' ');
        for (int i = 0; i < l - 1; i++)
            str.insert(0, '-');
        for (int i = 0; i < r - 1; i++)
            str.append('-');
        str.insert(0, '\n');

        return str.toString();
    }
}
