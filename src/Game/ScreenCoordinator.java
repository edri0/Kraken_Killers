package Game;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.SoundPlayer;
import Inventory.PlayerInventory;
import Screens.ArcadeScreen;
import Screens.MenuScreen;
import Screens.CampaignScreen;
import Screens.PickPlayerScreen;
import Screens.ControlsScreen;

/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {

    private PickPlayerScreen pickPlayerScreen;
    // Global selected player (default to JackSparrow)

    // Currently shown Screen
    protected Screen currentScreen = new DefaultScreen();

    // Keep track of gameState so ScreenCoordinator knows which Screen to show
    protected GameState gameState;
    protected GameState previousGameState;

    private final PlayerInventory sharedInventory = new PlayerInventory();

    public GameState getGameState() {
        return gameState;
    }

    // Other Screens can set the gameState of this class to force it to change the currentScreen
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void initialize() {
        // start game off with Menu Screen
        gameState = GameState.MENU;

        // Load from JAR root
        SoundPlayer.preloadSounds("POC.wav", "walking.wav", "jump.wav", "swords.wav");
    }


    @Override
    public void update() {
        do {
            // If previousGameState does not equal gameState, it means there was a change in gameState
            // This triggers ScreenCoordinator to bring up a new Screen based on what the gameState is
            if (previousGameState != gameState) {
                switch(gameState) {
                    case MENU:
                        currentScreen = new MenuScreen(this);
                        break;
                    case PLAYER:
                        pickPlayerScreen = new PickPlayerScreen(this);
                        currentScreen = pickPlayerScreen;
                        break;
                    case LEVEL:
                        currentScreen = new CampaignScreen(this, sharedInventory, pickPlayerScreen);
                        break;
                    case ARCADE:
                        currentScreen = new ArcadeScreen(this, sharedInventory, pickPlayerScreen);
                        break;
                    case CONTROLS:
                        currentScreen = new ControlsScreen(this);
                        break;
                }
                currentScreen.initialize();
            }
            previousGameState = gameState;

            // Call the update method for the currentScreen
            currentScreen.update();
        } while (previousGameState != gameState);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        // Call the draw method for the currentScreen
        currentScreen.draw(graphicsHandler);
    }
}

