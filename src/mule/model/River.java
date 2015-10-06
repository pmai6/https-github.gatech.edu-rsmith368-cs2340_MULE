package mule.model;

import java.util.ArrayList;
import java.util.List;

public class River implements Tile {

    private Player player;
    private boolean isOwned;
    private List<Mule> mules = new ArrayList<>();

    public River() {

    }
    public void setOwner(Player player) {
        this.player = player;
    }
    public Tile getLocation(Tile[][] tiles) { return tiles[4][3]; }

    public Player getOwner() {
        return player;
    }

    public int getCost() {
        return 300;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setIsOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public List<Mule> getMule() {
        return mules;
    }

    public void addMule (Mule mule) {
        mules.add(mule);
    }

}

