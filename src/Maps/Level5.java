package Maps;

import EnhancedMapTiles.EndLevelBox;
// import EnhancedMapTiles.HorizontalMovingPlatform;
import Level.*;
import NPCs.Chest;
import Tilesets.CommonTileset5;
import Utils.Direction;

//import Utils.Direction;
import java.util.ArrayList;
//import Engine.ImageLoader;
//import GameObject.Rectangle;

import Enemies.DinosaurEnemy;
import Enemies.RangeEnemy;
import Engine.SoundPlayer;


public class Level5 extends Map{

    public Level5(){
        super("level5.txt", new CommonTileset5());
        this.playerStartPosition = getMapTile(4, 9).getLocation();
        SoundPlayer.stopMusic(); 
    }
    

@Override
public ArrayList<Enemy> loadEnemies() {
    ArrayList<Enemy> enemies = new ArrayList<>();

    // -------------------------
    // RANGED PIRATE #1
    // -------------------------
    RangeEnemy rangedPirate1 = new RangeEnemy(
            getMapTile(36, 15).getLocation().addY(2),
            getMapTile(37, 15).getLocation().addY(2),
            Direction.RIGHT
    );
    enemies.add(rangedPirate1);

    // -------------------------
    // RANGED PIRATE #2
    // -------------------------
    RangeEnemy rangedPirate2 = new RangeEnemy(
            getMapTile(32, 15).getLocation().addY(2),
            getMapTile(35, 15).getLocation().addY(2),
            Direction.LEFT
    );
    enemies.add(rangedPirate2);

    // -------------------------
    // RANGED PIRATE #3
    // -------------------------
    RangeEnemy rangedPirate3 = new RangeEnemy(
            getMapTile(40, 15).getLocation().addY(2),
            getMapTile(44, 15).getLocation().addY(2),
            Direction.RIGHT
    );
    enemies.add(rangedPirate3);

    return enemies;
}


@Override
public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
/* 
    HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
            ImageLoader.load("GreenPlatform.png"),
            getMapTile(40, 10).getLocation(),
            getMapTile(38, 10).getLocation(),
            TileType.JUMP_THROUGH_PLATFORM,
            3,
            new Rectangle(0, 6,16,4),
            Direction.RIGHT
    );
    
    enhancedMapTiles.add(hmp);
*/

   

    EndLevelBox endLevelBox = new EndLevelBox(getMapTile(49, 7).getLocation());
    enhancedMapTiles.add(endLevelBox);

    return enhancedMapTiles;
} 

//@Override

    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        
        //Chest chest = new Chest(getMapTile(35, 11).getLocation().subtractY(13));
        //npcs.add(chest);
        
        return npcs;
        
        }
    
}

