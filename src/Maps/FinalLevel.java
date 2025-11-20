
package Maps;

import EnhancedMapTiles.EndLevelBox;
//import EnhancedMapTiles.HorizontalMovingPlatform;
import Level.*;
import NPCs.Chest;
import Tilesets.L8CommmonTileset;
import Utils.Direction;

//import Utils.Direction;
import java.util.ArrayList;

import Enemies.KrakenHead;
import Enemies.Leg1;
import Enemies.Leg2;
import Enemies.Leg3;
import Enemies.Leg4;

//import Engine.ImageLoader;
import Engine.SoundPlayer;
//import GameObject.Rectangle;


public class FinalLevel extends Map{

    public FinalLevel(){
        super("FinalLevel.txt", new L8CommmonTileset());
        this.playerStartPosition = getMapTile(9, 10).getLocation();
        SoundPlayer.stopMusic(); 
    
    }
    

@Override
public ArrayList<Enemy> loadEnemies() {
    ArrayList<Enemy> enemies = new ArrayList<>();

   // BugEnemy bugEnemy = new BugEnemy(getMapTile(16, 10).getLocation().subtractY(25), Direction.LEFT);
   // enemies.add(bugEnemy);

   // DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
   // enemies.add(dinosaurEnemy);

   Leg1 leg1 = new Leg1(getMapTile(12, 12).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
   enemies.add(leg1);
   Leg2 leg2 = new Leg2(getMapTile(15, 12).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
   enemies.add(leg2);
   Leg3 leg3 = new Leg3(getMapTile(23, 12).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.LEFT);
   enemies.add(leg3);
   Leg4 leg4 = new Leg4(getMapTile(20, 12).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.LEFT);
   enemies.add(leg4);
   KrakenHead krakenHead = new KrakenHead(getMapTile(31, 14).getLocation().addY(2), getMapTile(31, 15).getLocation().addY(2), Direction.LEFT);
   enemies.add(krakenHead);

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
   

EndLevelBox endLevelBox = new EndLevelBox(getMapTile(39, 15).getLocation());
enhancedMapTiles.add(endLevelBox);

return enhancedMapTiles;
} 

@Override
public ArrayList<NPC> loadNPCs() {
ArrayList<NPC> npcs = new ArrayList<>();

Chest chest = new Chest(getMapTile(4, 15).getLocation().subtractY(13));
npcs.add(chest);

return npcs;

}

}

