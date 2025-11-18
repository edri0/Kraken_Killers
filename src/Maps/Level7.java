
package Maps;

import EnhancedMapTiles.EndLevelBox;
 import EnhancedMapTiles.HorizontalMovingPlatform;
import Level.*;
//import NPCs.Chest;
import Tilesets.CommonTileset;
import Utils.Direction;
import java.util.ArrayList;

import Enemies.Fitz;
import Enemies.RangeEnemy;
import Enemies.SwordPirate;
import Engine.ImageLoader;
import Engine.SoundPlayer;
import GameObject.Rectangle;


public class Level7 extends Map{

    public Level7(){
        super("level7.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(4, 9).getLocation();
        SoundPlayer.stopMusic(); 
    }
    

 @Override
 public ArrayList<Enemy> loadEnemies() {
     ArrayList<Enemy> enemies = new ArrayList<>();
 
     // Sword Pirate
     SwordPirate swordDude = new SwordPirate(
             getMapTile(16, 17).getLocation().subtractY(25),
             getMapTile(17, 17).getLocation().subtractY(25),
             Direction.LEFT
     );
     enemies.add(swordDude);
 
     // Ranged Pirate #1
     RangeEnemy rangedPirate1 = new RangeEnemy(
             getMapTile(36, 15).getLocation().addY(2),
             getMapTile(37, 15).getLocation().addY(2),
             Direction.RIGHT
     );
     enemies.add(rangedPirate1);
 
     // Fitz
     Fitz fitzEnemy = new Fitz(
             getMapTile(30, 16).getLocation().addY(2),
             getMapTile(33, 16).getLocation().addY(2),
             Direction.RIGHT
     );
     enemies.add(fitzEnemy);
 
     // Ranged Pirate #2
     RangeEnemy rangedPirate2 = new RangeEnemy(
             getMapTile(32, 15).getLocation().addY(2),
             getMapTile(37, 15).getLocation().addY(2),
             Direction.RIGHT
     );
     enemies.add(rangedPirate2);
 
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

   

EndLevelBox endLevelBox = new EndLevelBox(getMapTile(39, 10).getLocation());
enhancedMapTiles.add(endLevelBox);

return enhancedMapTiles;
} 

//@Override
public ArrayList<NPC> loadNPCs() {
    ArrayList<NPC> npcs = new ArrayList<>();
    
   // Chest chest = new Chest(getMapTile(35, 11).getLocation().subtractY(13));
  //  npcs.add(chest);
    
    return npcs;
    
    }

}


