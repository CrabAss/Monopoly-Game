package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Game.Game;

public class Main extends Application {

    private final static int WIDTH = 854;
    private final static int HEIGHT = 480;
    private static Stage MainStage;
    private static GUIGame game = new GUIGame();

    private static void setMainStage(Stage PrimaryStage) {
        MainStage = PrimaryStage;
    }

    public static GUIGame getGame() {
        return game;
    }

    public static void setStage(Parent root, int width, int height) {
        try {
            MainStage.hide();
            MainStage.setTitle("Monopoly");
            MainStage.setScene(new Scene(root, width, height));
            MainStage.setResizable(false);
            MainStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FormWelcome.fxml"));
        setMainStage(primaryStage);
        setStage(root, WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
