/*package Screens;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Engine.ScreenManager;
import SpriteFont.SpriteFont;
import java.awt.*;
import java.awt.image.BufferedImage;

// Simple automatic Level Cleared screen — no keypress needed
public class EndLevelScreen extends Screen {

    private SpriteFont endMessage;
    protected BufferedImage EndLevelImage;
    protected Boolean hasInitialized = false;

    public EndLevelScreen() {
        initialize();
        hasInitialized = true;
        EndLevelImage = ImageLoader.load("EndLevel.png");
    }

    @Override
    public void initialize() {
      endMessage = new SpriteFont("", 110, 200, "Black Goth", 25, Color.WHITE);
    }

    @Override
    public void update() {
        // Nothing needed — CampaignScreen handles timing and transitions
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        /*graphicsHandler.drawFilledRectangle(
            0, 0,
            ScreenManager.getScreenWidth(),
            ScreenManager.getScreenHeight(),
            Color.BLACK
            //800w 600h
        );

       graphicsHandler.drawImage(EndLevelImage, 0, 0, 800, 600); 

        endMessage.draw(graphicsHandler);
    }

    public Boolean hasInitialized(){
        return hasInitialized;
    }
}
*/