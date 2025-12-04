package Maps;

import EnhancedMapTiles.EndLevelBox;
import Level.*;
//import NPCs.NPC;
import Tilesets.CommonTileset;
import Utils.Direction;
import Enemies.Fitz;
import Enemies.RangeEnemy;
import Enemies.SwordPirate;
import Engine.SoundPlayer;

import java.util.ArrayList;

public class Level6 extends Map {

    public Level6() {
        super("level6.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(4, 9).getLocation();
        SoundPlayer.stopMusic();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        // --- Sword Pirate ---
        SwordPirate swordPirate = new SwordPirate(
                getMapTile(9, 13).getLocation().subtractY(25),
                getMapTile(8, 12).getLocation().subtractY(25),
                Direction.LEFT
        );
        enemies.add(swordPirate);

        // --- Ranged Pirate ---
        RangeEnemy rangedPirate = new RangeEnemy(
                getMapTile(33, 17).getLocation().addY(2),
                getMapTile(34, 17).getLocation().addY(2),
                Direction.RIGHT
        );
        enemies.add(rangedPirate);

        // --- Fitz Enemy ---
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

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(39, 14).getLocation());
        enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    }

//    @Override
//    public ArrayList<NPC> loadNPCs() {
//        return new ArrayList<>(); // No NPCs in Level6
//    }

    @Override
    public void mapEntity(Coin c) {
        // Level6 has no coins; leave empty
    }
}
