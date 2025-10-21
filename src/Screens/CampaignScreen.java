package Screens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.ScreenManager;
import Engine.Key;
import Engine.Keyboard;
import Game.GameState;
import Game.ScreenCoordinator;
import Inventory.PlayerInventory;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Players.JackSparrow;
import Players.WillTurner;
import Utils.Point;
import UI.HealthBar;
//import Utils.Point;
import Maps.Level2;
import Maps.Level3;
import Maps.Level4;
import Maps.Level5;



// This class is for when the platformer game is actually being played
public class CampaignScreen extends Screen implements PlayerListener {
    protected final ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected CampaignScreenState campaignScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;

    private PickPlayerScreen pickPlayerScreen; 
 
    private final PlayerInventory playerInventory;
    private ShopScreen shopScreen;
    private boolean sToggleLock = false;
    private HealthBar healthBar;


    private int levelIndex = 0;
    private static final String SAVE_FILE = "campaign_level_save.txt";



    public CampaignScreen(ScreenCoordinator screenCoordinator, PlayerInventory inventory, PickPlayerScreen pickPlayerScreen) {

        this.pickPlayerScreen = pickPlayerScreen; 
        this.screenCoordinator = screenCoordinator;
        this.playerInventory = inventory;
    }

    public void initialize() {
        if (new File(SAVE_FILE).exists()) {
            loadProgress();
        } else {
            levelIndex = 0;
        }
        

        this.map = loadMapForIndex(levelIndex);

        // define/setup map
        Point startPos = map.getPlayerStartPosition(); 
        
        int selectedPlayerType = 0; 

        if(pickPlayerScreen != null){
            selectedPlayerType = pickPlayerScreen.getSelectedPlayer();
        }
        if(selectedPlayerType ==0){
            this.player = new JackSparrow(startPos.x, startPos.y);
        }else {
            this.player = new WillTurner(startPos.x, startPos.y);
        }

        this.player.setMap(map);
        this.player.addListener(this);

        this.healthBar = new HealthBar(player); 


        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);


        this.shopScreen = new ShopScreen(playerInventory, player);
        this.shopScreen.initialize();
        

        this.campaignScreenState = CampaignScreenState.RUNNING;

    }

    //Campaign
    public void update() {
        
        // based on screen state, perform specific actions
        switch (campaignScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                boolean sDown = Keyboard.isKeyDown(Key.S);
                if( sDown && !sToggleLock){
                    sToggleLock = true;
                    campaignScreenState = CampaignScreenState.SHOP;
                }
                if(!sDown) sToggleLock = false;

                player.update();
                map.update(player);
                healthBar.update();
                break;
            case SHOP: {
                shopScreen.update();

                sDown = Keyboard.isKeyDown(Key.S);
                if(sDown && !sToggleLock){
                    sToggleLock = true;
                    campaignScreenState = CampaignScreenState.RUNNING;
                }
                if (!sDown) sToggleLock = false;
                break;
            }
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
            if (levelCompletedStateChangeStart) {
                screenTimer = 130;
                levelCompletedStateChangeStart = false;
            } else {
                levelClearedScreen.update();
                screenTimer--;
                if (screenTimer <= 0) {
                    levelIndex++;
                    saveProgress();
                    Point levelStartPos = map.getPlayerStartPosition();
                    int selectedPlayerType = pickPlayerScreen.getSelectedPlayer(); 
                    if(selectedPlayerType == 0){
                        this.player = new JackSparrow(levelStartPos.x, levelStartPos.y);
                    }
                    else {
                        this.player = new WillTurner(levelStartPos.x, levelStartPos.y);
                    }
                    this.player.setMap(map); 
                    this.player.addListener(this); 
                    this.campaignScreenState = CampaignScreenState.RUNNING;

                    // Load next level dynamically
                    Map nextMap = loadMapForIndex(levelIndex);
                    if (nextMap == null) {
                        // No more levels — reset to menu or end of campaign
                        screenCoordinator.setGameState(GameState.MENU);
                        return;
                    }

                    this.map = nextMap;
                    this.player.setMap(map);
                    this.player.addListener(this);
                    this.campaignScreenState = CampaignScreenState.RUNNING;
                    }
                }
                if (levelCompletedStateChangeStart) {
                    screenTimer = 130;
                    levelCompletedStateChangeStart = false;
                    map = new Level2();
                    Point levelStartPos = map.getPlayerStartPosition(); 
                    int selectedPlayerType = pickPlayerScreen.getSelectedPlayer(); 
                    if(selectedPlayerType == 0){
                        this.player = new JackSparrow(levelStartPos.x, levelStartPos.y);
                    }
                    else {
                        this.player = new WillTurner(levelStartPos.x, levelStartPos.y);
                    }
                    this.player.setMap(map); 
                    this.player.addListener(this); 
                    this.campaignScreenState = CampaignScreenState.RUNNING;

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
                healthBar.draw(graphicsHandler);

                String money = PlayerInventory.fmt(playerInventory.getMoneyCents());
                int w = ScreenManager.getScreenWidth();
                graphicsHandler.drawFilledRectangle(w - 170, 10, 160, 30, new Color(22,29,44,200));
                graphicsHandler.drawString(money, w - 160,32, new Font("Arial", Font.BOLD, 18), Color.WHITE);
                break;
            case SHOP:{
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                shopScreen.draw(graphicsHandler);
                break;
            }
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
        // DON'T increment levelIndex here
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
        Point resetStartPos = map.getPlayerStartPosition(); 
        int selectedPlayerType = pickPlayerScreen.getSelectedPlayer();
        if(selectedPlayerType == 0){
            this.player = new JackSparrow(resetStartPos.x, resetStartPos.y);
        }
        else {
            this.player = new WillTurner(resetStartPos.x, resetStartPos.y); 
        }
        this.player.setMap(map);
        this.player.addListener(this);
        this.healthBar = new HealthBar(player); 

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
    private Map loadMapForIndex(int idx) {
        switch (idx) {
            case 0: return new TestMap();
            case 1: return new Level2();
            case 2: return new Level3();
            case 3: return new Level4(); 
            case 4: return new Level5(); 
            default: return null; // no more levels yet
        }
    }
    
    // This enum represents the different states this screen can be in
    private enum CampaignScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE, SHOP
    }
}
