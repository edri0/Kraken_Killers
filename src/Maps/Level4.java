package Maps;

import EnhancedMapTiles.EndLevelBox;
import Level.*;
import NPCs.Chest;
import Tilesets.CommonTileset5;
import Utils.Direction;
import Engine.SoundPlayer;

import java.util.ArrayList;

import Enemies.RangeEnemy;
import Enemies.SwordPirate;
import Enemies.Fitz;
import Enemies.Krabs;

public class Level4 extends Map {

    public Level4() {
        super("level4.txt", new CommonTileset5());
        this.playerStartPosition = getMapTile(3, 5).getLocation();
        SoundPlayer.stopMusic();
    }

    @Override
    public void mapEntity(Coin c) {
        // No coin logic for this level
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

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
        // Fitz enemies
        // ---------------------------
        enemies.add(new Fitz(getMapTile(14, 4).getLocation().addY(2), getMapTile(14, 6).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new Fitz(getMapTile(18, 8).getLocation().addY(2), getMapTile(20, 8).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new Fitz(getMapTile(34, 9).getLocation().addY(2), getMapTile(33, 9).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new Fitz(getMapTile(40, 9).getLocation().addY(2), getMapTile(42, 9).getLocation().addY(2), Direction.RIGHT));

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(48, 8).getLocation());
        enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Example:
        // Chest chest = new Chest(getMapTile(24, 8).getLocation().subtractY(13));
        // npcs.add(chest);

        return npcs;
    }
}


