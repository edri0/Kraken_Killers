package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.ScreenManager;
import SpriteFont.SpriteFont;
import java.awt.*;

// Simple automatic Level Cleared screen — no keypress needed
public class EndLevelScreen extends Screen {

    private SpriteFont winMessage;

    public EndLevelScreen() {
        initialize();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("THIS IS EITHER MADNESS OR BRILLIANCE!", 300, 200, "Black Goth", 30, Color.red);
    }

    @Override
    public void update() {
        // Nothing needed — CampaignScreen handles timing and transitions
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(
            0, 0,
            ScreenManager.getScreenWidth(),
            ScreenManager.getScreenHeight(),
            Color.BLACK
            //800w 600h
        );

        winMessage.draw(graphicsHandler);
    }
}
