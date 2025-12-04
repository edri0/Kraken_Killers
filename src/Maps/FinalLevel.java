package Maps;

import EnhancedMapTiles.EndLevelBox;
import Level.*;
import NPCs.Chest;
import Tilesets.L8CommmonTileset;
import Utils.Direction;
import Enemies.*;
import Engine.SoundPlayer;
import java.util.ArrayList;

public class FinalLevel extends Map {

    public FinalLevel() {
        super("FinalLevel.txt", new L8CommmonTileset());
        this.playerStartPosition = getMapTile(9, 10).getLocation();
        SoundPlayer.stopMusic();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        Leg1 leg1 = new Leg1(getMapTile(12, 12).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
        Leg2 leg2 = new Leg2(getMapTile(15, 12).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
        Leg3 leg3 = new Leg3(getMapTile(23, 12).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.LEFT);
        Leg4 leg4 = new Leg4(getMapTile(20, 12).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.LEFT);
        KrakenHead krakenHead = new KrakenHead(getMapTile(31, 14).getLocation().addY(2), getMapTile(31, 15).getLocation().addY(2), Direction.LEFT);

        enemies.add(leg1);
        enemies.add(leg2);
        enemies.add(leg3);
        enemies.add(leg4);
        enemies.add(krakenHead);

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

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

    @Override
    public void mapEntity(Coin c) {
        // FinalLevel has no coins, leave empty
    }
}
