package Maps;

//import Enemies.DinosaurEnemy;
//import Enemies.BugEnemy;
import Enemies.*;
import Engine.ImageLoader;
import Engine.SoundPlayer;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Chest;
//import NPCs.Walrus;
import Tilesets.CommonTileset3;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new CommonTileset3());
        this.playerStartPosition = getMapTile(1, 5).getLocation();
        SoundPlayer.stopMusic(); 
    }

@Override
public ArrayList<Enemy> loadEnemies() {
    ArrayList<Enemy> enemies = new ArrayList<>();

    // --------------------------------------------------
    // Sword Pirate 1
    // --------------------------------------------------
   SwordPirate swordPirate1 = new SwordPirate(
           getMapTile(7, 10).getLocation().subtractY(25),
           getMapTile(13, 10).getLocation().subtractY(25),
           Direction.LEFT
   );
   enemies.add(swordPirate1);


    // --------------------------------------------------
    // Sword Pirate 2
    // --------------------------------------------------
//     SwordPirate swordPirate2 = new SwordPirate(
//            getMapTile(20, 12).getLocation().subtractY(25),
//            getMapTile(26, 12).getLocation().subtractY(25),
//            Direction.RIGHT
//    );
//    enemies.add(swordPirate2);


    // --------------------------------------------------
    // Sword Pirate 3
    // --------------------------------------------------
   // SwordPirate swordPirate3 = new SwordPirate(
    //        getMapTile(30, 9).getLocation().subtractY(25),
    ///        getMapTile(35, 9).getLocation().subtractY(25),
    //        Direction.LEFT
   // );
   // enemies.add(swordPirate3);


    // --------------------------------------------------
    // Ranged Pirate
    // --------------------------------------------------
    RangeEnemy rangedPirate = new RangeEnemy(
           getMapTile(23, 8).getLocation().addY(2),
           getMapTile(22, 1).getLocation().addY(2),
           Direction.RIGHT
    );
   enemies.add(rangedPirate);

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

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(33, 8).getLocation());
        enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    } 

    //@Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Chest chest = new Chest(getMapTile(7, 10).getLocation().subtractY(13));
        npcs.add(chest);

        return npcs;
        
    }
}
