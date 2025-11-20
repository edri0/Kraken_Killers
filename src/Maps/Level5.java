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
import Enemies.Squidenemy;
import Enemies.SwordPirate;
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
        // SQUID 1
        // -------------------------
        Squidenemy squid1 = new Squidenemy(
                getMapTile(14, 18).getLocation().addY(2),
                getMapTile(12, 18).getLocation().addY(2),
                Direction.RIGHT
        );
        enemies.add(squid1);
    
    
        // -------------------------
        // SQUID 2
        // -------------------------
        Squidenemy squid2 = new Squidenemy(
                getMapTile(39, 18).getLocation().addY(2),
                getMapTile(37, 17).getLocation().addY(2),
                Direction.LEFT
        );
        enemies.add(squid2);
    
    
        // -------------------------
        // SQUID 3
        // -------------------------
        Squidenemy squid3 = new Squidenemy(
                getMapTile(47, 18).getLocation().addY(2),
                getMapTile(49, 18).getLocation().addY(2),
                Direction.RIGHT
        );
     // Sword Pirate
     SwordPirate swordDude = new SwordPirate(
             getMapTile(45, 10).getLocation().subtractY(25),
             getMapTile(46, 10).getLocation().subtractY(25),
             Direction.LEFT
     );     enemies.add(swordDude);
          // Sword Pirate
          SwordPirate swordDude2 = new SwordPirate(
            getMapTile(19, 10).getLocation().subtractY(25),
            getMapTile(20, 10).getLocation().subtractY(25),
            Direction.LEFT
    );
    enemies.add(swordDude2);

              // Sword Pirate
              SwordPirate swordDude3 = new SwordPirate(
                getMapTile(7, 12).getLocation().subtractY(25),
                getMapTile(5, 12).getLocation().subtractY(25),
                Direction.LEFT
        );
        enemies.add(swordDude3);

                  // Sword Pirate
                  SwordPirate swordDude4 = new SwordPirate(
                    getMapTile(35, 12).getLocation().subtractY(25),
                    getMapTile(23, 12).getLocation().subtractY(25),
                    Direction.LEFT
            );
            enemies.add(swordDude4);

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

