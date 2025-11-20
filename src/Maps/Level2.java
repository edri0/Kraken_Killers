package Maps;

import EnhancedMapTiles.EndLevelBox;
// import EnhancedMapTiles.HorizontalMovingPlatform;
import Level.*;
import NPCs.Chest;
import Tilesets.CommonTileset;
import Utils.Direction;

//import Utils.Direction;
import java.util.ArrayList;
//import Engine.ImageLoader;
//import GameObject.Rectangle;

import Enemies.RangeEnemy;
import Enemies.Squidenemy;
import Enemies.SwordPirate;
import Engine.SoundPlayer;



public class Level2 extends Map {

    public Level2() {
        super("level2.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 11).getLocation(); // starting tile
    
        SoundPlayer.stopMusic(); 
    
    }
    // @Override
    // public ArrayList<Enemy> loadEnemies() {
    //     ArrayList<Enemy> enemies = new ArrayList<>();

    //    // BugEnemy bugEnemy = new BugEnemy(getMapTile(16, 10).getLocation().subtractY(25), Direction.LEFT);
    //    // enemies.add(bugEnemy);

    //    // DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
    //    // enemies.add(dinosaurEnemy);

    //     return enemies;
    // }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
    
        // -------------------------
        // Sword Pirate
        // -------------------------
        SwordPirate swordDude = new SwordPirate(
                getMapTile(19, 16).getLocation().subtractY(25),
                getMapTile(20, 16).getLocation().subtractY(25),
                Direction.RIGHT
        );
        enemies.add(swordDude);
        SwordPirate swordDude2 = new SwordPirate(
            getMapTile(5, 13).getLocation().subtractY(25),
            getMapTile(5, 14).getLocation().subtractY(25),
            Direction.RIGHT
    );
    enemies.add(swordDude2);
    

   

            RangeEnemy rangedPirate = new RangeEnemy(getMapTile(23, 16).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
    enemies.add(rangedPirate);

    RangeEnemy rangedPirate2 = new RangeEnemy(getMapTile(41, 18).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
    enemies.add(rangedPirate2);
    
        return enemies;
    }
    

     @Override
     public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //    HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
    //             ImageLoader.load("GreenPlatform.png"),
    //             getMapTile(24, 6).getLocation(),
    //             getMapTile(27, 6).getLocation(),
    //             TileType.JUMP_THROUGH_PLATFORM,
    //             3,
    //             new Rectangle(0, 6,16,4),
    //             Direction.RIGHT
    //     );
        
    //     enhancedMapTiles.add(hmp);

// @Override
// public ArrayList<Enemy> loadEnemies() {
//     ArrayList<Enemy> enemies = new ArrayList<>();

//    SwordPirate swordDude = new SwordPirate(getMapTile(12, 10).getLocation().subtractY(25),getMapTile(18, 10).getLocation().subtractY(25), Direction.LEFT);
//    //getMapTile(16, 10).getLocation().subtractY(25)
//    enemies.add(swordDude);

//     RangeEnemy rangedPirate = new RangeEnemy(getMapTile(23, 8).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
//     enemies.add(rangedPirate);

//     return enemies;
// }
    


     EndLevelBox endLevelBox = new EndLevelBox(getMapTile(49, 19).getLocation());
        enhancedMapTiles.add(endLevelBox);

         return enhancedMapTiles;
    } 

    //@Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        
        //Chest chest = new Chest(getMapTile(49, 28).getLocation().subtractY(13));
        //npcs.add(chest);
        
        return npcs;
        
        }
        
    }

