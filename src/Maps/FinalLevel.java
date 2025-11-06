
package Maps;

import EnhancedMapTiles.EndLevelBox;
//import EnhancedMapTiles.HorizontalMovingPlatform;
import Level.*;
import NPCs.Chest;
import Tilesets.L8CommonTileset;
//import Utils.Direction;
import java.util.ArrayList;
//import Engine.ImageLoader;
import Engine.SoundPlayer;
//import GameObject.Rectangle;


public class FinalLevel extends Map{

    public FinalLevel(){
        super("FinalLevel.txt", new L8CommonTileset());
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

    /*HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
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
   

EndLevelBox endLevelBox = new EndLevelBox(getMapTile(36, 7).getLocation());
enhancedMapTiles.add(endLevelBox);

return enhancedMapTiles;
} 

//@Override
public ArrayList<NPC> loadNPCs() {
ArrayList<NPC> npcs = new ArrayList<>();

Chest chest = new Chest(getMapTile(30, 7).getLocation().subtractY(13));
npcs.add(chest);

return npcs;

}

}

