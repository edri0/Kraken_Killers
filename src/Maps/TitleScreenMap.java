package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset2;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite cat;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset2());
        Point catLocation = getMapTile(6, 8).getLocation().subtractX(32).subtractY(8);
        cat = new Sprite(ImageLoader.loadSubImage("JackSparrow.png", Colors.MAGENTA, 0, 0, 32, 32));
        cat.setScale(3);
        cat.setLocation(catLocation.x, catLocation.y);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        cat.draw(graphicsHandler);
    }

}
