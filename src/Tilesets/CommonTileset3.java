package Tilesets;

// For test map and title screen
import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;
import Utils.SlopeTileLayoutUtils;

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
        Frame sandFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder sandTile = new MapTileBuilder(sandFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(sandTile);

// ----- (0,1) small right slope -----
Frame slope01Frame = new FrameBuilder(getSubImage(0, 1))
        .withScale(tileScale)
        .build();

MapTileBuilder slope01 = new MapTileBuilder(slope01Frame)
        .withTileType(TileType.SLOPE);

mapTiles.add(slope01);


// ----- (0,2) medium right slope -----
Frame slope02Frame = new FrameBuilder(getSubImage(0, 2))
        .withScale(tileScale)
        .build();

MapTileBuilder slope02 = new MapTileBuilder(slope02Frame)
        .withTileType(TileType.SLOPE);

mapTiles.add(slope02);


// ----- (0,3) deep right slope -----
Frame slope03Frame = new FrameBuilder(getSubImage(0, 3))
        .withScale(tileScale)
        .build();

MapTileBuilder slope03 = new MapTileBuilder(slope03Frame)
        .withTileType(TileType.SLOPE);

mapTiles.add(slope03);


// ----- (0,4) left slope -----
Frame slope04Frame = new FrameBuilder(getSubImage(0, 4))
        .withScale(tileScale)
        .build();

MapTileBuilder slope04 = new MapTileBuilder(slope04Frame)
        .withTileType(TileType.SLOPE);

mapTiles.add(slope04);


        mapTiles.add(tile(0, 5, TileType.NOT_PASSABLE));  // wet sand



   // (1,0) wave curve
Frame waveCurveFrame = new FrameBuilder(getSubImage(1, 0))
.withScale(tileScale)
.build();
MapTileBuilder waveCurveTile = new MapTileBuilder(waveCurveFrame)
.withTileType(TileType.NOT_PASSABLE);
mapTiles.add(waveCurveTile);

// (1,1) water surface
Frame waterSurfaceFrame = new FrameBuilder(getSubImage(1, 1))
.withScale(tileScale)
.build();
MapTileBuilder waterSurfaceTile = new MapTileBuilder(waterSurfaceFrame)
.withTileType(TileType.JUMP_THROUGH_PLATFORM);
mapTiles.add(waterSurfaceTile);

// (1,2) ladder
Frame ladderFrame = new FrameBuilder(getSubImage(1, 2))
.withScale(tileScale)
.build();
MapTileBuilder ladderTile = new MapTileBuilder(ladderFrame)
.withTileType(TileType.JUMP_THROUGH_PLATFORM);
mapTiles.add(ladderTile);

// (1,3) cave rocks
Frame caveRocksFrame = new FrameBuilder(getSubImage(1, 3))
.withScale(tileScale)
.build();
MapTileBuilder caveRocksTile = new MapTileBuilder(caveRocksFrame)
.withTileType(TileType.JUMP_THROUGH_PLATFORM);
mapTiles.add(caveRocksTile);

// (1,4) palm tree
Frame palmTreeFrame = new FrameBuilder(getSubImage(1, 4))
.withScale(tileScale)
.build();
MapTileBuilder palmTreeTile = new MapTileBuilder(palmTreeFrame)
.withTileType(TileType.NOT_PASSABLE);
mapTiles.add(palmTreeTile);

// (1,5) branch
Frame branchFrame = new FrameBuilder(getSubImage(1, 5))
.withScale(tileScale)
.build();
MapTileBuilder branchTile = new MapTileBuilder(branchFrame)
.withTileType(TileType.JUMP_THROUGH_PLATFORM);
mapTiles.add(branchTile);

// (2,0) ocean wave
Frame oceanWaveFrame = new FrameBuilder(getSubImage(2, 0))
        .withScale(tileScale)
        .build();
MapTileBuilder oceanWaveTile = new MapTileBuilder(oceanWaveFrame)
        .withTileType(TileType.PASSABLE);
mapTiles.add(oceanWaveTile);

// (2,1) cloud
Frame cloudFrame = new FrameBuilder(getSubImage(2, 1))
        .withScale(tileScale)
        .build();
MapTileBuilder cloudTile = new MapTileBuilder(cloudFrame)
        .withTileType(TileType.PASSABLE);
mapTiles.add(cloudTile);

// (2,2) stone block
Frame stoneBlockFrame = new FrameBuilder(getSubImage(2, 2))
        .withScale(tileScale)
        .build();
MapTileBuilder stoneBlockTile = new MapTileBuilder(stoneBlockFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(stoneBlockTile);

// (2,3) bats cave
Frame batsCaveFrame = new FrameBuilder(getSubImage(2, 3))
        .withScale(tileScale)
        .build();
MapTileBuilder batsCaveTile = new MapTileBuilder(batsCaveFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(batsCaveTile);

// (2,4) palm side
Frame palmSideFrame = new FrameBuilder(getSubImage(2, 4))
        .withScale(tileScale)
        .build();
MapTileBuilder palmSideTile = new MapTileBuilder(palmSideFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(palmSideTile);

// (2,5) ship front
Frame shipFrontFrame = new FrameBuilder(getSubImage(2, 5))
        .withScale(tileScale)
        .build();
MapTileBuilder shipFrontTile = new MapTileBuilder(shipFrontFrame)
        .withTileType(TileType.PASSABLE);
mapTiles.add(shipFrontTile);

// (3,0) sun tile
Frame sunTileFrame = new FrameBuilder(getSubImage(3, 0))
        .withScale(tileScale)
        .build();
MapTileBuilder sunTile = new MapTileBuilder(sunTileFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(sunTile);

// (3,1) ocean
Frame oceanFrame = new FrameBuilder(getSubImage(3, 1))
        .withScale(tileScale)
        .build();
MapTileBuilder oceanTile = new MapTileBuilder(oceanFrame)
        .withTileType(TileType.PASSABLE);
mapTiles.add(oceanTile);

// (3,2) stone
Frame stoneFrame = new FrameBuilder(getSubImage(3, 2))
        .withScale(tileScale)
        .build();
MapTileBuilder stoneTile = new MapTileBuilder(stoneFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(stoneTile);

// (3,3) ship sails
Frame shipSailsFrame = new FrameBuilder(getSubImage(3, 3))
        .withScale(tileScale)
        .build();
MapTileBuilder shipSailsTile = new MapTileBuilder(shipSailsFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(shipSailsTile);

// (3,4) skull flag
Frame skullFlagFrame = new FrameBuilder(getSubImage(3, 4))
        .withScale(tileScale)
        .build();
MapTileBuilder skullFlagTile = new MapTileBuilder(skullFlagFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(skullFlagTile);

// (3,5) ship bow
Frame shipBowFrame = new FrameBuilder(getSubImage(3, 5))
        .withScale(tileScale)
        .build();
MapTileBuilder shipBowTile = new MapTileBuilder(shipBowFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(shipBowTile);

// (4,0) sky filler
Frame skyFillerFrame = new FrameBuilder(getSubImage(4, 0))
        .withScale(tileScale)
        .build();
MapTileBuilder skyFillerTile = new MapTileBuilder(skyFillerFrame)
        .withTileType(TileType.PASSABLE);
mapTiles.add(skyFillerTile);

// (4,1) sand bottom
Frame sandBottomFrame = new FrameBuilder(getSubImage(4, 1))
        .withScale(tileScale)
        .build();
MapTileBuilder sandBottomTile = new MapTileBuilder(sandBottomFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(sandBottomTile);

// (4,2) ship deck
Frame shipDeckFrame = new FrameBuilder(getSubImage(4, 2))
        .withScale(tileScale)
        .build();
MapTileBuilder shipDeckTile = new MapTileBuilder(shipDeckFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(shipDeckTile);

// (4,3) hull mid
Frame hullMidFrame = new FrameBuilder(getSubImage(4, 3))
        .withScale(tileScale)
        .build();
MapTileBuilder hullMidTile = new MapTileBuilder(hullMidFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(hullMidTile);

// (4,4) hull lower
Frame hullLowerFrame = new FrameBuilder(getSubImage(4, 4))
        .withScale(tileScale)
        .build();
MapTileBuilder hullLowerTile = new MapTileBuilder(hullLowerFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(hullLowerTile);

// (4,5) hull bottom
Frame hullBottomFrame = new FrameBuilder(getSubImage(4, 5))
        .withScale(tileScale)
        .build();
MapTileBuilder hullBottomTile = new MapTileBuilder(hullBottomFrame)
        .withTileType(TileType.NOT_PASSABLE);
mapTiles.add(hullBottomTile);

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
