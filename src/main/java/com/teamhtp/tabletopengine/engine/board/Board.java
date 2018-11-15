package com.teamhtp.tabletopengine.engine.board;

import com.teamhtp.tabletopengine.engine.Unique;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class Board extends Unique {

    private Tile[][] tiles;

    public Board(int width, int height) {
        tiles = new Tile[width][height];
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                tiles[x][y] = new Tile(x, y);
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public Optional<Tile> findTileOccupant(TileOccupant tileOccupant) {
        return Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter((tile) -> tile.getOccupants().contains(tileOccupant)).findFirst();
    }

    public void putTileOccupant(TileOccupant tileOccupant, int x, int y) {
        Tile target = getTile(x, y);
        if (target.isOccupied()) {
            throw new TileOccupiedException();
        }
        Optional<Tile> foundOnTile = findTileOccupant(tileOccupant);
        if (foundOnTile.isPresent()) {
            throw new TileOccupantAlreadyExistsException();
        }
        target.addOccupant(tileOccupant);
    }

    public void moveTileOccupant(TileOccupant tileOccupant, int x, int y) {
        Tile target = getTile(x, y);
        if (target.isOccupied()) {
            throw new TileOccupiedException();
        }
        Optional<Tile> foundOnTile = findTileOccupant(tileOccupant);
        if (!foundOnTile.isPresent()) {
            throw new TileOccupantNotFoundException();
        }
        foundOnTile.get().removeOccupant(tileOccupant);
        target.addOccupant(tileOccupant);
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

}
