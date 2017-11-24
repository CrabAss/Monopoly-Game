package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final static int WIDTH = 854;
    private final static int HEIGHT = 480;
    private static Stage MainStage;

    private static void setMainStage(Stage PrimaryStage) {MainStage = PrimaryStage;}

    public static void setStage(Parent root){
        try{
            MainStage.hide();
            MainStage.setTitle("Monopoly");
            MainStage.setScene(new Scene(root, WIDTH, HEIGHT));
            MainStage.setResizable(false);
            MainStage.show();
        }catch (Exception e){
            System.out.print(e);
        }
    }
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FormWelcome.fxml"));
        setMainStage(primaryStage);
        setStage(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
