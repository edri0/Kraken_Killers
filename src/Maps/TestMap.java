package Maps;

//import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.SwordPirate;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Chest;
import NPCs.Walrus;

//
//>>>>>>> 13a7320d62f92095bf8dc9c9716bb8773bb9a4d4//
import Tilesets.CommonTileset2;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new CommonTileset2());
        this.playerStartPosition = getMapTile(2, 11).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

       SwordPirate swordDude = new SwordPirate(getMapTile(12, 10).getLocation().subtractY(25),getMapTile(18, 10).getLocation().subtractY(25), Direction.LEFT);
       //getMapTile(16, 10).getLocation().subtractY(25)
       enemies.add(swordDude);

        //DinosaurEnemy SwordPirate = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
       // enemies.add(SwordPirate);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(24, 6).getLocation(),
                getMapTile(27, 6).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        );
        enhancedMapTiles.add(hmp);

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(32, 7).getLocation());
        enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    } 

    //@Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Chest chest = new Chest(getMapTile(30, 10).getLocation().subtractY(13));
        npcs.add(chest);

        return npcs;
        
    }
}
