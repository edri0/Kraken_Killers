package Screens;

import java.io.*;
import java.awt.*;
import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.ScreenManager;
import Engine.Key;
import Engine.Keyboard;
import Game.GameState;
import Game.ScreenCoordinator;
import Inventory.Armor;
import Inventory.PlayerInventory;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Maps.Level2;
import Maps.Level3;
import Maps.Level4;
import Maps.Level5;
import Maps.Level6;
import Maps.Level7;
import Maps.FinalLevel;
import Players.JackSparrow;
import Players.WillTurner;
import Utils.Point;
import UI.HealthBar;

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

    private int levelIndex = 0;
    private static final String SAVE_FILE = "campaign_level_save.txt";
    private static final int FINAL_LEVEL_INDEX = 7;
    private EndLevelScreen endLevelScreen;
    private boolean endGameActive = false;
    private int endGameTimer = 0;

    public CampaignScreen(ScreenCoordinator screenCoordinator, PlayerInventory inventory, PickPlayerScreen pickPlayerScreen) {
        this.pickPlayerScreen = pickPlayerScreen; 
        this.screenCoordinator = screenCoordinator;
        this.playerInventory = inventory;
    }

    public void initialize() {
        if (new File(SAVE_FILE).exists()) loadProgress();
        else levelIndex = 0;

        this.map = loadMapForIndex(levelIndex);
        Point startPos = map.getPlayerStartPosition();

        int selectedPlayerType = (pickPlayerScreen != null) ? pickPlayerScreen.getSelectedPlayer() : 0;

        if (this.player == null) {
            this.player = (selectedPlayerType == 0) ? new JackSparrow(startPos.x, startPos.y)
                                                    : new WillTurner(startPos.x, startPos.y);
        } else {
            this.player.setMap(map);
            this.player.setX(startPos.x);
            this.player.setY(startPos.y);
        }

        this.player.setMap(map);
        this.player.addListener(this);
        player.getArmorTimer().stop();
        player.getHealthBar().update();

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);
        endLevelScreen = new EndLevelScreen();

        if (this.shopScreen == null) {
            this.shopScreen = new ShopScreen(playerInventory, player);
            this.shopScreen.initialize();
        } else this.shopScreen.setPlayer(this.player);

        campaignScreenState = CampaignScreenState.RUNNING;

        System.out.println("Level Index: " + levelIndex);
        System.out.println("Selected Player: " + selectedPlayerType);
        System.out.println("Map Class: " + map.getClass().getSimpleName());
        System.out.println("Player Start Position: " + startPos.x + "," + startPos.y);
    }

    public void update() {
        // Handle final level screen
        if (endGameActive) {
            endGameTimer--;
            if (endGameTimer <= 0) {
                clearProgress();
                screenCoordinator.setGameState(GameState.MENU);
                endGameActive = false;
            }
            return;
        }

        switch (campaignScreenState) {
            case RUNNING:
                boolean sDown = Keyboard.isKeyDown(Key.S);
                if (sDown && !sToggleLock) {
                    sToggleLock = true;
                    campaignScreenState = CampaignScreenState.SHOP;
                }
                if (!sDown) sToggleLock = false;

                player.update();
                map.update(player);
                player.getHealthBar().update();
                break;

            case SHOP:
                shopScreen.update();
                sDown = Keyboard.isKeyDown(Key.S);
                if (sDown && !sToggleLock) {
                    sToggleLock = true;
                    Armor equipped = playerInventory.getEquippedArmor();
                    if (equipped != null && player.getEquippedArmor() != equipped) equipped.equip(player);
                    campaignScreenState = CampaignScreenState.RUNNING;
                }
                if (!sDown) sToggleLock = false;
                break;

            case LEVEL_COMPLETED:
                if (levelCompletedStateChangeStart) {
                    screenTimer = 130;
                    levelCompletedStateChangeStart = false;
                } else {
                    levelClearedScreen.update();
                    screenTimer--;
                    if (screenTimer <= 0) {
                        if (levelIndex == FINAL_LEVEL_INDEX) {
                            triggerEndGameScreen();
                            return;
                        }
                        levelIndex++;
                        saveProgress();
                        Map nextMap = loadMapForIndex(levelIndex);
                        if (nextMap == null) { screenCoordinator.setGameState(GameState.MENU); return; }

                        this.map = nextMap;
                        Point levelStartPos = map.getPlayerStartPosition();
                        int selectedPlayerType = (pickPlayerScreen != null) ? pickPlayerScreen.getSelectedPlayer() : 0;

                        this.player = (selectedPlayerType == 0) ? new JackSparrow(levelStartPos.x, levelStartPos.y)
                                                                : new WillTurner(levelStartPos.x, levelStartPos.y);
                        this.player.setMap(map); 
                        this.player.addListener(this);
                        reapplyArmor();
                        this.shopScreen.setPlayer(this.player);
                        campaignScreenState = CampaignScreenState.RUNNING;
                    }
                }
                break;

            case LEVEL_LOSE:
                levelLoseScreen.update();
                break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        switch (campaignScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                player.getHealthBar().draw(graphicsHandler);
                String money = PlayerInventory.fmt(playerInventory.getMoneyCents());
                int w = ScreenManager.getScreenWidth();
                graphicsHandler.drawFilledRectangle(w - 170, 10, 160, 30, new Color(22,29,44,200));
                graphicsHandler.drawString(money, w - 160,32, new Font("Arial", Font.BOLD, 18), Color.WHITE);
                break;
            case SHOP:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                shopScreen.draw(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }

        if (endGameActive) endLevelScreen.draw(graphicsHandler);
    }

    public CampaignScreenState getPlayLevelScreenState() { return campaignScreenState; }

    @Override
    public void onLevelCompleted() {
        if (campaignScreenState != CampaignScreenState.LEVEL_COMPLETED) {
            campaignScreenState = CampaignScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (campaignScreenState != CampaignScreenState.LEVEL_LOSE) campaignScreenState = CampaignScreenState.LEVEL_LOSE;
    }

    public void resetLevel() {
        this.map = loadMapForIndex(levelIndex);
        Point resetStartPos = map.getPlayerStartPosition(); 
        int selectedPlayerType = (pickPlayerScreen != null) ? pickPlayerScreen.getSelectedPlayer() : 0;

        this.player = (selectedPlayerType == 0) ? new JackSparrow(resetStartPos.x, resetStartPos.y)
                                                : new WillTurner(resetStartPos.x, resetStartPos.y);
        this.player.setMap(map);
        this.player.addListener(this);
        reapplyArmor();

        this.shopScreen = new ShopScreen(playerInventory, player);
        this.shopScreen.initialize();
        player.getHealthBar().update();

        campaignScreenState = CampaignScreenState.RUNNING;
        levelCompletedStateChangeStart = false;
    }

    public void goBackToMenu() { saveProgress(); screenCoordinator.setGameState(GameState.MENU); }

    private void saveProgress() {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) { writer.write("level=" + levelIndex + "\n"); } 
        catch (IOException e) { e.printStackTrace(); }
    }

    private void loadProgress() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line = reader.readLine();
            levelIndex = (line != null && line.startsWith("level=")) ? Integer.parseInt(line.split("=")[1]) : 0;
        } catch (Exception e) { levelIndex = 0; }
    }

    private void clearProgress() { try { new File(SAVE_FILE).delete(); } catch (Exception ignored) {} }

    private Map loadMapForIndex(int idx) {
        switch (idx) {
            case 0: return new TestMap();
            case 1: return new Level2();
            case 2: return new Level3();
            case 3: return new Level4();
            case 4: return new Level5();
            case 5: return new Level6();
            case 6: return new Level7();
            case 7: return new FinalLevel();
        }
        return null;
    }

    private void triggerEndGameScreen() {
        endLevelScreen = new EndLevelScreen();
        endGameActive = true;
        endGameTimer = 180;  // adjust duration
    }

    private void reapplyArmor() {
        Armor armor = playerInventory.getEquippedArmor();
        if (armor != null) armor.equip(this.player);
    }

    private enum CampaignScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE, SHOP
    }
}
