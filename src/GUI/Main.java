package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class Main is used to excute the program and call main stage
 */
public class Main extends Application {
    private final static int WIDTH = 854;
    private final static int HEIGHT = 480;
    private static Stage MainStage;
    private static GUIGame game = new GUIGame();
    private static int GUI = 0;

    /**
     * @param PrimaryStage the default program stage
     */
    private static void setMainStage(Stage PrimaryStage) { MainStage = PrimaryStage; }

    /**
     * @return the mainstage of program
     */
    static Stage getMainStage() {
        return MainStage;
    }

    /**
     * @return the current game process
     */
    public static GUIGame getGame() {
        return game;
    }

    /**
     * @param GUI the flag to judge if the program is using GUI
     */
    public static void setGUI(int GUI) {
        Main.GUI = GUI;
    }

    /**
     * @return return the GUI flag
     */
    public static int getGUI() {
        return GUI;
    }

    /**
     * @return the GUI flag
     */
    public static boolean isGUI() {return GUI == 1;}

    /**
     * @param root the fxml
     * @param width the stage width
     * @param height the stage height
     */
    static void setStage(Parent root, int width, int height) {
        try {
            MainStage.hide();
            MainStage.getIcons().add(new Image("GUI/resources/icon.png"));
            MainStage.setTitle("MONOPOLY");
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
     * @param args lunch javafx
     */
    public static void main(String[] args) {
        launch(args);
    }
}