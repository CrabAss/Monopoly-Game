package Game;

import Land.*;
import Others.Input;
import Others.Property;
import Others.Output;
import Player.*;

public class Game {
    private final int MAXLANDNUMBER = 20;
    private final int MAXPLAYERNUMBER = 6;
    private final int STARTLAND = 1;
    private final int JAILLAND = 1;
    private Land landList[] = new Land[MAXLANDNUMBER + 1];
    private Player playerList[] = new Player[MAXPLAYERNUMBER + 1];
    private int playerAlive, playerNumber;
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
    }

    private void initLand() {
        for (int i = 1; i <= MAXLANDNUMBER; i++)
            switch (landName[i]){
                case "Start":
                    landList[i] = new LandStart(landName[i], i);
                    break;
                case "Tax Paid":
                    landList[i] = new LandTax(landName[i], i);
                    break;
                case "Jail":
                    landList[i] = new LandOrdinary(landName[i], i);
                    break;
                case "Chance":
                    landList[i] = new LandChance(landName[i], i);
                    break;
                case "Free Parking":
                    landList[i] = new LandOrdinary(landName[i], i);
                    break;
                case "Go to Jail":
                    landList[i] = new LandGotoJail(landName[i], i, landList[JAILLAND]);;
                    break;
                default:
                    landList[i] = new LandProperty(new Property(landName[i], landPrice[i], landRent[i]), i);
                    break;
            }
        for (int i = 1; i < MAXLANDNUMBER; i++)
            landList[i].setNextLand(landList[i + 1]);
        landList[MAXLANDNUMBER].setNextLand(landList[1]);
    }

    private void initGame() {
        String hint1 = "Please input the number of players (2-6):";
        playerNumber = Input.getInput(hint1, 2, 6);

        for (int i = 1; i <= playerNumber; i++) {
            String hint2 = "Please decide the type of Player " + i + " (0: human; 1: AI):";
            int type = Input.getInput(hint2, 0, 1);

            if (type == 0)
                playerList[i - 1] = new PlayerUser("Player " + i, landList[STARTLAND]);
            else
                playerList[i - 1] = new PlayerAI("Player " + i, landList[STARTLAND]);
        }

        rounds = 0;
        playerAlive = playerNumber;
    }

    public void newGame(){
        initGame();
        runGame();
    }

    public void saveGame() {}

    public void loadGame() {}

    void report() {
        Output.printlnAndDelay(Output.title("Players' Location"));
        for (Player player : playerList)
            if (player != null && !player.isDead()) {
                Output.printlnAndDelay(player + " : " + player.getPosition());
            }
    }

    public void runGame() {
        String hint = "0: continue; 1: report; 2: auto; 3: retire; 4: save; 5: load. :";
        while (++rounds <= 100) {
            for (int i = 0; i < playerNumber; i++) {
                Player player = playerList[i];
                if (playerAlive == 1) break;
                if (player.isDead())
                    continue;
                if (player instanceof PlayerUser) {
                    while (true) {
                        Output.printlnAndDelay(Output.title("Game Option"));
                        int option = Input.getInput(hint, 0, 5);
                        switch (option) {
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
                        if (option == 0 || option == 2 || option == 3) break;
                    }
                }
                player.run();
                if (player.isDead())
                    playerAlive--;
            }
            if (playerAlive == 1) break;
        }

        Output.printlnAndDelay(Output.title("Game terminated"));
        int maxvalue = 0;
        for (Player player : playerList)
            if (player != null && !player.isDead() && player.getMoney() > maxvalue)
                maxvalue = player.getMoney();

        Output.printlnAndDelay("Winner:");
        for (Player player : playerList)
            if (player != null && !player.isDead() && player.getMoney() == maxvalue)
                Output.printlnAndDelay(player.toString());
    }
}
