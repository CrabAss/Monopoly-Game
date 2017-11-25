package GUI;

import Player.Player;

public class GUIPlayer {
    private Player player;

    public GUIPlayer(Player player){
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }

    public int getMoney() {return player.getMoney();}

    public String getStatus(){
        if (player.isInJail()) return "In Jail";
        else if (player.isDead()) return "Eliminated";
        else return "Playing";
    }
}
