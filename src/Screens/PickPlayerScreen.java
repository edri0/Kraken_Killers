package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import java.awt.*;
import java.io.File;

// This is the class for the main menu screen
public class PickPlayerScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont jack;
    protected SpriteFont will;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker(); 


    private static final String SAVE_FILE = "campaign_level_save.txt" ;
    private boolean hasSave = false;
    private int selectedPlayer; 

    public PickPlayerScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }
 


    @Override
    public void initialize() {
        hasSave = new File(SAVE_FILE).exists();

        jack = new SpriteFont("JACK", 200, 123, "Arial", 30, new Color(126, 23, 23));
        jack.setOutlineColor(Color.black);
        jack.setOutlineThickness(3);

        will = new SpriteFont("WILL", 200, 173, "Arial", 30, new Color(126, 23, 23));
        will.setOutlineColor(Color.black);
        will.setOutlineThickness(3);

        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);

        selectedPlayer = 0; 

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
            jack.setColor(new Color(213, 207, 181));
            will.setColor(new Color(126, 23, 23));
            pointerLocationX = 170;
            pointerLocationY = 130;
        } else if (currentMenuItemHovered == 1) {
            if (hasSave) {
                jack.setColor(new Color(126, 23, 23));
                will.setColor(new Color(213, 207, 181));
                pointerLocationX = 170;
                pointerLocationY = 180;
            } else {
                jack.setColor(new Color(126, 23, 23));
                will.setColor(new Color(213, 207, 181));
                pointerLocationX = 170;
                pointerLocationY = 230;

            }
            
        } else if (currentMenuItemHovered == 2 && hasSave){
            jack.setColor( new Color(126, 23, 23));
            will.setColor(new Color(126, 23, 23));
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
                selectedPlayer = 0;
                screenCoordinator.setGameState(GameState.LEVEL);
            } 
            else if (menuItemSelected == 1){
                selectedPlayer = 1;
                screenCoordinator.setGameState(GameState.LEVEL);
            } 

        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        jack.draw(graphicsHandler);
        will.draw(graphicsHandler);

        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(49, 207, 240), Color.black, 2);
    }
    public int getSelectedPlayer(){
        return selectedPlayer; 
    }
}
