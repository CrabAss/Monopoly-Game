package GUI;

import Game.Game;
import Others.Input;
import Player.PlayerAI;
import Player.PlayerUser;

public class GUIGame extends Game {
    private int playerNumber;

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
}
