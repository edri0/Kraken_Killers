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
import Enemies.SwordPirate;
import Engine.SoundPlayer;
import Enemies.Krabs;

public class Level4 extends Map{

    public Level4(){
        super("level4.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(4, 11).getLocation();
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

   public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

       SwordPirate swordDude = new SwordPirate(getMapTile(10, 11).getLocation().subtractY(25),getMapTile(18, 10).getLocation().subtractY(25), Direction.LEFT);
       //getMapTile(16, 10).getLocation().subtractY(25)
       enemies.add(swordDude);

        RangeEnemy rangedPirate = new RangeEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
        enemies.add(rangedPirate);

        Krabs krabsEnemy = new Krabs(getMapTile(13, 6).getLocation().subtractY(25), Direction.RIGHT);
        enemies.add(krabsEnemy);

        //Krabs krabsEnemy = new Krabs(getMapTile(27, 6).getLocation().subtractY(25), Direction.LEFT);
       // enemies.add(krabsEnemy);


        //DinosaurEnemy SwordPirate = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
       // enemies.add(SwordPirate);

        return enemies;
    }

@Override
public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

   /*  HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
            ImageLoader.load("GreenPlatform.png"),
            getMapTile(24, 6).getLocation(),
            getMapTile(27, 6).getLocation(),
            TileType.JUMP_THROUGH_PLATFORM,
            3,
            new Rectangle(0, 6,16,4),
            Direction.RIGHT
    );
    
    enhancedMapTiles.add(hmp);
*/
    EndLevelBox endLevelBox = new EndLevelBox(getMapTile(48, 8).getLocation());
    enhancedMapTiles.add(endLevelBox);

    return enhancedMapTiles;
} 

//@Override
public ArrayList<NPC> loadNPCs() {
    ArrayList<NPC> npcs = new ArrayList<>();
    
    //Chest chest = new Chest(getMapTile(24, 8).getLocation().subtractY(13));
    //npcs.add(chest);
    
    return npcs;
    
    }
        
}

