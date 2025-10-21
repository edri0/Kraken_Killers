package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
//import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;
//import Utils.SlopeTileLayoutUtils;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CommonTileset extends Tileset {

    public CommonTileset() {
        super(ImageLoader.load("CommonTileSet4.png"), 16, 16, 3);
        
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

      //  Kraken Killers

        // sand
        Frame sandFrame = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder sandTile = new MapTileBuilder(sandFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(sandTile);

        //Skies
        /*
         // Change the Sky codes to this 
        Frame DarkSkyFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder DarkSkyTile = new MapTileBuilder(DarkSkyFrame)
                .withTileType(TileType.PASSABLE)
                .withTileLayout(SlopeTileLayoutUtils.createBottomLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(DarkSkyTile);

         */

        // Bluesky
        Frame BlueskyFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder BlueskyTile = new MapTileBuilder(BlueskyFrame);

        mapTiles.add(BlueskyTile);

        // Redsky
        Frame RedskyFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder RedskyTile = new MapTileBuilder(RedskyFrame);

        mapTiles.add(RedskyTile);

        // BloodOrangesky
        Frame BloodOrangeskyFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder BloodOrangeskyTile = new MapTileBuilder(BloodOrangeskyFrame);

        mapTiles.add(BloodOrangeskyTile);

        //Orangesky
        Frame OrangeskyFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder OrangeskyTile = new MapTileBuilder(OrangeskyFrame);

        mapTiles.add(OrangeskyTile);

                // LightOrangesky
                Frame LightOrangeskyFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder LightOrangeskyTile = new MapTileBuilder(LightOrangeskyFrame);

        mapTiles.add(LightOrangeskyTile);

                // Tangerinesky
                Frame TangerineskyFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder TangerineskyTile = new MapTileBuilder(TangerineskyFrame);

        mapTiles.add(TangerineskyTile);

                  // Mustardsky
                Frame MustardskyFrame = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder MustardskyTile = new MapTileBuilder(MustardskyFrame);

        mapTiles.add(MustardskyTile);


                  // Yellowsky
                  Frame YellowskyFrame = new FrameBuilder(getSubImage(0, 6))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder YellowskyTile = new MapTileBuilder(YellowskyFrame);
  
          mapTiles.add(YellowskyTile);
       
                  // BrightYellowsky
                  Frame BrightYellowskyFrame = new FrameBuilder(getSubImage(2, 0))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder BrightYellowskyTile = new MapTileBuilder(BrightYellowskyFrame);
  
          mapTiles.add(BrightYellowskyTile);
        
                  // Nightsky
                  Frame NightskyFrame = new FrameBuilder(getSubImage(1, 1))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder NightskyTile = new MapTileBuilder(NightskyFrame);
  
          mapTiles.add(NightskyTile);


                  // Cloud2
                  Frame Cloud2Frame = new FrameBuilder(getSubImage(1, 0))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder Cloud2Tile = new MapTileBuilder(Cloud2Frame);
  
          mapTiles.add(Cloud2Tile);

                  // Cloud1
                  Frame Cloud1Frame = new FrameBuilder(getSubImage(1, 3))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder Cloud1Tile = new MapTileBuilder(Cloud1Frame);
  
          mapTiles.add(Cloud1Tile);


                  // Sun
                  Frame SunFrame = new FrameBuilder(getSubImage(1, 4))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder SunTile = new MapTileBuilder(SunFrame);
  
          mapTiles.add(SunTile);
        
                  // Moon
                  Frame MoonFrame = new FrameBuilder(getSubImage(1, 2))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder MoonTile = new MapTileBuilder(MoonFrame);
  
          mapTiles.add(MoonTile);
       
       
                  // Sunset
                  Frame SunsetFrame = new FrameBuilder(getSubImage(1, 6))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder SunsetTile = new MapTileBuilder(SunsetFrame);
  
          mapTiles.add(SunsetTile);
          //End of Skies

                  //LightPoint
                  Frame LightPointFrame = new FrameBuilder(getSubImage(2, 1))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder LightPointTile = new MapTileBuilder(LightPointFrame);
  
          mapTiles.add(LightPointTile);

        //Ocean Tiles
                  //Waves
                  Frame WavesFrame = new FrameBuilder(getSubImage(2, 3))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder WavesTile = new MapTileBuilder(WavesFrame);
  
          mapTiles.add(WavesTile);

                  //TopWater
                  Frame TopWaterFrame = new FrameBuilder(getSubImage(2, 4))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder TopWaterTile = new MapTileBuilder(TopWaterFrame);
  
          mapTiles.add(TopWaterTile);


                  //BottomWater
                  Frame BottomWaterFrame = new FrameBuilder(getSubImage(2, 5))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder BottomWaterTile = new MapTileBuilder(BottomWaterFrame);
  
          mapTiles.add(BottomWaterTile);
          
                  //Cave1
                  Frame Cave1Frame = new FrameBuilder(getSubImage(3, 3))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder Cave1Tile = new MapTileBuilder(Cave1Frame);
  
          mapTiles.add(Cave1Tile);

                  //Cave2
                  Frame Cave2Frame = new FrameBuilder(getSubImage(3, 4))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder Cave2Tile = new MapTileBuilder(Cave2Frame);
  
          mapTiles.add(Cave2Tile);

                  //Cave3
                  Frame Cave3Frame = new FrameBuilder(getSubImage(3, 5))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder Cave3Tile = new MapTileBuilder(Cave3Frame);
  
          mapTiles.add(Cave3Tile);

                  //Cave4
                  Frame Cave4Frame = new FrameBuilder(getSubImage(3, 6))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder Cave4Tile = new MapTileBuilder(Cave4Frame);
  
          mapTiles.add(Cave4Tile);

                  //Wave
                  Frame WaveFrame = new FrameBuilder(getSubImage(5, 3))
                  .withScale(tileScale)
                  .build();
  
          MapTileBuilder WaveTile = new MapTileBuilder(WaveFrame);
  
          mapTiles.add(WaveTile);



        // Ladder
        Frame LadderFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder LadderTile = new MapTileBuilder(LadderFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(LadderTile);


        // FlagPoint
        Frame FlagPointFrame = new FrameBuilder(getSubImage(2, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder FlagPointTile = new MapTileBuilder(FlagPointFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(FlagPointTile);

   //  boat bottom left 
   Frame boatBottomLEFTFrame = new FrameBuilder(getSubImage(5, 0))  // column = 4, row = 5
   .withScale(tileScale)
   .build();

MapTileBuilder boatBottomLEFTTile = new MapTileBuilder(boatBottomLEFTFrame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(boatBottomLEFTTile);

//  boat bottom right
Frame boatBottomRIGHTFrame = new FrameBuilder(getSubImage(5, 2))  // column = 5, row = 5
   .withScale(tileScale)
   .build();

MapTileBuilder boatBottomRIGHTTile = new MapTileBuilder(boatBottomRIGHTFrame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(boatBottomRIGHTTile);

   //  left corner side boat 
   Frame boatLeftFrame = new FrameBuilder(getSubImage(4, 0))  // column = 0, row = 5
   .withScale(tileScale)
   .build();

MapTileBuilder boatLeftTile = new MapTileBuilder(boatLeftFrame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(boatLeftTile);

   //  right corner side boat 
   Frame boatRightFrame = new FrameBuilder(getSubImage(4, 2))  // column = 3, row = 5
   .withScale(tileScale)
   .build();

MapTileBuilder boatRightTile = new MapTileBuilder(boatRightFrame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(boatRightTile);

   //  Window 1 
   Frame Window1Frame = new FrameBuilder(getSubImage(4, 1))  // column = 1, row = 5
   .withScale(tileScale)
   .build();

MapTileBuilder Window1Tile = new MapTileBuilder(Window1Frame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(Window1Tile);


//   bottom 2 
Frame Bottom2Frame = new FrameBuilder(getSubImage(5, 1))  // column = 5, row = 3
   .withScale(tileScale)
   .build();

MapTileBuilder Bottom2Tile = new MapTileBuilder(Bottom2Frame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(Bottom2Tile);

// FlagPost
Frame FlagPostFrame = new FrameBuilder(getSubImage(3, 0))  // column = 5, row = 3
   .withScale(tileScale)
   .build();

MapTileBuilder FlagPostTile = new MapTileBuilder(FlagPostFrame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(FlagPostTile);

// Flag
Frame FlagFrame = new FrameBuilder(getSubImage(3, 1))  // column = 5, row = 3
   .withScale(tileScale)
   .build();

MapTileBuilder FlagTile = new MapTileBuilder(FlagFrame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(FlagTile);

// Light
Frame LightFrame = new FrameBuilder(getSubImage(3, 2))  // column = 5, row = 3
   .withScale(tileScale)
   .build();

MapTileBuilder LightTile = new MapTileBuilder(LightFrame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(LightTile);

// Boxes
Frame BoxesFrame = new FrameBuilder(getSubImage(4, 4))  // column = 5, row = 3
   .withScale(tileScale)
   .build();

MapTileBuilder BoxesTile = new MapTileBuilder(BoxesFrame)
   .withTileType(TileType.NOT_PASSABLE); 

mapTiles.add(BoxesTile);
   
        return mapTiles;


        //Commented Code w/ Animations 
        /* // sun
        Frame[] sunFrames = new Frame[]{
                new FrameBuilder(getSubImage(2, 0))
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 1))
                        .withScale(tileScale)
                        .build()
        };


        MapTileBuilder sunTile = new MapTileBuilder(sunFrames);

        mapTiles.add(sunTile);
        */

             /*   // yellow flower
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
        */
        
        /*        // left end branch
        Frame leftEndBranchFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder leftEndBranchTile = new MapTileBuilder(leftEndBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(leftEndBranchTile);
         */

         /*        // right end branch
        Frame rightEndBranchFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightEndBranchTile = new MapTileBuilder(rightEndBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(rightEndBranchTile);
        */

        /*        // middle branch
        Frame middleBranchFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder middleBranchTile = new MapTileBuilder(middleBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(middleBranchTile);
         */

    }
}
