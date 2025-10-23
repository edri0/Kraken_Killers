package NPCs;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.Sprite;
import GameObject.SpriteSheet;
import Inventory.Armor;
import Level.NPC;
import Level.Player;
import Utils.Point;

import java.util.HashMap;
public class Chest extends NPC {
    public Chest(Point location){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Chest.png"), 16, 16), "Chest_Closed");
        isInteractable = true;
        talkedToTime = 200;
        //As Armor gets added this can be updated3
         textboxOffsetX = -4;
        textboxOffsetY = -34;

        
    }

    public void update(Player player){
        if(talkedTo){
            //when chest is interacted with it will generate a number between 1 and 3 which will assign which armor is given to the player
            //1 = bronze
            //2 = silver
            //3 = diamond 
            currentAnimationName = "Chest_Open";
            int armorAssign = (int)Math.floor(Math.random()*3) + 1;
            if(armorAssign ==1){
                 textbox.setText("Congrats you have received the bronze armor and some money!");
            Armor bArmor = new Armor("bronze", 0, 20, new Sprite(null));
            bArmor.equip(player);
            } else if(armorAssign == 2){
                textbox.setText("Congrats you have recieved the iron armor and a lot of money!");
                Armor iArmor = new Armor("iron", 0, 50, null);
                iArmor.equip(player);
            }else{
                textbox.setText("CONGRATS!! YOU HAVE FOUND THE DIAMOND ARMOR AND TONS OF RICHES!!");
                Armor dArmor = new Armor("diamond", 0, 100, null);
                dArmor.equip(player);
            }
        } else{
            currentAnimationName = "Chest_Closed";
        }
    }
    @Override
public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
           put("Chest_Closed", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(3)
                           .build()
           });
            put("Chest_Open", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1))
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
