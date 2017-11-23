package hk.edu.polyu.comp.comp2021.monopoly;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.image.*;
import javafx.scene.chart.*;
import javafx.scene.paint.*;
import javafx.scene.effect.*;
import javafx.stage.*;
import javafx.util.*;
import javafx.animation.*;

/**
 *
 */
public class Game extends Application{

    @Override
    public void start(Stage stage) {
        VBox box = new VBox();
        Scene scene = new Scene(box,854, 480);
        scene.setFill(null);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);

        stage.getIcons().add(new Image("hk/edu/polyu/comp/comp2021/monopoly/img/monopoly.png"));
        stage.setTitle("Monopoly");

        Line line = new Line(100, 200,   300,   110);
        line.setSmooth(true);
        box.getChildren().add(line);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
