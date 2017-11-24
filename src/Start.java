import Game.Game;
import java.util.*;
import Others.Input;
import Others.Output;

public class Start {
    public static void main(String[] args) {

        Scanner inp = new Scanner(System.in);
        int command = 0;
        Output.println("This game is fictional.");
        while (true) {
            Output.printTitle("Monopoly");
            Output.println("1. New game");
            Output.println("2. Load game");
            Output.println("3. Quit");

            command = Input.getInput("Your choice:", 1, 3);
            if (command == 1) {
                Game game = new Game();
                game.newGame();
            } else if (command == 2) {//Start a new game
                Game game = new Game();
                game.loadGame();
            } else if (command == 3) {
                Output.println("Thanks for playing!");
                break;
            }
        }
    }
}
