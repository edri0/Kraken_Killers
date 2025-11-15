package Tilesets;

// For test map and title screen
import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

public class CommonTileset3 extends Tileset {

    public CommonTileset3() {
        super(ImageLoader.load("CommonTileSet3.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // ----------------------------------------------------------
        // ROW 0 (0,0 → 0,5)
        // ----------------------------------------------------------
        mapTiles.add(tile(0, 0, TileType.NOT_PASSABLE)); // sand pyramid top
        mapTiles.add(tile(0, 1, TileType.PASSABLE));      // sky
        mapTiles.add(tile(0, 2, TileType.NOT_PASSABLE));  // dirt
        mapTiles.add(tile(0, 3, TileType.NOT_PASSABLE));  // slope 1
        mapTiles.add(tile(0, 4, TileType.NOT_PASSABLE));  // slope 2
        mapTiles.add(tile(0, 5, TileType.NOT_PASSABLE));  // wet sand

        // ----------------------------------------------------------
        // ROW 1 (1,0 → 1,5)
        // ----------------------------------------------------------
        mapTiles.add(tile(1, 0, TileType.NOT_PASSABLE)); // wave curve
        mapTiles.add(tile(1, 1, TileType.JUMP_THROUGH_PLATFORM)); // water surface
        mapTiles.add(tile(1, 2, TileType.JUMP_THROUGH_PLATFORM)); // ladder
        mapTiles.add(tile(1, 3, TileType.JUMP_THROUGH_PLATFORM)); // cave rocks
        mapTiles.add(tile(1, 4, TileType.NOT_PASSABLE));          // palm tree
        mapTiles.add(tile(1, 5, TileType.JUMP_THROUGH_PLATFORM)); // branch

        // ----------------------------------------------------------
        // ROW 2 (2,0 → 2,5)
        // ----------------------------------------------------------
        mapTiles.add(tile(2, 0, TileType.PASSABLE));     // ocean wave
        mapTiles.add(tile(2, 1, TileType.PASSABLE));     // cloud
        mapTiles.add(tile(2, 2, TileType.NOT_PASSABLE)); // stone block
        mapTiles.add(tile(2, 3, TileType.NOT_PASSABLE)); // bats cave
        mapTiles.add(tile(2, 4, TileType.NOT_PASSABLE)); // palm side
        mapTiles.add(tile(2, 5, TileType.PASSABLE));     // ship front

        // ----------------------------------------------------------
        // ROW 3 (3,0 → 3,5)
        // ----------------------------------------------------------
        mapTiles.add(tile(3, 0, TileType.NOT_PASSABLE)); // sun tile
        mapTiles.add(tile(3, 1, TileType.PASSABLE));     // ocean
        mapTiles.add(tile(3, 2, TileType.NOT_PASSABLE)); // stone
        mapTiles.add(tile(3, 3, TileType.NOT_PASSABLE)); // ship sails
        mapTiles.add(tile(3, 4, TileType.NOT_PASSABLE)); // skull flag
        mapTiles.add(tile(3, 5, TileType.NOT_PASSABLE)); // ship bow

        // ----------------------------------------------------------
        // ROW 4 (4,0 → 4,5)
        // ----------------------------------------------------------
        mapTiles.add(tile(4, 0, TileType.PASSABLE));     // sky filler
        mapTiles.add(tile(4, 1, TileType.NOT_PASSABLE)); // sand bottom
        mapTiles.add(tile(4, 2, TileType.NOT_PASSABLE)); // ship deck
        mapTiles.add(tile(4, 3, TileType.NOT_PASSABLE)); // hull mid
        mapTiles.add(tile(4, 4, TileType.NOT_PASSABLE)); // hull lower
        mapTiles.add(tile(4, 5, TileType.NOT_PASSABLE)); // hull bottom

        return mapTiles;
    }

    // ----------------------------------------------------------
    // CLEAN TILE HELPER
    // ----------------------------------------------------------
    private MapTileBuilder tile(int row, int col, TileType type) {
        Frame f = new FrameBuilder(getSubImage(row, col))
                .withScale(tileScale)
                .build();

        return new MapTileBuilder(f).withTileType(type);
    }

    // ----------------------------------------------------------
    // (OPTIONAL) EXTRA ANIMATION EXAMPLES YOU PASTED EARLIER
    // THEY ARE NOW SAFE, OUTSIDE THE tile() METHOD
    // ----------------------------------------------------------

    /*
    // sun example
    Frame[] sunFrames = new Frame[]{
            new FrameBuilder(getSubImage(2, 0))
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(2, 1))
                    .withScale(tileScale)
                    .build()
    };

    MapTileBuilder sunTile = new MapTileBuilder(sunFrames);
    */

    /*
    // yellow flower animation example
    Frame[] yellowFlowerFrames = new Frame[]{
            new FrameBuilder(getSubImage(1, 2), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(1, 3), 65)
                    .withScale(tileScale)
                    .build()
    };

    MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);
    */

    /*
    // slope example
    Frame leftSlopeFrame = new FrameBuilder(getSubImage(3, 3))
            .withScale(tileScale)
            .build();

    MapTileBuilder leftSlopeTile = new MapTileBuilder(leftSlopeFrame)
            .withTileType(TileType.SLOPE);
    */

}
