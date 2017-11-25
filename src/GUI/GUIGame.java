package GUI;

import Game.Game;
import Others.Input;
import Others.Output;
import Player.PlayerAI;
import Player.PlayerUser;
import javafx.scene.control.*;

public class GUIGame extends Game {
    private GUIPlayer GUIhelper[] = new GUIPlayer[getMAXPLAYERNUMBER()];
    private GUIOutput guiOutput;
    private GUIInput guiInput = new GUIInput();
    private Button Continue, Save, Load, Auto, Retire;
    private int curPlayer;

    public void setContinue(Button Continue){this.Continue = Continue;}
    public void setSave(Button Save){this.Save = Save;}
    public void setLoad(Button Load){this.Load = Load;}
    public void setAuto(Button Auto){this.Auto = Auto;}
    public void setRetire(Button Retire){this.Retire = Retire;}

    GUIGame(){super();}

    public void setGuiOutput(TextArea textArea) { this.guiOutput = new GUIOutput(textArea); }

    public GUIInput getGuiInput() {
        return guiInput;
    }

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

    }
    public void nextTurn(){
        if (getPlayerAlive() == 1){ EndGame(); return;}

        curPlayer++;
        if (curPlayer >= getPlayerNumber()){
            curPlayer = 0;
            rounds++;
            if (rounds > 100) {EndGame(); return;}
        }

        if (playerList[curPlayer] instanceof PlayerAI){

        }
    }

}
