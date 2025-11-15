package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;
import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the TitleScreen.png file
public class TitleCommonTileset extends Tileset {

    public TitleCommonTileset() {
        super(ImageLoader.load("TitleScreen.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // Black tile
        Frame blackFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder blackTile = new MapTileBuilder(blackFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(blackTile);

        // White tile
        Frame whiteFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder whiteTile = new MapTileBuilder(whiteFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(whiteTile);

        // Gold tile (uses same subimage as black, maybe intentional?)
        Frame goldFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder goldTile = new MapTileBuilder(goldFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(goldTile);

        return mapTiles;
    }
}
