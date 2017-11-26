package GUI;

import Cmd.Game.Game;
import Cmd.Others.Output;
import Cmd.Player.Player;
import Cmd.Player.PlayerAI;
import Cmd.Player.PlayerUser;
import javafx.scene.control.Button;
import javafx.scene.image.*;

/**
 * The GUIGame is a subclass of Game,
 * implement more methods which focus on GUI
 */
public class GUIGame extends Game {
    private Button Action, EndTurn;
    private ImageView Dice1, Dice2;
    private ControllerGame controllerGame;
    private GUIPlayer GUIhelper[] = new GUIPlayer[getMAXPLAYERNUMBER()];
    private GUIOutput guiOutput;
    private int curPlayer;
    private String SavePath, LoadPath;
    private Player gameWinner = null;    // winner

    /**
     * Build the GUIGame
     */
    GUIGame() {
        super();
    }

    /*----------------------setxx method-------------------*/
    /**
     * @param endTurn The endTurn button
     */
    void setEndTurn(Button endTurn) {
        EndTurn = endTurn;
    }

    /**
     * @param action the action button
     */
    void setAction(Button action) {
        Action = action;
    }

    /**
     * @param savePath set the save path
     */
    void setSavePath(String savePath) {
        SavePath = savePath;
    }

    /**
     * @param loadPath set the load path
     */
    void setLoadPath(String loadPath) {
        LoadPath = loadPath;
    }

    /**
     * @param curPlayer set current player
     */
    void setCurPlayer(int curPlayer) {
        this.curPlayer = curPlayer;
    }

    /**
     * @param controllerGame the controllergame class
     */
    void setControllerGame(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }

    /**
     * @param dice1 The frist dice image view
     */
    void setDice1(ImageView dice1) {
        Dice1 = dice1;
    }

    /**
     * @param dice2 set the second dice imageview
     */
    void setDice2(ImageView dice2) {
        Dice2 = dice2;
    }

    /**
     * @param guiOutput set the guiOutput field
     */
    void setGuiOutput(GUIOutput guiOutput) {
        this.guiOutput = guiOutput;
    }

    /*----------------------getxx method-------------------*/
    /**
     * @return get current player
     */
    int getCurPlayer() {
        return curPlayer;
    }

    /**
     * @return get the save path
     */
    public String getSavePath() {
        return SavePath;
    }

    /**
     * @return get the load path
     */
    public String getLoadPath() {
        return LoadPath;
    }

    /**
     * @return the guioutput of GUIOutput class
     * it is the field that impletments GUIoutput
     */
    public GUIOutput getGuiOutput() {
        return guiOutput;
    }

    /**
     * @return To get the game winner.
     */
    Player getWinner() {
        return gameWinner;
    }

    /**
     * To reset the game winner to null.
     */
    void clearWinner() {
        //noinspection AssignmentToNull
        gameWinner = null;
    }

    /**
     * @return return the GUIhelper list
     */
    GUIPlayer[] getGUIhelper() {
        return GUIhelper;
    }

    /**
     * @return getaction button
     */
    Button getAction() {
        return Action;
    }

    /**
     * @return get endturn button
     */
    Button getEndTurn() {
        return EndTurn;
    }

    /**
     * @return get dice1
     */
    ImageView getDice1() {
        return Dice1;
    }

    /**
     * @return get dice2
     */
    ImageView getDice2() {
        return Dice2;
    }

    /**
     * @return get controllergame
     */
    ControllerGame getControllerGame() {
        return controllerGame;
    }

    /**
     * @param NumberOfplayer the number of player
     * @param NumberOfAI the number of AI
     * This method is used to initialize all the player and current player
     */
    void initGame(int NumberOfplayer, int NumberOfAI) {
        setPlayerNumber(NumberOfplayer);

        for (int i = 1; i <= getPlayerNumber(); i++) {
            if (i <= getPlayerNumber() - NumberOfAI)
                getPlayerList()[i - 1] = new PlayerUser("Player " + i, getLandList()[getSTARTLAND()]);
            else
                getPlayerList()[i - 1] = new PlayerAI("Player " + i, getLandList()[getSTARTLAND()]);
            GUIhelper[i - 1] = new GUIPlayer(getPlayerList()[i - 1]);
        }
        setRounds(0);
        curPlayer = 10;
        setPlayerAlive(getPlayerNumber());
    }

    /**
     * This method is used to show the End Game interface
     */
    void EndGame() {
        int maxvalue = 0;
        for (Player player : getPlayerList())
            if (player != null && !player.isDead() && player.getMoney() > maxvalue) {
                maxvalue = player.getMoney();
                gameWinner = player;
            }

        guiOutput.Print("Winner: " + gameWinner.toString());
        getControllerGame().updateGraph();
    }

    /**
     * This method is nextTurn method
     * it pass turn to next player and detect if the game is end
     * it also refresh the menu of the interface
     */
    void nextTurn() {
        if (getPlayerAlive() == 1) {
            EndGame();
            return;
        }
        if (getControllerGame().getActionMenu().isVisible())
            getControllerGame().changeMenu();
        do {
            curPlayer++;
            if (curPlayer >= getPlayerNumber()) {
                curPlayer = 0;
                setRounds(getRounds() + 1);
                if (getRounds() > 100) {
                    EndGame();
                    return;
                }
                guiOutput.Print(Output.title("Round " + getRounds()));
            }
        } while (getPlayerList()[curPlayer].isDead());

        guiOutput.Print(Output.title("Player " + (curPlayer + 1)));
        getControllerGame().updateGraph();
        if (getPlayerList()[curPlayer] instanceof PlayerAI) {
            getControllerGame().HandleContinue();
        }
    }
}
