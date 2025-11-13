package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont NewGame;
    protected SpriteFont Continue;
    protected SpriteFont Arcade;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();


    private static final String SAVE_FILE = "campaign_level_save.txt" ;
    private boolean hasSave = false;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        hasSave = new File(SAVE_FILE).exists();

        NewGame = new SpriteFont("CAMPAIGN", 200, 123, "Arial", 30, new Color(126, 23, 23));
        NewGame.setOutlineColor(Color.black);
        NewGame.setOutlineThickness(3);

        Continue = new SpriteFont("CONTINUE", 200, 173, "Arial", 30, new Color(126, 23, 23));
        Continue.setOutlineColor(Color.black);
        Continue.setOutlineThickness(3);

        Arcade = new SpriteFont("ARCADE", 200, 223, "Arial", 30, new Color(126, 23, 23));
        Arcade.setOutlineColor(Color.black);
        Arcade.setOutlineThickness(3);
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);

        if (!SoundPlayer.isPlaying()){
            //SoundPlayer.playMusic("Resources/POC.wav", true); 
            //System.out.println("Music file exists: " + new File("POC.wav").exists());
        }
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        if(keyPressTimer >0){
            keyPressTimer--;
        }

        // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.DOWN) &&  keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.UP) &&  keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
        } else if (keyPressTimer > 0) { keyPressTimer--;}

        int maxIndex = hasSave ? 2 : 1;
    


        // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > maxIndex) {
            currentMenuItemHovered = 0;
        } if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = maxIndex;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            NewGame.setColor(new Color(213, 207, 181));
            Continue.setColor(hasSave  ? new Color(126, 23, 23) : Color.gray);
            Arcade.setColor(new Color(126, 23, 23));
            pointerLocationX = 170;
            pointerLocationY = 130;
        } else if (currentMenuItemHovered == 1) {
            if (hasSave) {
                NewGame.setColor(new Color(126, 23, 23));
                Continue.setColor(new Color(213, 207, 181));
                Arcade.setColor(new Color(126, 23, 23));
                pointerLocationX = 170;
                pointerLocationY = 180;
            } else {
                NewGame.setColor(new Color(126, 23, 23));
                Arcade.setColor(new Color(213, 207, 181));
                pointerLocationX = 170;
                pointerLocationY = 230;

            }
            
        } else if (currentMenuItemHovered == 2 && hasSave){
            NewGame.setColor( new Color(126, 23, 23));
            Continue.setColor(new Color(126, 23, 23));
            Arcade.setColor(new Color(213, 207, 181));
            pointerLocationX = 170;
            pointerLocationY = 230;
        }

        // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            menuItemSelected = currentMenuItemHovered;  

            if (menuItemSelected == 0 ) {
                new File(SAVE_FILE).delete();
                screenCoordinator.setGameState(GameState.PLAYER);
            } else if (menuItemSelected == 1 && hasSave){
                screenCoordinator.setGameState(GameState.LEVEL);
            } else if (menuItemSelected == 1 && !hasSave || (menuItemSelected == 2)) {
                screenCoordinator.setGameState(GameState.PLAYER);
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        NewGame.draw(graphicsHandler);
        if (hasSave) Continue.draw(graphicsHandler);
        Arcade.draw(graphicsHandler);

        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(126, 23, 23), Color.black, 2);
    }
}
