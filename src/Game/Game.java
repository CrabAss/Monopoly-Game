package Game;

import Land.*;
import Others.Input;
import Others.Property;
import Others.Output;
import Player.*;

public class Game {
    private final int MAX_LAND_NUMBER = 20;
    private final int MAX_PLAYER_NUMBER = 6;
    private Land landList[] = new Land[MAX_LAND_NUMBER + 1];
    private Player playerList[] = new Player[MAX_PLAYER_NUMBER];
    private int playerAlive;
    private int rounds;

    public Game() {
        BuildLandList();
        initGame();
    }

    void initGame() {
        BuildLandList();

        String hint1 = "Please input the number of players (2-6):";
        int inp = getInput(hint1, 2, 7);
        playerList = new Player[inp];
        for (int i = 1; i <= playerList.length; i++) {
            String hint2 = "Please decide the identity of Player " + i + " (0: human; 1: AI):";
            inp = getInput(hint2, 0, 2);
            if (inp == 0)
                playerList[i - 1] = new PlayerUser("Player " + i, landList[1]);
            else
                playerList[i - 1] = new PlayerAI("Player " + i, landList[1]);
        }

        rounds = 0;
        playerAlive = playerList.length;
    }

    public void newGame() {
        String hint = "0: continue; 1: report; 2: auto; 3: retire; 4: save; 5: load.";
        while (++rounds <= 100) {
            for (int i = 0; i < playerList.length; i++) {
                Player player = playerList[i];
                if (playerAlive == 1) break;
                if (player.isDead())
                    continue;
                if (player instanceof PlayerUser) {
                    while (true) {
                        Output.printTitle("Game Option");
                        int inp = getInput(hint, 0, 6);
                        switch (inp) {
                            case 0:
                                break;
                            case 1:
                                report();
                                break;
                            case 2:
                                player = playerList[i] = ((PlayerUser) player).toRobot();
                                break;
                            case 3:
                                player.retired();
                                break;
                            case 4:
                                saveGame();
                                break;
                            case 5:
                                loadGame();
                                break;
                        }
                        if (inp == 0 || inp == 2 || inp == 3) break;
                    }
                }
                player.run();
                if (player.isDead())
                    playerAlive--;
            }
            if (playerAlive == 1) break;
        }

        Output.printTitle("Game terminated");
        int maxvalue = 0;
        for (Player player : playerList)
            if (!player.isDead() && player.getMoney() > maxvalue)
                maxvalue = player.getMoney();

        Output.println("Winner:");
        for (Player player : playerList)
            if (!player.isDead() && player.getMoney() == maxvalue)
                Output.println(player.toString());
    }

    public void saveGame() {}

    public void loadGame() {}

    void report() {
        Output.printTitle("Players' Location");
        for (Player player : playerList)
            if (!player.isDead()) {
                Output.println(player + " : " + player.getPosition());
            }
    }

    public int getInput(String hint, int lo, int hi) {
        System.out.println(hint);
        int inp = Input.getInt();
        while (!(inp >= lo && inp < hi)) {
            System.out.println("Invalid Input.");
            System.out.println(hint);
            inp = Input.getInt();
        }
        return inp;
    }

    private void BuildLandList() {
        landList = new Land[21];
        landList[1] = new LandStart("Start");
        landList[2] = new LandProperty(new Property("Central", 850, 90));
        landList[3] = new LandProperty(new Property("Wan Chai", 750, 70));
        landList[4] = new LandTax("Tax Paid");
        landList[5] = new LandProperty(new Property("Stanley", 650, 65));
        landList[6] = new Land("Jail");
        landList[7] = new LandProperty(new Property("Shek O", 350, 15));
        landList[8] = new LandProperty(new Property("Mong Kok", 550, 35));
        landList[9] = new LandChance("Chance");
        landList[10] = new LandProperty(new Property("Tsing Yi", 450, 20));
        landList[11] = new Land("Free Parking");
        landList[12] = new LandProperty(new Property("Shatin", 650, 70));
        landList[13] = new LandChance("Chance");
        landList[14] = new LandProperty(new Property("Tuen Mun", 350, 25));
        landList[15] = new LandProperty(new Property("Tai Po", 550, 20));
        landList[16] = new LandGotoJail("Go to Jail", landList[6]);
        landList[17] = new LandProperty(new Property("Sai Kung", 400, 15));
        landList[18] = new LandProperty(new Property("Yuen Long", 450, 25));
        landList[19] = new LandChance("Chance");
        landList[20] = new LandProperty(new Property("Tai O", 650, 30));
        for (int i = 1; i < 20; i++)
            landList[i].setNextLand(landList[i + 1]);
        landList[20].setNextLand(landList[1]);
    }
}
