import Game.Game;
import java.util.*;
import Others.Input;
import Others.Output;

public class Start {
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
            } else if (command == 2) { //Start a new game
                Game game = new Game();
                game.loadGame();
            } else if (command == 3) {
                Output.printlnAndDelay("Thanks for playing!");
                break;
            }
        }
    }
}
