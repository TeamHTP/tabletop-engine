package com.teamhtp.tabletopengine.engine.board;

public class Tile {

    private int x;
    private int y;
    private TileOccupant occupant;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupant = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return occupant != null;
    }

    public TileOccupant getOccupant() {
        return occupant;
    }

    public void setOccupant(TileOccupant occupant) {
        this.occupant = occupant;
    }

}
