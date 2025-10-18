package NPCs;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Utils.Point;

import java.util.HashMap;
public class Chest extends NPC {
    public Chest(Point location){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Chest.png"), 16, 16), "Chest_Closed");
        isInteractable = true;
        talkedToTime = 200;
        textbox.setText("Congrats you have recieved money and armor!"); //As Armor gets added this can be updated
         textboxOffsetX = -4;
        textboxOffsetY = -34;
        
    }

    public void update(Player player){
        if(talkedTo){
            currentAnimationName = "Chest_Open";
        } else{
            currentAnimationName = "Chest_Closed";
        }
    }
    @Override
public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
           put("Chest_Closed", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withBounds(8, 5, 16, 16)
                           .withScale(3)
                           .build()
           });
            put("Chest_Open", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1))
                    .withBounds(8, 5, 16, 16)
                            .withScale(3)
                            .build()
            });
        }};
    }
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
