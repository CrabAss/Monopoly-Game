package GUI;

import Player.PlayerAI;
import com.sun.org.apache.regexp.internal.RE;
import javafx.fxml.FXML;
import javafx.scene.shape.*;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;

public class ControllerGame {

    private final static int MAXPLAYERNUMBER = 6;
    private final static int MAXLANDNUMBER = 20;
    @FXML
    private GridPane Central;

    @FXML
    private Label TypePlayer1, TypePlayer2, TypePlayer3;
    @FXML
    private Label TypePlayer4, TypePlayer5, TypePlayer6;
    private Label TypePlayer[];

    @FXML
    private Label MoneyPlayer1, MoneyPlayer2, MoneyPlayer3;
    @FXML
    private Label MoneyPlayer4, MoneyPlayer5, MoneyPlayer6;
    private Label MoneyPlayer[];

    @FXML
    private Label StatuPlayer1, StatuPlayer2, StatuPlayer3;
    @FXML
    private Label StatuPlayer4, StatuPlayer5, StatuPlayer6;
    private Label StatuPlayer[];

    @FXML
    private Rectangle RectanglePlayer1, RectanglePlayer2, RectanglePlayer3;
    @FXML
    private Rectangle RectanglePlayer4, RectanglePlayer5, RectanglePlayer6;
    private Rectangle RectanglePlayer[];

    @FXML
    public void initialize() {
        TypePlayer = new Label[]{TypePlayer1, TypePlayer2, TypePlayer3, TypePlayer4, TypePlayer5, TypePlayer6};
        MoneyPlayer = new Label[]{MoneyPlayer1, MoneyPlayer2, MoneyPlayer3, MoneyPlayer4, MoneyPlayer5, MoneyPlayer6};
        StatuPlayer = new Label[]{StatuPlayer1, StatuPlayer2, StatuPlayer3, StatuPlayer4, StatuPlayer5, StatuPlayer6};
        RectanglePlayer = new Rectangle[]{RectanglePlayer1, RectanglePlayer2, RectanglePlayer3, RectanglePlayer4, RectanglePlayer5, RectanglePlayer6};
        updateGraph();
    }


    @FXML
    public void HandleAction(ActionEvent event){
        Rectangle t;

        //t = RectanglePlayer1;
        //Central.add(t, 0, 0);
        updateGraph();
    }

    public void updateGraph(){
        //GridPanePlayer1.
        for (int i = 0; i < Main.getGame().getPlayerNumber(); i++){
            if (Main.getGame().playerList[i] instanceof PlayerAI) TypePlayer[i].setText("AI");
            else TypePlayer[i].setText("Human");
            MoneyPlayer[i].setText(Main.getGame().getGUIhelper()[i].getMoney() + "");
            StatuPlayer[i].setText(Main.getGame().getGUIhelper()[i].getStatus());
        }
        for (int i  = Main.getGame().getPlayerNumber(); i < MAXPLAYERNUMBER; i++) {
            TypePlayer[i].setText("");
            MoneyPlayer[i].setText("");
            StatuPlayer[i].setText("");
        }
        //TypePlayer[1].setText("Funny");
    }

}
