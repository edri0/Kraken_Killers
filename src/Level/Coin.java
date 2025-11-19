package Level;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Inventory.PlayerInventory;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point; 


public class Coin extends Enemy{ 
    public boolean collected = false; 

    protected KeyLocker keyLocker = new KeyLocker();
    protected Key COLLECT_KEY = Key.SPACE;


    public Coin(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Coin.png"), 16, 16), "DEFAULT");
        
        initialize();
    }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map

        super.update();

        if (intersects(player) && Keyboard.isKeyDown(COLLECT_KEY)){
            player.addMoney(coinValue); 
            this.mapEntityStatus = MapEntityStatus.REMOVED; 
            collected = true; 
        }
    }

    @Override
    public void touchedCoin(Player player) {
        // if fireball touches player, it disappears
        player.addMoney(coinValue); 
        this.mapEntityStatus = MapEntityStatus.REMOVED;
    }


    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    }
}
