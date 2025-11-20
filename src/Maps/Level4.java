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

import Enemies.RangeEnemy;
import Enemies.SwordPirate;
import Engine.SoundPlayer;
import Enemies.Fitz;
import Enemies.Krabs;

public class Level4 extends Map{

    public Level4(){
        super("level4.txt", new CommonTileset5());
        this.playerStartPosition = getMapTile(3, 5).getLocation();
        SoundPlayer.stopMusic(); 
    }
    

@Override
public ArrayList<Enemy> loadEnemies() {
    ArrayList<Enemy> enemies = new ArrayList<>();

    // // ---------------------------
    // // Sword Pirate
    // // ---------------------------
    // SwordPirate swordDude = new SwordPirate(
    //         getMapTile(10, 11).getLocation().subtractY(25),
    //         getMapTile(18, 10).getLocation().subtractY(25),
    //         Direction.LEFT
    // );
    // enemies.add(swordDude);


    // // ---------------------------
    // // Ranged Pirate
    // // ---------------------------
    // RangeEnemy rangedPirate = new RangeEnemy(
    //         getMapTile(19, 1).getLocation().addY(2),
    //         getMapTile(22, 1).getLocation().addY(2),
    //         Direction.RIGHT
    // );
    // enemies.add(rangedPirate);


    // ---------------------------
    // KRABS 1
    // ---------------------------
    Krabs krabs1 = new Krabs(
            getMapTile(4, 10).getLocation().subtractY(25),
            Direction.RIGHT
    );
    enemies.add(krabs1);


    // ---------------------------
    // KRABS 2
    // ---------------------------
    Krabs krabs2 = new Krabs(
            getMapTile(21, 10).getLocation().subtractY(25),
            Direction.LEFT
    );
    enemies.add(krabs2);


    // ---------------------------
    // KRABS 3
    // ---------------------------
    Krabs krabs3 = new Krabs(
            getMapTile(3, 10).getLocation().subtractY(25),
            Direction.RIGHT
    );
    enemies.add(krabs3);


      Fitz fitzEnemy = new Fitz(getMapTile(10, 7).getLocation().addY(2), getMapTile(11, 7).getLocation().addY(2), Direction.RIGHT);
      enemies.add(fitzEnemy);

      Fitz fitzEnemy2 = new Fitz(getMapTile(14, 4).getLocation().addY(2), getMapTile(14, 6).getLocation().addY(2), Direction.RIGHT);
      enemies.add(fitzEnemy2);

      Fitz fitzEnemy3 = new Fitz(getMapTile(18, 8).getLocation().addY(2), getMapTile(20, 8).getLocation().addY(2), Direction.RIGHT);
      enemies.add(fitzEnemy3);

      Fitz fitzEnemy4 = new Fitz(getMapTile(34, 9).getLocation().addY(2), getMapTile(33, 9).getLocation().addY(2), Direction.RIGHT);
      enemies.add(fitzEnemy4);

      Fitz fitzEnemy5 = new Fitz(getMapTile(40, 9).getLocation().addY(2), getMapTile(42, 9).getLocation().addY(2), Direction.RIGHT);
      enemies.add(fitzEnemy5);
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

