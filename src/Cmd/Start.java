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

        Scanner inp = new Scanner(System.in);
        int command = 0;
        Output.printlnAndDelay("This game is fictional.");
        while (true) {
            Output.printlnAndDelay(Output.title("Monopoly"));
            Output.printlnAndDelay("1. New game");
            Output.printlnAndDelay("2. Load game");
            Output.printlnAndDelay("3. Quit");

            command = Input.getInput("Your choice:", 1, 3);
            if (command == 1) {
                Game game = new Game();
                game.newGame();
            } else if (command == 2) { //Cmd.Start a new game
                Game game = new Game();
                game.loadGame();
            } else if (command == 3) {
                Output.printlnAndDelay("Thanks for playing!");
                break;
            }
        }
    }
}
