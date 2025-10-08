package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;
import Utils.SlopeTileLayoutUtils;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CommonTileset extends Tileset {

    public CommonTileset() {
        super(ImageLoader.load("CommonTileSet3.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // grass
        Frame grassFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassTile = new MapTileBuilder(grassFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(grassTile);

        // sky
        Frame skyFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder skyTile = new MapTileBuilder(skyFrame);

        mapTiles.add(skyTile);

        // dirt
        Frame dirtFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder dirtTile = new MapTileBuilder(dirtFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(dirtTile);

        // sun
        Frame[] sunFrames = new Frame[]{
                new FrameBuilder(getSubImage(2, 0), 50)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 1), 50)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder sunTile = new MapTileBuilder(sunFrames);

        mapTiles.add(sunTile);

        // tree trunk with full hole
        Frame treeTrunkWithFullHoleFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkWithFullHoleTile = new MapTileBuilder(treeTrunkWithFullHoleFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkWithFullHoleTile);

        // left end branch
        Frame leftEndBranchFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder leftEndBranchTile = new MapTileBuilder(leftEndBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(leftEndBranchTile);

        // right end branch
        Frame rightEndBranchFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightEndBranchTile = new MapTileBuilder(rightEndBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(rightEndBranchTile);

        // tree trunk
        Frame treeTrunkFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkTile = new MapTileBuilder(treeTrunkFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkTile);

        // tree top leaves
        Frame treeTopLeavesFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTopLeavesTile = new MapTileBuilder(treeTopLeavesFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTopLeavesTile);

        // yellow flower
        Frame[] yellowFlowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 4), 65)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);

        mapTiles.add(yellowFlowerTile);

        // purple flower
        Frame[] purpleFlowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(0, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 4), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 5), 65)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder purpleFlowerTile = new MapTileBuilder(purpleFlowerFrames);

        mapTiles.add(purpleFlowerTile);

        // middle branch
        Frame middleBranchFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder middleBranchTile = new MapTileBuilder(middleBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(middleBranchTile);

        // tree trunk hole top
        Frame treeTrunkHoleTopFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkHoleTopTile = new MapTileBuilder(treeTrunkHoleTopFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkHoleTopTile);

        // tree trunk hole bottom
        Frame treeTrunkHoleBottomFrame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkHoleBottomTile = new MapTileBuilder(treeTrunkHoleBottomFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkHoleBottomTile);

        // top water
        Frame topWaterFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder topWaterTile = new MapTileBuilder(topWaterFrame);

        mapTiles.add(topWaterTile);

        // water
        Frame waterFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder waterTile = new MapTileBuilder(waterFrame)
                .withTileType(TileType.WATER);

        mapTiles.add(waterTile);

        // grey rock
        Frame greyRockFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder greyRockTile = new MapTileBuilder(greyRockFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(greyRockTile);

        // left 45 degree slope
        Frame leftSlopeFrame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftSlopeTile = new MapTileBuilder(leftSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftSlopeTile);

        // right 45 degree slope
        Frame rightSlopeFrame = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightSlopeTile = new MapTileBuilder(rightSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createRight45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(rightSlopeTile);

   //Kraken Killers
        // BLUESKY
        Frame BLUESKYFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder BLUESKYTile = new MapTileBuilder(BLUESKYFrame)
                .withTileType(TileType.PASSABLE)
                .withTileLayout(SlopeTileLayoutUtils.createBottomLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(BLUESKYTile);


        // SAND
        Frame SANDFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder SANDTile = new MapTileBuilder(SANDFrame)
                .withTileType(TileType.NOT_PASSABLE)
                .withTileLayout(SlopeTileLayoutUtils.createTopLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(SANDTile);

        //  boat bottom left 
        Frame boatBottomLEFTFrame = new FrameBuilder(getSubImage(5, 4))  // column = 4, row = 5
                .withScale(tileScale)
                .build();

        MapTileBuilder boatBottomLEFTTile = new MapTileBuilder(boatBottomLEFTFrame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(boatBottomLEFTTile);

        //  boat bottom right
        Frame boatBottomRIGHTFrame = new FrameBuilder(getSubImage(5, 5))  // column = 5, row = 5
                .withScale(tileScale)
                .build();

        MapTileBuilder boatBottomRIGHTTile = new MapTileBuilder(boatBottomRIGHTFrame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(boatBottomRIGHTTile);

                //  left corner side boat 
                Frame boatLeftFrame = new FrameBuilder(getSubImage(5, 0))  // column = 0, row = 5
                .withScale(tileScale)
                .build();

        MapTileBuilder boatLeftTile = new MapTileBuilder(boatLeftFrame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(boatLeftTile);

                //  right corner side boat 
                Frame boatRightFrame = new FrameBuilder(getSubImage(5, 3))  // column = 3, row = 5
                .withScale(tileScale)
                .build();

        MapTileBuilder boatRightTile = new MapTileBuilder(boatRightFrame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(boatRightTile);

                //  Window 1 
                Frame Window1Frame = new FrameBuilder(getSubImage(5, 1))  // column = 1, row = 5
                .withScale(tileScale)
                .build();

        MapTileBuilder Window1Tile = new MapTileBuilder(Window1Frame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(Window1Tile);

                //  Window 2 
                Frame Window2Frame = new FrameBuilder(getSubImage(5, 2))  // column = 2, row = 5
                .withScale(tileScale)
                .build();

        MapTileBuilder Window2Tile = new MapTileBuilder(Window2Frame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(Window2Tile);
      
        //   bottom 2 
        Frame Bottom2Frame = new FrameBuilder(getSubImage(3, 5))  // column = 5, row = 3
                .withScale(tileScale)
                .build();

        MapTileBuilder Bottom2Tile = new MapTileBuilder(Bottom2Frame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(Bottom2Tile);

        //   bottom 3
        Frame Bottom3Frame = new FrameBuilder(getSubImage(4, 5))  // column = 5, row = 4
                .withScale(tileScale)
                .build();

        MapTileBuilder Bottom3Tile = new MapTileBuilder(Bottom3Frame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(Bottom3Tile);

        //   Post
        Frame PostFrame = new FrameBuilder(getSubImage(4, 3))  // column = 3, row = 4
                .withScale(tileScale)
                .build();

        MapTileBuilder PostTile = new MapTileBuilder(PostFrame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(PostTile);

        //   Flag
        Frame FlagFrame = new FrameBuilder(getSubImage(4, 2))  // column = 2, row = 4
                .withScale(tileScale)
                .build();

        MapTileBuilder FlagTile = new MapTileBuilder(FlagFrame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(FlagTile);

        //   Light
        Frame LightFrame = new FrameBuilder(getSubImage(4, 4))  // column = 4, row = 4
                .withScale(tileScale)
                .build();

        MapTileBuilder LightTile = new MapTileBuilder(LightFrame)
                .withTileType(TileType.NOT_PASSABLE); 

        mapTiles.add(LightTile);

      //   Cloud
      Frame CloudFrame = new FrameBuilder(getSubImage(0, 6))  // column = 4, row = 4
      .withScale(tileScale)
      .build();

MapTileBuilder CloudTile = new MapTileBuilder(CloudFrame)
      .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(CloudTile);

        return mapTiles;
    }
}
