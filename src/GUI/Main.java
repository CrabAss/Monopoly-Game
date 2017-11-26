package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private final static int WIDTH = 854;
    private final static int HEIGHT = 480;
    private static Stage MainStage;
    private static GUIGame game = new GUIGame();
    public static int GUI = 0;

    private static void setMainStage(Stage PrimaryStage) {
        MainStage = PrimaryStage;
    }

    /**
     * @return
     */
    public static GUIGame getGame() {
        return game;
    }

    /**
     * @param GUI
     */
    public static void setGUI(int GUI) {
        Main.GUI = GUI;
    }

    /**
     * @return
     */
    public static boolean isGUI() {return GUI == 1;}

    /**
     * @param root
     * @param width
     * @param height
     */
    public static void setStage(Parent root, int width, int height) {
        try {
            MainStage.hide();
            MainStage.getIcons().add(new Image("file:resources/icon.png"));
            MainStage.setTitle("Monopoly");
            MainStage.setScene(new Scene(root, width, height));
            MainStage.setResizable(false);
            MainStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setGUI(1);
        Parent root = FXMLLoader.load(getClass().getResource("FormWelcome.fxml"));
        setMainStage(primaryStage);
        setStage(root, WIDTH, HEIGHT);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
