package Maps;

import EnhancedMapTiles.EndLevelBox;
import Level.*;
import NPCs.Chest;
import Tilesets.CommonTileset;
import Utils.Direction;
import Engine.SoundPlayer;

import java.util.ArrayList;

import Enemies.RangeEnemy;
import Enemies.SwordPirate;

public class Level3 extends Map {

    public Level3() {
        super("level3.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 5).getLocation();
        SoundPlayer.stopMusic();
    }

    @Override
    public void mapEntity(Coin c) {
        // Level 3 has no coins
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(new SwordPirate(getMapTile(11, 17).getLocation().subtractY(25),
                getMapTile(18, 10).getLocation().subtractY(25), Direction.LEFT));
        enemies.add(new SwordPirate(getMapTile(14, 16).getLocation().subtractY(25),
                getMapTile(18, 10).getLocation().subtractY(25), Direction.LEFT));
        enemies.add(new SwordPirate(getMapTile(29, 15).getLocation().subtractY(25),
                getMapTile(18, 10).getLocation().subtractY(25), Direction.LEFT));

        enemies.add(new RangeEnemy(getMapTile(44, 12).getLocation().addY(2),
                getMapTile(44, 12).getLocation().addY(2), Direction.RIGHT));

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        enhancedMapTiles.add(new EndLevelBox(getMapTile(49, 15).getLocation()));
        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        return new ArrayList<>();
    }
}



