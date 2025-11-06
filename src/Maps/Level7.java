
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

       SwordPirate swordDude = new SwordPirate(getMapTile(16, 17).getLocation().subtractY(25),getMapTile(17, 17).getLocation().subtractY(25), Direction.LEFT);
       //getMapTile(16, 10).getLocation().subtractY(25)
       enemies.add(swordDude);

        RangeEnemy rangedPirate = new RangeEnemy(getMapTile(36, 15).getLocation().addY(2), getMapTile(37, 15).getLocation().addY(2), Direction.RIGHT);
        enemies.add(rangedPirate);

        Fitz fitzEnemy = new Fitz(getMapTile(30, 16).getLocation().addY(2), getMapTile(33, 16).getLocation().addY(2), Direction.RIGHT);
        enemies.add(fitzEnemy);

        //DinosaurEnemy SwordPirate = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
       // enemies.add(SwordPirate);

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


