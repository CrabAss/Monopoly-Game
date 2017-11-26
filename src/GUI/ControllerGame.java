package GUI;

import Cmd.Land.LandProperty;
import Cmd.Others.BankruptException;
import Cmd.Others.Dice;
import Cmd.Others.Output;
import Cmd.Others.Property;
import Cmd.Player.Player;
import Cmd.Player.PlayerAI;
import Cmd.Player.PlayerUser;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class ControllerGame {

    private final static int MAXPLAYERNUMBER = 6, MAXLANDNUMBER = 20;

    @FXML
    public Label CurrentLandName, CurrentLandPrice, CurrentLandRent, NonSaleLandName, CurMoneyChange;

    @FXML
    public Button ButtonContinue;

    @FXML
    public Button ButtonAction, ButtonEndTurn;

    @FXML
    private GridPane GridPanePlayer1, GridPanePlayer2, GridPanePlayer3,
            GridPanePlayer4, GridPanePlayer5, GridPanePlayer6;
    private GridPane GridPanePlayer[];

    @FXML
    private GridPane Land1, Land2, Land3, Land4, Land5, Land6, Land7, Land8, Land9, Land10,
            Land11, Land12, Land13, Land14, Land15, Land16, Land17, Land18, Land19, Land20;
    private GridPane[] Land;

    @FXML
    private Label TypePlayer1, TypePlayer2, TypePlayer3,
            TypePlayer4, TypePlayer5, TypePlayer6;
    private Label TypePlayer[];

    @FXML
    private Label MoneyPlayer1, MoneyPlayer2, MoneyPlayer3,
            MoneyPlayer4, MoneyPlayer5, MoneyPlayer6;
    private Label MoneyPlayer[];

    @FXML
    private Label StatuPlayer1, StatuPlayer2, StatuPlayer3,
            StatuPlayer4, StatuPlayer5, StatuPlayer6;
    private Label StatuPlayer[];

    @FXML
    private Rectangle RectanglePlayer1, RectanglePlayer2, RectanglePlayer3,
            RectanglePlayer4, RectanglePlayer5, RectanglePlayer6;
    private Rectangle RectanglePlayer[];

    @FXML
    private TextArea ActionLog;

    @FXML
    public GridPane TurnMenu, ActionMenu;

    @FXML
    private ImageView Dice1, Dice2;
    private String color[] = {"#F8BBD0", "#FFE0B2", "#C8E6C9", "#B2EBF2", "#C5CAE9", "#E1BEE7"};

    public void changeMenu(){
        if (!ActionMenu.isVisible()) {
            ActionMenu.setVisible(true);
            //ActionMenu.setDisable(false);
        } else {
            ActionMenu.setVisible(false);
            //ActionMenu.setDisable(true);
        }
    }

    @FXML
    public void initialize() {
        GridPanePlayer = new GridPane[] {GridPanePlayer1, GridPanePlayer2, GridPanePlayer3,
                GridPanePlayer4, GridPanePlayer5, GridPanePlayer6};
        TypePlayer = new Label[] {TypePlayer1, TypePlayer2, TypePlayer3,
                TypePlayer4, TypePlayer5, TypePlayer6};
        MoneyPlayer = new Label[] {MoneyPlayer1, MoneyPlayer2, MoneyPlayer3,
                MoneyPlayer4, MoneyPlayer5, MoneyPlayer6};
        StatuPlayer = new Label[] {StatuPlayer1, StatuPlayer2, StatuPlayer3,
                StatuPlayer4, StatuPlayer5, StatuPlayer6};
        RectanglePlayer = new Rectangle[] {RectanglePlayer1, RectanglePlayer2, RectanglePlayer3,
                RectanglePlayer4, RectanglePlayer5, RectanglePlayer6};
        Land = new GridPane[] {null, Land1, Land2, Land3, Land4, Land5, Land6, Land7, Land8, Land9, Land10,
                Land11, Land12, Land13, Land14, Land15, Land16, Land17, Land18, Land19, Land20};

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
        changeMenu();
        Main.getGame().getGUIhelper()[Main.getGame().getCurPlayer()].run();
        updateGraph();
    }

    @FXML
    public void HandleRetire(){
        Player player = Main.getGame().playerList[Main.getGame().getCurPlayer()];
        player.retired();
        updateGraph();
        Main.getGame().nextTurn();
    }

    @FXML
    public void HandleAuto(){
        Player player = (PlayerUser)Main.getGame().playerList[Main.getGame().getCurPlayer()];
        Main.getGame().playerList[Main.getGame().getCurPlayer()] = (PlayerAI)((PlayerUser) player).toRobot();
        Main.getGame().getGUIhelper()[Main.getGame().getCurPlayer()] = new GUIPlayer(Main.getGame().playerList[Main.getGame().getCurPlayer()]);
        for (Property x: Main.getGame().playerList[Main.getGame().getCurPlayer()].propertyList){
            x.setBelongs(Main.getGame().playerList[Main.getGame().getCurPlayer()]);
        }
        updateGraph();
        HandleContinue();
    }
    @FXML
    public void HandleSave(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        //System.out.println(pic.getId());

        File file = fileChooser.showSaveDialog(Main.getMainStage());
        if (file != null) {
            try {
                Main.getGame().setCurrentPlayer(Main.getGame().getCurPlayer());
                Main.getGame().setSavePath(file.getAbsolutePath());
                Main.getGame().saveGame();

            } catch (Exception ex) {
                //Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
    public void HandleLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //System.out.println(pic.getId());

        File file = fileChooser.showOpenDialog(Main.getMainStage());
        if (file != null) {
            Main.getGame().initGame(6, 0);

            try {

                Main.getGame().setLoadPath(file.getAbsolutePath());
                Main.getGame().loadGame();
            } catch (Exception ex) {
                //Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < Main.getGame().getPlayerNumber(); i++)
                Main.getGame().getGUIhelper()[i].setPlayer(Main.getGame().playerList[i]);

            for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++){
                if (Main.getGame().landList[i] instanceof  LandProperty){
                    ((LandProperty)Main.getGame().landList[i]).getProperty().setBelongs(null);
                }
            }
            for (int i = 0; i < Main.getGame().getPlayerNumber(); i++) {
                for (Property x : Main.getGame().playerList[i].propertyList) {
                    x.setBelongs(Main.getGame().playerList[i]);
                }
            }

            System.out.println(Main.getGame().playerList[0].getPosition());
            updateGraph();
            Main.getGame().setCurPlayer(Main.getGame().getCurrentPlayer());
            Main.getGame().setCurPlayer(Main.getGame().getCurPlayer() - 1);
            Main.getGame().nextTurn();
        }
    }

    @FXML
    public void HandleEndTurn(){
        if (Objects.equals(ButtonEndTurn.getText(), "End turn")) {
            Player player = Main.getGame().playerList[Main.getGame().getCurPlayer()];
            Main.getGame().getGuiOutput().Print(player + " decides to end turn.");
            updateGraph();
            Main.getGame().nextTurn();
        } else { // Dice
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
                } else {
                    try {
                        player.getPosition().run(player);
                    } catch (Exception e) {
                        updateGraph();
                        Main.getGame().nextTurn();
                    }
                }
            } else if (player.getJailDay() == 3) { // The third day
                try {
                    Output.println(player + " must pay.");
                    player.decMoney(90);
                    player.release();
                    guiPlayer.run();
                } catch (BankruptException e) {
                    updateGraph();
                    Main.getGame().nextTurn();
                }
            }
        }
        updateGraph();
    }

    @FXML
    public void HandleAction(){
        Player player = Main.getGame().playerList[Main.getGame().getCurPlayer()];
        GUIPlayer guiPlayer = Main.getGame().getGUIhelper()[Main.getGame().getCurPlayer()];
        Property property = GUILandProperty.getCurProperty();
        // Buy
        try {
            if (player.getPosition() instanceof LandProperty) {
                Output.println(player + " decides to rent " + property.toString() + ".");
                player.addProperty(property);
                player.decMoney(property.getPrice());
                property.setBelongs(player);
                Main.getGame().Action.setDisable(true);

            } else { // In Jail
                Output.println(player + " decides to pay.");
                Main.getGame().EndTurn.setText("End turn");
                player.decMoney(90);
                player.release();
                guiPlayer.run();
            }
        } catch (BankruptException e) {
          updateGraph();
          Main.getGame().nextTurn();
        }
        updateGraph();
    }

    public void updateGraph(){

        // reset
        for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++){
            Land[i].setStyle("-fx-border-color: #000; -fx-background-color: #ffffff");
            for (int j = 0; j < Main.getGame().getPlayerNumber(); j++)
                Land[i].getChildren().remove(RectanglePlayer[j]);
        }

        for (int i = 0; i < Main.getGame().getPlayerNumber(); i++){
            GridPanePlayer[i].setStyle("");

        // set
            if (Main.getGame().playerList[i] instanceof PlayerAI)
                TypePlayer[i].setText("AI");
            else
                TypePlayer[i].setText("Human");

            MoneyPlayer[i].setText(Main.getGame().getGUIhelper()[i].getMoney() + "");
            StatuPlayer[i].setText(Main.getGame().getGUIhelper()[i].getStatus());

            if (!Main.getGame().playerList[i].isDead()) {
                RectanglePlayer[i].setVisible(true);
                Land[Main.getGame().getGUIhelper()[i].getPosition()].add(RectanglePlayer[i], i % 3, i / 3);
            } else
                RectanglePlayer[i].setVisible(false);

        }

        for (int i = Main.getGame().getPlayerNumber(); i < MAXPLAYERNUMBER; i++) {
            TypePlayer[i].setText("");
            MoneyPlayer[i].setText("");
            StatuPlayer[i].setText("");
            RectanglePlayer[i].setVisible(false);
            GridPanePlayer[i].setVisible(false);
        }

        for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++) {
            for (int j = 0; j < Main.getGame().getPlayerNumber(); j++) {
                if (Main.getGame().landList[i] instanceof LandProperty) {
                    if (((LandProperty) Main.getGame().landList[i]).getProperty().getBelongs() == (Player) Main.getGame().playerList[j]) {
                        Land[i].setStyle("-fx-border-color: #000; -fx-background-color: " + color[j]);
                    }
                }
            }
        }
        if (Main.getGame().getPlayerAlive() > 1 && Main.getGame().getRounds() <= 100) {
            if (Main.getGame().getCurPlayer() < Main.getGame().getPlayerNumber()) {
                GridPanePlayer[Main.getGame().getCurPlayer()].setStyle("-fx-background-color:" + color[Main.getGame().getCurPlayer()]);

                Player player = Main.getGame().playerList[Main.getGame().getCurPlayer()];
                CurrentLandName.setText(player.getPosition().getName());
                if (player.getPosition() instanceof LandProperty) {
                    if (((LandProperty) player.getPosition()).getProperty().getBelongs() != null)
                        CurrentLandPrice.setText("--");
                    else
                        CurrentLandPrice.setText("" + ((LandProperty) player.getPosition()).getProperty().getPrice());
                    CurrentLandRent.setText("" + ((LandProperty) player.getPosition()).getProperty().getRent());
                } else {
                    CurrentLandPrice.setText("--");
                    CurrentLandRent.setText("--");
                }

            }
        } else {
            ActionMenu.setDisable(true);
            TurnMenu.setDisable(true);
            CurrentLandName.setText("Finish!");
        }
    }
}
