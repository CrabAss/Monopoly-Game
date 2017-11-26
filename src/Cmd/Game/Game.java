package Cmd.Game;

import Cmd.Land.*;
import Cmd.Others.Input;
import Cmd.Others.Property;
import Cmd.Others.Output;
import Cmd.Player.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class for game control.
 */
public class Game {
    private final int MAXLANDNUMBER = 20;
    private final int MAXPLAYERNUMBER = 6;
    private final int STARTLAND = 1;
    private final int JAILLAND = 6;
    private Land landList[] = new Land[MAXLANDNUMBER + 1];
    private Player playerList[] = new Player[MAXPLAYERNUMBER + 1];
    private int playerAlive, playerNumber, currentPlayer;
    private int rounds;

    private final String landName[] = {"",
            "Start", "Central", "Wan Chai", "Tax Paid", "Stanley",
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

    /**
     * @return The amount of alive players.
     */
    public int getPlayerAlive() {
        playerAlive = 0;
        for (int i = 0; i < playerNumber; i++){
            if (!playerList[i].isDead()) playerAlive++;
        }
        return playerAlive;
    }

    /**
     * @return The amount of total players.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * @return The maximum amount of lands.
     */
    public int getMAXLANDNUMBER() {
        return MAXLANDNUMBER;
    }

    /**
     * @return The maximum amount of players.
     */
    public int getMAXPLAYERNUMBER() {
        return MAXPLAYERNUMBER;
    }

    /**
     * @return The number of the "Jail" land.
     */
    public int getJAILLAND() { return JAILLAND; }

    /**
     * @return The number of the "Start" land.
     */
    public int getSTARTLAND() {
        return STARTLAND;
    }

    /**
     * @return The land list of the game
     */
    public Land[] getLandList() {
        return landList;
    }

    /**
     * @return The player list of the game
     */
    public Player[] getPlayerList() {
        return playerList;
    }

    /**
     * @return The round of the game.
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * @param playerAlive The current amount of alive players.
     */
    public void setPlayerAlive(int playerAlive) {
        this.playerAlive = playerAlive;
    }

    /**
     * @param playerNumber The current amount of total players.
     */
    protected void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    /**
     * @return The current player of the game.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Set the current player of the game.
     * @param currentPlayer The current player to set.
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @param rounds The current round of the game.
     */
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    /**
     * Initializer: call initLand().
     */
    public Game() {
        initLand();
    }

    /**
     * Initialize landList
     */
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
                    landList[i] = new LandGotoJail(landName[i], i, landList[JAILLAND]);
                    break;
                default:
                    landList[i] = new LandProperty(new Property(landName[i], landPrice[i], landRent[i]), i);
                    break;
            }
        for (int i = 1; i < MAXLANDNUMBER; i++)
            landList[i].setNextLand(landList[i + 1]);
        landList[MAXLANDNUMBER].setNextLand(landList[1]);
    }

    /**
     * To initialize the player status and round count of a game
     */
    public void initGame() {
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

        rounds = 1;
        playerAlive = playerNumber;
        currentPlayer = 0;
    }

    /**
     * To save the game
     */
    public void saveGame() {
        String hint = "Please input the data path:";
        ObjectOutputStream oos = Input.getOutputStream(hint);
        try {
            oos.writeInt(playerNumber);
            oos.writeInt(playerAlive);
            oos.writeInt(currentPlayer);
            oos.writeInt(rounds);
            for (int i = 0; i < playerNumber; i++)
                oos.writeObject(playerList[i]);
            for (int i = 1; i <= MAXLANDNUMBER; i++) {
                oos.writeObject(landList[i]);
            }
            Output.println("Game saved");
        } catch (Exception e) { e.printStackTrace(); }
        Output.println("Save successfully.");
    }

    /**
     * To load the game
     */
    public void loadGame() {
        String hint = "Please input the data path:";
        ObjectInputStream ois = Input.getInputStream(hint);

        boolean loadSuccess = false;
        while (!loadSuccess) {
            loadSuccess = true;
            try {
                playerNumber = ois.readInt();
                playerAlive = ois.readInt();
                currentPlayer = ois.readInt();
                rounds = ois.readInt();
                for (int i = 0; i < playerNumber; i++)
                    playerList[i] = (Player) ois.readObject();
                for (int i = 1; i <= MAXLANDNUMBER; i++)
                    landList[i] = (Land) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                loadSuccess = false;
                if (GUI.Main.isGUI()) {System.out.println("Error"); System.exit(0);}
            }
        }
        Output.println("Load successfully.");
    }

    /**
     * to report the location of players
     */
    private void report() {
        Output.println(Output.title("Players' Location"));
        for (Player player : playerList)
            if (player != null && !player.isDead()) {
                Output.println(player + " : " + player.getPosition());
            }
    }

    /**
     * to run the game
     */
    public void runGame() {
        String hint = "0: continue; 1: report; 2: auto; 3: retire; 4: save; 5: load. :";
        while (rounds <= 100) {
            for ( ; currentPlayer < playerNumber; currentPlayer++) {
                Player player = playerList[currentPlayer];
                if (playerAlive == 1) break;
                if (player.isDead())
                    continue;
                if (player instanceof PlayerUser) {
                    while (true) {
                        Output.println(Output.title("Game Option"));
                        int option = Input.getInput(hint, 0, 5);
                        switch (option) {
                            case 0:
                                break;
                            case 1:
                                report();
                                break;
                            case 2:
                                player = playerList[currentPlayer] = player.toRobot();
                                break;
                            case 3:
                                player.retired();
                                break;
                            case 4:
                                saveGame();
                                break;
                            case 5:
                                loadGame();
                                player = playerList[currentPlayer];
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
            rounds++;
            currentPlayer = 0;
        }

        Output.println(Output.title("Game terminated"));
        int maxvalue = 0;
        for (Player player : playerList)
            if (player != null && !player.isDead() && player.getMoney() > maxvalue)
                maxvalue = player.getMoney();

        Output.println("Winner:");
        for (Player player : playerList)
            if (player != null && !player.isDead() && player.getMoney() == maxvalue)
                Output.println(player.toString());
    }
}
