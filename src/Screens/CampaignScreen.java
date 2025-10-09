package Screens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Players.JackSparrow;
import Players.WillTurner;
import Utils.Point;

// This class is for when the platformer game is actually being played
public class CampaignScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected CampaignScreenState campaignScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;
    
 
    private int levelIndex = 0;
    private static final String SAVE_FILE = "campaign_level_save.txt";




    public CampaignScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        if (new File(SAVE_FILE).exists()) {
            loadProgress();
        } else {
            levelIndex = 0;
        }
        

        this.map = loadMapForIndex(levelIndex);

        // define/setup map
        this.map = new TestMap();

        // setup player
        this.player = new JackSparrow(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);

        if (ScreenCoordinator.selectedPlayer.equals("JackSparrow")) {
            this.player = new JackSparrow(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y); 
        }
        else if (ScreenCoordinator.selectedPlayer.equals("WillTurner")){
            this.player = new WillTurner(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y); 
        }

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.campaignScreenState = CampaignScreenState.RUNNING;
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (campaignScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                if (levelCompletedStateChangeStart) {
                    screenTimer = 130;
                    levelCompletedStateChangeStart = false;
                } else {
                    levelClearedScreen.update();
                    screenTimer--;
                    if (screenTimer == 0) {
                        goBackToMenu();
                    }
                }
                break;
            // wait on level lose screen to make a decision (either resets level or sends player back to main menu)
            case LEVEL_LOSE:
                levelLoseScreen.update();
                break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (campaignScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

    public CampaignScreenState getPlayLevelScreenState() {
        return campaignScreenState;
    }

    @Override
    public void onLevelCompleted() {
        if (campaignScreenState != CampaignScreenState.LEVEL_COMPLETED) {
            campaignScreenState = CampaignScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;

            levelIndex++;
            saveProgress();

        }
    }

    @Override
    public void onDeath() {
        if (campaignScreenState != CampaignScreenState.LEVEL_LOSE) {
            campaignScreenState = CampaignScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        this.map = loadMapForIndex(levelIndex);
        this.player = new JackSparrow(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);

        campaignScreenState = CampaignScreenState.RUNNING;
        levelCompletedStateChangeStart = false;
    }

    public void goBackToMenu() {
        saveProgress();
        screenCoordinator.setGameState(GameState.MENU);
    }
    

    private void saveProgress(){
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            writer.write("level=" + levelIndex + "\n");

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void loadProgress(){
        try(BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))){
            String line = reader.readLine();
            if (line != null && line.startsWith("level=")) {
                levelIndex = Integer.parseInt(line.split("=")[1]);
            } else{
                levelIndex = 0;
            }

        } catch (Exception e) {
            levelIndex = 0;
        }
    }
    private void clearProgress(){
        try {
            new File(SAVE_FILE).delete();

        } catch (Exception ignored) {}
    }
    private Map loadMapForIndex(int idx){
        return new TestMap();
    }
    // This enum represents the different states this screen can be in
    private enum CampaignScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
    }
}
