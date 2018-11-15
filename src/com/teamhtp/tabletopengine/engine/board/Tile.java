package com.teamhtp.tabletopengine.engine.board;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    private int x;
    private int y;
    private List<TileOccupant> occupants;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        occupants = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return !occupants.isEmpty();
    }

    public List<TileOccupant> getOccupants() {
        return occupants;
    }

    public void addOccupant(TileOccupant occupant) {
        occupants.add(occupant);
    }

    public void removeOccupant(TileOccupant occupant) {
        occupants.remove(occupant);
    }

}
