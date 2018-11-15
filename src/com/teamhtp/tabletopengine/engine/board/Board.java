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

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public Optional<Tile> findTileOccupant(TileOccupant tileOccupant) {
        return findTileOccupant(tileOccupant.getUuid());
    }

    public Optional<Tile> findTileOccupant(UUID uuid) {
        Optional<Tile> found = Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter((tile) -> tile.getOccupant().getUuid().equals(uuid)).findFirst();
        return found;
    }

    public void putTileOccupant(TileOccupant tileOccupant, int x, int y) {
        Tile target = this.getTile(x, y);
        if (target.isOccupied()) {
            throw new TileOccupiedException();
        }
        Optional<Tile> foundOnTile = findTileOccupant(tileOccupant);
        if (foundOnTile.isPresent()) {
            throw new TileOccupantNotFoundException();
        }
        target.setOccupant(tileOccupant);
    }

    public void moveTileOccupant(TileOccupant tileOccupant, int x, int y) {
        Tile target = this.getTile(x, y);
        if (target.isOccupied()) {
            throw new TileOccupiedException();
        }
        Optional<Tile> foundOnTile = findTileOccupant(tileOccupant);
        if (!foundOnTile.isPresent()) {
            throw new TileOccupantNotFoundException();
        }
        foundOnTile.get().setOccupant(null);
        target.setOccupant(tileOccupant);
    }

}
