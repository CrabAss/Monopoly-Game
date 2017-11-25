package GUI;

import Player.PlayerAI;
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
    private GridPane Land1, Land2, Land3, Land4, Land5, Land6, Land7, Land8, Land9, Land10;
    @FXML
    private GridPane Land11, Land12, Land13, Land14, Land15, Land16, Land17, Land18, Land19, Land20;
    private GridPane[] Land;

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
    private TextArea ActionLog;

    @FXML
    private GridPane TurnMenu, ActionMenu;

    public void changeMenu(){
        if (ActionMenu.getOpacity() == 0.0f) {ActionMenu.setOpacity(1.0f); ActionMenu.setDisable(false);}
        else {ActionMenu.setOpacity(0.0f); ActionMenu.setDisable(true);}
    }
    @FXML
    public void initialize() {
        TypePlayer = new Label[]{TypePlayer1, TypePlayer2, TypePlayer3, TypePlayer4, TypePlayer5, TypePlayer6};
        MoneyPlayer = new Label[]{MoneyPlayer1, MoneyPlayer2, MoneyPlayer3, MoneyPlayer4, MoneyPlayer5, MoneyPlayer6};
        StatuPlayer = new Label[]{StatuPlayer1, StatuPlayer2, StatuPlayer3, StatuPlayer4, StatuPlayer5, StatuPlayer6};
        RectanglePlayer = new Rectangle[]{RectanglePlayer1, RectanglePlayer2, RectanglePlayer3, RectanglePlayer4, RectanglePlayer5, RectanglePlayer6};
        Land = new GridPane[]{Land1,
                Land1, Land2, Land3, Land4, Land5,
                Land6, Land7, Land8, Land9, Land10,
                Land11, Land12, Land13, Land14, Land15,
                Land16, Land17, Land18, Land19, Land20
        };
        changeMenu();
        updateGraph();
    }
    @FXML
    public void HandleContinue(ActionEvent event){
        updateGraph();
    }

    @FXML
    public void HandleAction(ActionEvent event){

    }

    public void updateGraph(){
        //GridPanePlayer1.
        for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++){
            for (int j = 0; j < Main.getGame().getPlayerNumber(); j++) Land[i].getChildren().remove(RectanglePlayer[j]);
        }
        for (int i = 0; i < Main.getGame().getPlayerNumber(); i++){
            if (Main.getGame().playerList[i] instanceof PlayerAI) TypePlayer[i].setText("AI");
            else TypePlayer[i].setText("Human");
            MoneyPlayer[i].setText(Main.getGame().getGUIhelper()[i].getMoney() + "");
            StatuPlayer[i].setText(Main.getGame().getGUIhelper()[i].getStatus());
            RectanglePlayer[i].setOpacity(1.0f);
            Land[Main.getGame().getGUIhelper()[i].getPosition()].add(RectanglePlayer[i], i % 3, i / 3);
        }
        for (int i  = Main.getGame().getPlayerNumber(); i < MAXPLAYERNUMBER; i++) {
            TypePlayer[i].setText("");
            MoneyPlayer[i].setText("");
            StatuPlayer[i].setText("");
            RectanglePlayer[i].setOpacity(0.0f);
        }

        //TypePlayer[1].setText("Funny");
    }

}
