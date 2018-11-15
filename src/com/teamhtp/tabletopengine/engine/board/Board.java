package com.teamhtp.tabletopengine.engine.board;

import com.teamhtp.tabletopengine.engine.Unique;

public class Board extends Unique {

    private Tile[][] tiles;

    public Board(int width, int height) {
        tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y);
            }
        }
    }

}
