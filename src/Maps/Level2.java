package Maps;

import EnhancedMapTiles.EndLevelBox;
import Level.*;
import NPCs.Chest;
import Tilesets.CommonTileset;
import Utils.Direction;
import Engine.SoundPlayer;

import java.util.ArrayList;

import Enemies.RangeEnemy;
import Enemies.Squidenemy;
import Enemies.SwordPirate;

public class Level2 extends Map {

    public Level2() {
        super("level2.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 11).getLocation(); // starting tile
        SoundPlayer.stopMusic();
    }

    @Override
    public void mapEntity(Coin c) {
        // Level 2 has no coins
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        // Sword Pirates
        enemies.add(new SwordPirate(getMapTile(19, 16).getLocation().subtractY(25),
                getMapTile(20, 16).getLocation().subtractY(25), Direction.RIGHT));
        enemies.add(new SwordPirate(getMapTile(5, 13).getLocation().subtractY(25),
                getMapTile(5, 14).getLocation().subtractY(25), Direction.RIGHT));

        // Ranged Pirates
        enemies.add(new RangeEnemy(getMapTile(23, 16).getLocation().addY(2),
                getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new RangeEnemy(getMapTile(41, 18).getLocation().addY(2),
                getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT));

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(49, 19).getLocation());
        enhancedMapTiles.add(endLevelBox);
        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        return new ArrayList<>();
    }
}
