import Game.Game;
import java.util.*;
import Others.Input;
import Others.Output;

public class Start {
    public static void main(String[] args) {

        Scanner inp = new Scanner(System.in);
        int command = 0;
        Output.print("This game is fictional.");
        while (true) {
            Output.printTitle("Monopoly");
            Output.print("1. New game");
            Output.print("2. Load game");
            Output.print("3. Quit");

            command = Input.getInput("Your choice:", 1, 3);
            if (command == 1) {
                Game game = new Game();
                game.runGame();
            } else if (command == 2) {//Start a new game
                Game game = new Game();
                game.loadGame();
            } else if (command == 3) {
                System.out.println("Thanks for playing!");
                break;
            }
        }
    }
}
