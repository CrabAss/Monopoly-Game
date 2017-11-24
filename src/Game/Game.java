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

    private final String landName[] = {"",
            "Start", "Central", "Wan chai", "Tax Paid", "Stanley",
            "Jail", "Shek O", "Mong Kok", "Chance", "Tsing Yi",
            "Free Parking", "Shatin", "Chance", "Tuen Mun", "Tai Po",
            "Go to Jail", "Sai Kung", "Yuen Long", "Chance", "Tai O"
    };
    private final int landPrice[] = {0,
            0, 850, 750, 0, 650,
            0, 350, 550, 0, 450,
            0, 650, 0, 350, 550,
            0, 400, 450, 0, 650
    };
    private final int landRent[] = {0,
            0, 90, 70, 0, 65,
            0, 15, 35, 0, 20,
            0, 70, 0, 25, 20,
            0, 15, 25, 0, 30
    };

    public Game() {
        initLand();
        initGame();
    }

    private void initLand() {
        for (int i = 1; i <= MAX_LAND_NUMBER; i++)
            switch (landName[i]){
                case "Start":
                    landList[i] = new LandStart(landName[i]);
                    break;
                case "Tax Paid":
                    landList[i] = new LandTax(landName[i]);
                    break;
                case "Jail":
                    landList[i] = new Land(landName[i]);
                    break;
                case "Chance":
                    landList[i] = new LandChance(landName[i]);
                    break;
                case "Free Parking":
                    landList[i] = new Land(landName[i]);
                    break;
                case "Go to Jail":
                    landList[i] = new LandGotoJail(landName[i], landList[6]);;
                    break;
                default:
                    landList[i] = new LandProperty(new Property(landName[i], landPrice[i], landRent[i]));
                    break;
            }
        for (int i = 1; i < MAX_LAND_NUMBER; i++)
            landList[i].setNextLand(landList[i + 1]);
        landList[MAX_LAND_NUMBER].setNextLand(landList[1]);
    }

    private void initGame() {
        String hint1 = "Please input the number of players (2-6):";
        int inp = Input.getInput(hint1, 2, 7);

        playerList = new Player[inp];
        for (int i = 1; i <= playerList.length; i++) {
            String hint2 = "Please decide the identity of Player " + i + " (0: human; 1: AI):";
            inp = Input.getInput(hint2, 0, 2);
            if (inp == 0)
                playerList[i - 1] = new PlayerUser("Player " + i, landList[1]);
            else
                playerList[i - 1] = new PlayerAI("Player " + i, landList[1]);
        }

        rounds = 0;
        playerAlive = playerList.length;
    }

    public void runGame() {
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
                        int inp = Input.getInput(hint, 0, 6);
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
}
