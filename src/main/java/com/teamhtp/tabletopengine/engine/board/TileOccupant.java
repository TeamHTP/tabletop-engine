package com.teamhtp.tabletopengine.engine.board;

import com.teamhtp.tabletopengine.engine.Unique;

public class TileOccupant extends Unique {

    private String type;

    public TileOccupant(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
