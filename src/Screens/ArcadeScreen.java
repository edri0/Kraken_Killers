package Screens;

import java.awt.Color;
import java.awt.Font;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import Inventory.PlayerInventory;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
//import Maps.Level2;
import Maps.TestMap;
import Players.Cat;
//import Utils.Point;

// This class is for when the platformer game is actually being played
public class ArcadeScreen extends Screen implements PlayerListener {
    protected final ScreenCoordinator screenCoordinator;
    private final PlayerInventory playerInventory;

    protected Map map;
    protected Player player;

    protected ArcadeScreenState arcadeScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;

    private ShopScreen shopScreen;
    private boolean sToggleLock = false;

    public ArcadeScreen(ScreenCoordinator screenCoordinator, PlayerInventory inventory) {
        this.screenCoordinator = screenCoordinator;
        this.playerInventory = inventory;
    }

    public void initialize() {
        // define/setup map
        this.map = new TestMap();

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.shopScreen = new ShopScreen(playerInventory);
        this.shopScreen.initialize();
            

        this.arcadeScreenState = ArcadeScreenState.RUNNING;
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (arcadeScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING: {
                boolean sDown = Keyboard.isKeyDown(Key.S);
                if (sDown && !sToggleLock){
                    sToggleLock = true;
                    arcadeScreenState = ArcadeScreenState.SHOP;
                    return;
                }
                if (!sDown) sToggleLock = false;
            
                player.update();
                map.update(player);
                break;
            }
            case SHOP: {
                shopScreen.update();

                boolean sDown = Keyboard.isKeyDown(Key.S);
                if (sDown && !sToggleLock) {
                    sToggleLock = true;
                    arcadeScreenState = ArcadeScreenState.RUNNING;
                    return;
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
                    if (screenTimer == 0) {
                        goBackToMenu();
                        //map = new Level2();
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
        switch (arcadeScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth() - 170, 10, 160, 30, new Color(22,29,44,200));
                graphicsHandler.drawString(PlayerInventory.fmt(playerInventory.getMoneyCents()), ScreenManager.getScreenWidth() - 160, 32, new Font("Arial", Font.BOLD, 18), Color.WHITE);

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
    }

    public ArcadeScreenState getArcadeScreenState() {
        return arcadeScreenState;
    }

    @Override
    public void onLevelCompleted() {
        if (arcadeScreenState != ArcadeScreenState.LEVEL_COMPLETED) {
            arcadeScreenState = ArcadeScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (arcadeScreenState != ArcadeScreenState.LEVEL_LOSE) {
            arcadeScreenState = ArcadeScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum ArcadeScreenState {
        RUNNING, SHOP, LEVEL_COMPLETED, LEVEL_LOSE
    }
}
