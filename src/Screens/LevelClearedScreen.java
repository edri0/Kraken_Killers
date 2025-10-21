package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.ScreenManager;
import SpriteFont.SpriteFont;
import java.awt.*;

// Simple automatic Level Cleared screen — no keypress needed
public class LevelClearedScreen extends Screen {

    private SpriteFont winMessage;

    public LevelClearedScreen() {
        initialize();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("LEVEL CLEARED!", 320, 200, "Arial", 30, Color.WHITE);
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
        );
        winMessage.draw(graphicsHandler);
    }
}
