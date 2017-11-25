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

    public int getPosition(){
        for (int i = 1; i <= Main.getGame().getMAXLANDNUMBER(); i++)
            if (player.getPosition() == Main.getGame().landList[i]) return i;
        return 0;
    }
}
