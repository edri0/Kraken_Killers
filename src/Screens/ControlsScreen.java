package Screens;

import java.awt.Color;
import java.awt.Font;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;

public class ControlsScreen extends Screen{
    protected KeyLocker keyLocker = new KeyLocker();
    private ScreenCoordinator screenCoordinator;
    protected Map background;

    public ControlsScreen(ScreenCoordinator screenCoordinator){
        this.screenCoordinator = screenCoordinator;
    }
    public void update(){
        background.update(null);
        //to go back to main menue press SPACE
        // if space is pressed, go back to main menu
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }
    @Override
    public void initialize() {
        background = new TitleScreenMap();
        background.setAdjustCamera(false);

        keyLocker.lockKey(Key.SPACE);

    }
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        //background
        background.draw(graphicsHandler);

        //title
        graphicsHandler.drawString("How To Play", 267,100, new Font("Times New Roman", Font.BOLD, 35), Color.BLACK);

        //instructions
        graphicsHandler.drawString("Move left and right: Arrow keys", 100, 180, new Font("Times New Roman", Font.BOLD, 22), (new Color (126,23,23)));
        graphicsHandler.drawString("Jump: Up arrow",100,230, new Font("Times New Roman", Font.BOLD, 22), (new Color(126,23,23)));
        graphicsHandler.drawString("Crouch:  Down arrow", 100,280, new Font("Times New Roman", Font.BOLD, 22), (new Color(126,23,23)));
        graphicsHandler.drawString("Attack:  A key", 100,330, new Font("Times New Roman", Font.BOLD, 22), (new Color(126,23,23)));
        graphicsHandler.drawString("Climb:  C key", 100,380, new Font("Times New Roman", Font.BOLD, 22), (new Color(126,23,23)));

        graphicsHandler.drawString("Press SPACE to return to main menu", 200,500, new Font("Times New Roman", Font.BOLD, 24), Color.RED);
        

    }
}
