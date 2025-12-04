package Maps;

import Enemies.*;
import Engine.SoundPlayer;
import EnhancedMapTiles.EndLevelBox;
import Level.*;
//import NPCs.NPC;
import Tilesets.CommonTileset;
import Utils.Direction;

import java.util.ArrayList;

public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(1, 5).getLocation();
        SoundPlayer.stopMusic();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        // Sword Pirates
        SwordPirate swordPirate1 = new SwordPirate(getMapTile(10, 10).getLocation().subtractY(25),
                getMapTile(11, 10).getLocation().subtractY(25), Direction.LEFT);
        SwordPirate swordPirate2 = new SwordPirate(getMapTile(14, 8).getLocation().subtractY(25),
                getMapTile(14, 9).getLocation().subtractY(25), Direction.RIGHT);
        SwordPirate swordPirate3 = new SwordPirate(getMapTile(19, 11).getLocation().subtractY(25),
                getMapTile(19, 11).getLocation().subtractY(25), Direction.LEFT);

        // Ranged Pirate
        RangeEnemy rangedPirate = new RangeEnemy(getMapTile(29, 11).getLocation().addY(2),
                getMapTile(30, 11).getLocation().addY(2), Direction.RIGHT);

        enemies.add(swordPirate1);
        enemies.add(swordPirate2);
        enemies.add(swordPirate3);
        enemies.add(rangedPirate);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(33, 11).getLocation());
        enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    }

//    @Override
//    public ArrayList<NPC> loadNPCs() {
//        return new ArrayList<>(); // No NPCs for TestMap
//    }

    @Override
    public void mapEntity(Coin c) {
        // TestMap has no coins, leave empty
    }
}
