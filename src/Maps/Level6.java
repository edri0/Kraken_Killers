
package Maps;

import EnhancedMapTiles.EndLevelBox;
//import EnhancedMapTiles.HorizontalMovingPlatform;
import Level.*;
import NPCs.Chest;
import Tilesets.CommonTileset;
//import Utils.Direction;
import java.util.ArrayList;
import Utils.Direction;
import Enemies.Fitz;
import Enemies.RangeEnemy;
import Enemies.SwordPirate;
//import Engine.ImageLoader;
import Engine.SoundPlayer;
//import GameObject.Rectangle;


public class Level6 extends Map{

    public Level6(){
        super("level6.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(4, 9).getLocation();
        SoundPlayer.stopMusic(); 
    }
    

@Override
public ArrayList<Enemy> loadEnemies() {
    ArrayList<Enemy> enemies = new ArrayList<>();

    // --- Sword Pirate ---
    // Patrols from (16,10) → (18,10)
    SwordPirate swordPirate = new SwordPirate(
            getMapTile(9, 13).getLocation().subtractY(25),
            getMapTile(8, 12).getLocation().subtractY(25),
            Direction.LEFT
    );
    enemies.add(swordPirate);

    

    // --- Ranged Pirate ---
    // Patrols from (19,1) → (22,1)
    RangeEnemy rangedPirate = new RangeEnemy(
            getMapTile(33, 17).getLocation().addY(2),
            getMapTile(34, 17).getLocation().addY(2),
            Direction.RIGHT
    );
    enemies.add(rangedPirate);

    // --- Fitz Enemy ---
    // Patrols from (30,5) → (35,5)
    Fitz fitz = new Fitz(
            getMapTile(37, 14).getLocation().addY(2),
            getMapTile(35, 14).getLocation().addY(2),
            Direction.RIGHT
    );
    enemies.add(fitz);

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
   

EndLevelBox endLevelBox = new EndLevelBox(getMapTile(39, 14).getLocation());
enhancedMapTiles.add(endLevelBox);

return enhancedMapTiles;
} 

//@Override
public ArrayList<NPC> loadNPCs() {
    ArrayList<NPC> npcs = new ArrayList<>();
    
   // Chest chest = new Chest(getMapTile(16, 17).getLocation().subtractY(13));
   // npcs.add(chest);
    
    return npcs;
    
    }

}


