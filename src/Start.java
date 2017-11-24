import Game.Game;
import java.util.*;

public class Start {
    public static void main(String[] args) {

        Scanner inp = new Scanner(System.in);
        int command = 0;
        System.out.println("This game is fictional.");
        while (true) {
            System.out.println("Monopoly");
            System.out.println("1. New game");
            System.out.println("2. Load game");
            System.out.println("3. Quit");
            try {
                if (inp.hasNextInt()) command = inp.nextInt();
                else{
                    System.out.print("Wrong");
                    continue;
                }
                if (command == 3) {
                    System.out.println("Thanks for playing!");
                    break;
                } else if (command == 1) {//Start a new game
                    Game game = new Game();
                    game.runGame();
                } else if (command == 2) {
                    Game game = new Game();
                    game.loadGame();
                } else System.out.println("Wrong input! Please input again");
            }catch (InputMismatchException e){
                System.out.println("Wrong input! Please input again");
                break;
            }
        }
    }
}
