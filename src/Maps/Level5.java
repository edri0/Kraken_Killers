package Maps;

import EnhancedMapTiles.EndLevelBox;
// import EnhancedMapTiles.HorizontalMovingPlatform;
import Level.*;
import NPCs.Chest;
import Tilesets.CommonTileset;
//import Utils.Direction;
import java.util.ArrayList;
//import Engine.ImageLoader;
//import GameObject.Rectangle;

import Engine.SoundPlayer;


public class Level5 extends Map{

    public Level5(){
        super("level5.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(4, 9).getLocation();
        SoundPlayer.stopMusic(); 
    }
    

@Override
public ArrayList<Enemy> loadEnemies() {
    ArrayList<Enemy> enemies = new ArrayList<>();

   // BugEnemy bugEnemy = new BugEnemy(getMapTile(16, 10).getLocation().subtractY(25), Direction.LEFT);
   // enemies.add(bugEnemy);

   // DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
   // enemies.add(dinosaurEnemy);


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

