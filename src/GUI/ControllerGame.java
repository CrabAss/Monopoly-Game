package GUI;

import Cmd.Land.Land;
import Cmd.Land.LandProperty;
import Cmd.Others.BankruptException;
import Cmd.Others.Dice;
import Cmd.Others.Output;
import Cmd.Others.Property;
import Cmd.Player.Player;
import Cmd.Player.PlayerAI;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class ControllerGame {
    @FXML
    public Label CurrentLandName, CurrentLandPrice, CurrentLandRent;

    @FXML
    public Button ButtonContinue;

    @FXML
    public Button ButtonAction, ButtonEndTurn;

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
    public GridPane TurnMenu, ActionMenu;

    @FXML
    private ImageView Dice1, Dice2;
    private String color[] = {"#F8BBD0", "#FFE0B2", "#C8E6C9", "#B2EBF2", "#C5CAE9", "#E1BEE7"};

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
        Main.getGame().setGuiOutput(ActionLog);
        Main.getGame().setContinue(ButtonContinue);
        Main.getGame().setEndTurn(ButtonEndTurn);
        Main.getGame().setAction(ButtonAction);
        Main.getGame().setDice1(Dice1);
        Main.getGame().setDice2(Dice2);
        Main.getGame().setControllerGame(this);
        changeMenu();
        updateGraph();
    }
    @FXML
    public void HandleContinue(){
        Main.getGame().getGUIhelper()[Main.getGame().getCurPlayer()].run();
        updateGraph();
        changeMenu();
        //System.out.print("Continue pressed");
    }

    @FXML
    public void HandleRetire(){
        Player player = Main.getGame().playerList[Main.getGame().getCurPlayer()];
        player.retired();
        Main.getGame().nextTurn();
    }
    @FXML
    public void HandleEndTurn(){
        if (Objects.equals(ButtonEndTurn.getText(), "End turn")) {
            Player player = Main.getGame().playerList[Main.getGame().getCurPlayer()];
            Main.getGame().getGuiOutput().Print(player + "decide to end turn.");
            Main.getGame().nextTurn();
            updateGraph();
        }else{//Dice
            Player player = Main.getGame().playerList[Main.getGame().getCurPlayer()];
            GUIPlayer guiPlayer = Main.getGame().getGUIhelper()[Main.getGame().getCurPlayer()];

            Dice dice = new Dice();
            dice.dice();
            Main.getGame().Dice1.setImage(new Image("GUI/resources/d" + dice.getX() + ".jpg"));
            Main.getGame().Dice2.setImage(new Image("GUI/resources/d" + dice.getY() + ".jpg"));
            Main.getGame().EndTurn.setText("End turn");
            Main.getGame().Action.setDisable(true);

            if (dice.isEqual()) {
                player.release();
                int step = dice.getStep();

                player.move(step);

                Main.getGame().controllerGame.updateGraph();
                if (player.getPosition() instanceof Cmd.Land.LandProperty){
                    GUILandProperty Guimodule = new GUILandProperty();
                    Guimodule.run(player.getPosition(), player);
                }else {
                    try {
                        player.getPosition().run(player);
                    }catch(Exception e){
                        updateGraph();
                        Main.getGame().nextTurn();
                    }
                }
            }else if (player.getJailDay() == 3){//The thrid day
                try{
                    Output.printlnAndDelay(player + " must pay.");
                    player.decMoney(90);
                    player.release();
                    guiPlayer.run();
                }catch (Exception e){
                    updateGraph();
                    Main.getGame().nextTurn();
                }
            }
        }
        updateGraph();
    }

    @FXML
    public void HandleAction(ActionEvent event){
        Player player = Main.getGame().playerList[Main.getGame().getCurPlayer()];
        GUIPlayer guiPlayer = Main.getGame().getGUIhelper()[Main.getGame().getCurPlayer()];
        Property property = GUILandProperty.getCurProperty();
        //Buy
        try {
            if (player.getPosition() instanceof LandProperty) {
                Output.printlnAndDelay(player + " decides to rent " + property.toString() + ".");
                player.addProperty(property);
                player.decMoney(property.getPrice());
                property.setBelongs(player);
                Main.getGame().Action.setDisable(true);

            } else {//In Jail
                Output.printlnAndDelay(player + " decides to pay.");
                player.decMoney(22290);
                player.release();
                guiPlayer.run();
            }
        } catch (BankruptException e){
          updateGraph();
          Main.getGame().nextTurn();
        }
        updateGraph();
    }

    public void updateGraph(){
        //GridPanePlayer1.
        for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++) Land[i].setStyle("-fx-border-color: #000; -fx-background-color: #ffffff");
        for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++){
            for (int j = 0; j < Main.getGame().getPlayerNumber(); j++) Land[i].getChildren().remove(RectanglePlayer[j]);
        }
        for (int i = 0; i < Main.getGame().getPlayerNumber(); i++){
            if (Main.getGame().playerList[i] instanceof PlayerAI) TypePlayer[i].setText("AI");
            else TypePlayer[i].setText("Human");
            MoneyPlayer[i].setText(Main.getGame().getGUIhelper()[i].getMoney() + "");
            StatuPlayer[i].setText(Main.getGame().getGUIhelper()[i].getStatus());

            if (!Main.getGame().playerList[i].isDead()) {
                RectanglePlayer[i].setOpacity(1.0f);
                Land[Main.getGame().getGUIhelper()[i].getPosition()].add(RectanglePlayer[i], i % 3, i / 3);
            }else RectanglePlayer[i].setOpacity(0.0f);

        }
        //Cmd.Land[12].add(RectanglePlayer[1], 0, 0);
        for (int i  = Main.getGame().getPlayerNumber(); i < MAXPLAYERNUMBER; i++) {
            TypePlayer[i].setText("");
            MoneyPlayer[i].setText("");
            StatuPlayer[i].setText("");
            RectanglePlayer[i].setOpacity(0.0f);
        }
        for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++)
            for (int j = 0; j < Main.getGame().getPlayerNumber(); j++) {
                if (Main.getGame().landList[i] instanceof  LandProperty){
                    if (((LandProperty)Main.getGame().landList[i]).getProperty().getBelongs() == Main.getGame().playerList[j]){
                        Land[i].setStyle("-fx-border-color: #000; -fx-background-color: " + color[j]);
                    }
                }
            }
        if (Main.getGame().getCurPlayer() < Main.getGame().getPlayerNumber()) {
            Player player = Main.getGame().playerList[Main.getGame().getCurPlayer()];
            CurrentLandName.setText(player.getPosition().getName());
            if (player.getPosition() instanceof LandProperty){
                CurrentLandPrice.setText("" + ((LandProperty)player.getPosition()).getProperty().getPrice());
                CurrentLandRent.setText("" + ((LandProperty)player.getPosition()).getProperty().getRent());
            }else {
                CurrentLandPrice.setText("0");
                CurrentLandRent.setText("0");
            }
        }

        //TypePlayer[1].setText("Funny");
    }

}
