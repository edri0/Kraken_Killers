package Maps;

import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import Level.*;
import Tilesets.CommonTileset;
import Utils.Direction;
import java.util.ArrayList;
import Enemies.Fitz;
import Enemies.RangeEnemy;
import Enemies.SwordPirate;
import Engine.ImageLoader;
import Engine.SoundPlayer;
import GameObject.Rectangle;

public class Level7 extends Map {

    public Level7(){
        super("level7.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(4, 9).getLocation();
        SoundPlayer.stopMusic();
    }

    @Override
    public void mapEntity(Coin c) {
        // Level 7 has no coins
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new SwordPirate(getMapTile(16, 17).getLocation().subtractY(25),
                getMapTile(17, 17).getLocation().subtractY(25), Direction.RIGHT));
        enemies.add(new RangeEnemy(getMapTile(29, 17).getLocation().addY(2),
                getMapTile(28, 17).getLocation().addY(2), Direction.LEFT));
        enemies.add(new Fitz(getMapTile(30, 17).getLocation().addY(2),
                getMapTile(33, 17).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new RangeEnemy(getMapTile(36, 18).getLocation().addY(2),
                getMapTile(37, 18).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new RangeEnemy(getMapTile(55, 7).getLocation().addY(2),
                getMapTile(54, 7).getLocation().addY(2), Direction.RIGHT));
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(15, 13).getLocation(),
                getMapTile(36, 13).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp);
        enhancedMapTiles.add(new EndLevelBox(getMapTile(59, 5).getLocation()));
        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        return new ArrayList<>();
    }
}

