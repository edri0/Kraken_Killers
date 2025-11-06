package Screens;

import Engine.*;
import SpriteFont.SpriteFont;
import java.awt.image.BufferedImage;
import java.awt.*;

// This is the class for the level lose screen
public class LevelLoseScreen extends Screen {
    protected Boolean hasInitialized = false;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected CampaignScreen campaignScreen;
    protected ArcadeScreen arcadeScreen;   
    
    protected SpriteFont loseMessage;
    protected BufferedImage levelLoseImage;

    public LevelLoseScreen(CampaignScreen campaignScreen) {
        this.campaignScreen = campaignScreen;
        this.arcadeScreen = null;
        initialize();
        initialize();
        hasInitialized = true;
        levelLoseImage = ImageLoader.load("LevelLose.png");
    }
    public LevelLoseScreen(ArcadeScreen arcadeScreen) {
        this.arcadeScreen = arcadeScreen;
        this.campaignScreen = null;
        initialize();
    }

    @Override
    public void initialize() {
        loseMessage = new SpriteFont("", 110, 200, "Black Goth", 25, Color.WHITE);
        instructions = new SpriteFont("Press Space to try again or Escape to go back to the main menu matey", 120, 279,"Arial", 20, Color.white);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            if(arcadeScreen != null){
                arcadeScreen.resetLevel();
            }else if (campaignScreen != null){
                campaignScreen.resetLevel();
            }
        } else if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)) {
            if(arcadeScreen != null){
                arcadeScreen.goBackToMenu();
            } else if (campaignScreen != null){
                campaignScreen.goBackToMenu();
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
       /*  graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        loseMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
        */

    graphicsHandler.drawImage(levelLoseImage, 0, 0, 800, 600); 

    loseMessage.draw(graphicsHandler);
}

public Boolean hasInitialized(){
    return hasInitialized;
}
}
