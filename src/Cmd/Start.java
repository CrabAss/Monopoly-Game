package Cmd;

import Cmd.Game.Game;
import java.util.*;
import Cmd.Others.Input;
import Cmd.Others.Output;

/**
 * The command-line interface for user to start a Monopoly game.
 */
public class Start {

    /**
     * @param args not used.
     */
    public static void main(String[] args) {

        int command;
        Output.println("This game is fictional.");
        while (true) {
            Output.println(Output.title("MONOPOLY"));
            Output.println("1. New game");
            Output.println("2. Load game");
            Output.println("3. Quit");

            command = Input.getInput("Your choice:", 1, 3);
            if (command == 1) {
                Game game = new Game();
                game.initGame();
                game.runGame();
            } else if (command == 2) { //Cmd.Start a new game
                Game game = new Game();
                game.loadGame();
                game.runGame();
            } else if (command == 3) {
                Output.println("Thanks for playing!");
                break;
            }
        }
    }
}
