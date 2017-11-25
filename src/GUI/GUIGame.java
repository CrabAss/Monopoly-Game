package GUI;

import Cmd.Game.Game;
import Cmd.Others.Output;
import Cmd.Player.PlayerAI;
import Cmd.Player.PlayerUser;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;

public class GUIGame extends Game {
    private GUIPlayer GUIhelper[] = new GUIPlayer[getMAXPLAYERNUMBER()];
    private GUIOutput guiOutput;
    public Button Continue, Save, Load, Auto, Retire;
    public Button Action, EndTurn;
    public ImageView Dice1, Dice2;
    public ControllerGame controllerGame;
    private int curPlayer;

    public void setContinue(Button Continue){this.Continue = Continue;}
    public void setSave(Button Save){this.Save = Save;}
    public void setLoad(Button Load){this.Load = Load;}
    public void setAuto(Button Auto){this.Auto = Auto;}
    public void setRetire(Button Retire){this.Retire = Retire;}
    public void setEndTurn(Button endTurn) {
        EndTurn = endTurn;
    }
    public void setAction(Button action) {
        Action = action;
    }

    @Override
    public int getRounds() { return super.getRounds(); }

    public void setControllerGame(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }

    public void setDice1(ImageView dice1) {
        Dice1 = dice1;
    }

    public void setDice2(ImageView dice2) {
        Dice2 = dice2;
    }

    public int getCurPlayer() {return curPlayer;}
    public GUIOutput getGuiOutput() {return guiOutput;}

    GUIGame(){super();}

    public void setGuiOutput(TextArea textArea) { this.guiOutput = new GUIOutput(textArea); }


    public GUIPlayer[] getGUIhelper() {
        return GUIhelper;
    }

    public void initGame(int NumberOfplayer, int NumberOfAI) {
        setPlayerNumber(NumberOfplayer);

        for (int i = 1; i <= getPlayerNumber(); i++) {
            if (i <= getPlayerNumber() - NumberOfAI) playerList[i - 1] = new PlayerUser("Player " + i, landList[getSTARTLAND()]);
            else playerList[i - 1] = new PlayerAI("Player " + i, landList[getSTARTLAND()]);
            GUIhelper[i - 1] = new GUIPlayer(playerList[i - 1]);
        }
        rounds = 0;
        curPlayer = 10;
        setPlayerAlive(getPlayerNumber());
    }
    public void EndGame(){
        controllerGame.ActionMenu.setDisable(true);
        controllerGame.TurnMenu.setDisable(true);
        controllerGame.CurrentLandName.setText("Finish!");
    }
    public void nextTurn(){
        if (getPlayerAlive() == 1){ EndGame(); return;}
        if (controllerGame.ActionMenu.getOpacity() == 1.0f) controllerGame.changeMenu();
        do{
            curPlayer++;
            if (curPlayer >= getPlayerNumber()) {
                curPlayer = 0;
                rounds++;
                if (rounds > 100) {
                    EndGame();
                    return;
                }
                guiOutput.Print(Output.title("Round " + rounds));
            }
        }while (playerList[curPlayer].isDead());

        guiOutput.Print(Output.title("Player " + (curPlayer + 1)));
        controllerGame.updateGraph();
        if (playerList[curPlayer] instanceof PlayerAI) {
            Main.getGame().controllerGame.HandleContinue();
        }
    }

}
