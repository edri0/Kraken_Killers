package Screens;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.ScreenCoordinator;
import SpriteFont.SpriteFont;
import Game.GameState; 
import SpriteFont.SpriteFont; 

import java.awt.*;

public class PickPlayerScreen extends Screen {
    protected ScreenCoordinator screenCoordinator; 
    protected int currentOption = 0; 
    protected SpriteFont jack, will; 
    protected int pointerLocationX, pointerLocationY; 
    protected KeyLocker keyLocker = new KeyLocker(); 
    protected int keyPressTimer = 0; 

     public PickPlayerScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }
    
    public class Players{
        public static final String jack = "JackSparrow"; 
        public static final String will = "WillTurner"; 
    } 

    
    public void initialize() {
        jack = new SpriteFont("JackSparrow", 200, 150, "Arial", 30, new Color(49, 207, 240));
        will = new SpriteFont("WillTurner", 200, 150, "Arial", 30, new Color(49, 207, 240)); 
    
        jack.setOutlineColor(Color.BLACK); 
        jack.setOutlineThickness(2);
        will.setOutlineColor(Color.BLACK); 
        will.setOutlineThickness(2); 

        keyLocker.lockKey(Key.SPACE); 
        keyLocker.lockKey(Key.RIGHT); 
        keyLocker.lockKey(Key.LEFT); 

    }   

    public void update(Keyboard keyboard) {
        if (keyboard.isKeyDown(Key.RIGHT) && !keyLocker.isKeyLocked(Key.RIGHT)) {
            currentOption = 1; 
            keyLocker.lockKey(Key.RIGHT); 
        }
        else if (keyboard.isKeyDown(Key.LEFT) && !keyLocker.isKeyLocked(Key.LEFT)) {
            currentOption = 0; 
            keyLocker.lockKey(Key.LEFT); 
        }

        if (keyboard.isKeyUp(Key.RIGHT)){
            keyLocker.unlockKey(Key.RIGHT);
        }

        if (keyboard.isKeyUp(Key.LEFT)){
            keyLocker.unlockKey(Key.LEFT);
        }

        if (keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)){
            if (currentOption == 0){
                ScreenCoordinator.selectedPlayer = "JackSparrow";
            }
            else {
                ScreenCoordinator.selectedPlayer = "WillTurner";
            }

            screenCoordinator.setGameState(GameState.LEVEL); 
            keyLocker.lockKey(Key.SPACE); 
        }
        if (keyboard.isKeyUp(Key.SPACE)){
            keyLocker.unlockKey(Key.SPACE); 
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (currentOption == 0){
            jack.setColor(Color.YELLOW); 
            will.setColor(Color.BLUE); 
            pointerLocationX = 170; 
            pointerLocationY = 155; 
        }
        else if (currentOption == 1){
            jack.setColor(Color.YELLOW); 
            will.setColor(Color.BLUE); 
            pointerLocationX = 170; 
            pointerLocationY = 155; 

            jack.draw(graphicsHandler); 
            will.draw(graphicsHandler); 

            graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(49, 207, 240), Color.BLACK, 2);
        }
    }


 /* 
    @Override
    public void update() {
        Keyboard keyboard = ScreenCoordinator.getKeyboard();

    if (keyboard.isKeyDown(Key.RIGHT) && !keyLocker.isKeyLocked(Key.RIGHT)) {
            currentOption = 1;
            keyLocker.lockKey(Key.RIGHT);
        } else if (keyboard.isKeyDown(Key.LEFT) && !keyLocker.isKeyLocked(Key.LEFT)) {
            currentOption = 0;
            keyLocker.lockKey(Key.LEFT); 
        }

        if (keyboard.isKeyUp(Key.RIGHT)){
            keyLocker.unlockKey(Key.RIGHT);
        }

        if (keyboard.isKeyUp(Key.LEFT)){
            keyLocker.unlockKey(Key.LEFT);
        }

        if (keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)){
            if (currentOption == 0){
                ScreenCoordinator.selectedPlayer = "JackSparrow";
            }
            else {
                ScreenCoordinator.selectedPlayer = "WillTurner";
            }

            screenCoordinator.setGameState(GameState.LEVEL); 
            keyLocker.lockKey(Key.SPACE); 
        }
        if (keyboard.isKeyUp(Key.SPACE)){
            keyLocker.unlockKey(Key.SPACE); 
        }


    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }   
  */
}