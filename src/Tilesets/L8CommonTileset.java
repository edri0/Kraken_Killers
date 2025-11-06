package Tilesets;
//For test map and title screen
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
public class L8CommonTileset extends Tileset {

    public L8CommonTileset() {
        super(ImageLoader.load("L8CT2.png"), 16, 16, 3);
        
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // water 
        Frame waterFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder waterTile = new MapTileBuilder(waterFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(waterTile);

        // sky
        Frame skyFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder skyTile = new MapTileBuilder(skyFrame);

        mapTiles.add(skyTile);

          // boat1
        Frame boat1Frame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder boat1Tile = new MapTileBuilder(boat1Frame);

        mapTiles.add(boat1Tile);

        // window
        Frame windowFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder windowTile = new MapTileBuilder(windowFrame);

        mapTiles.add(windowTile);

        // pole
        Frame poleFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder poleTile = new MapTileBuilder(poleFrame);

        mapTiles.add(poleTile);
    
        // moon
        Frame moonFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder moonTile = new MapTileBuilder(moonFrame);

        mapTiles.add(moonTile);

        // boat2
        Frame boat2Frame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder boat2Tile = new MapTileBuilder(boat2Frame);

        mapTiles.add(boat2Tile);

        // boat3
        Frame boat3Frame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder boat3Tile = new MapTileBuilder(boat3Frame);

        mapTiles.add(boat3Tile);

        // boat4
        Frame boat4Frame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder boat4Tile = new MapTileBuilder(boat4Frame);

        mapTiles.add(boat4Tile);

        // boat5
        Frame boat5Frame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder boat5Tile = new MapTileBuilder(boat5Frame);

        mapTiles.add(boat5Tile);

        // boat6
        Frame boat6Frame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder boat6Tile = new MapTileBuilder(boat6Frame);

        mapTiles.add(boat6Tile);

        // light
        Frame lightFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder lightTile = new MapTileBuilder(lightFrame);

        mapTiles.add(lightTile);

        // boat7
        Frame boat7Frame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder boat7Tile = new MapTileBuilder(boat7Frame);

        mapTiles.add(boat7Tile);

        // white
        Frame whiteFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder whiteTile = new MapTileBuilder(whiteFrame);

        mapTiles.add(whiteTile);

    
        // black
        Frame blackFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder blackTile = new MapTileBuilder(blackFrame);

        mapTiles.add(blackTile);
                return mapTiles;

    }
}