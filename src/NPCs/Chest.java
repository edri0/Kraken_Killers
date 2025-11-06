package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.Sprite;
import GameObject.SpriteSheet;
import Inventory.Armor;
import Inventory.PlayerInventory;
import Level.NPC;
import Level.Player;
import Utils.Point;
import Screens.ShopScreen;

import java.util.HashMap;

public class Chest extends NPC {

    private static boolean isOpened = false;

    public Chest(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Chest.png"), 16, 16), "Chest_Closed");
        isInteractable = true;
        talkedToTime = 200;
        textboxOffsetX = -4;
        textboxOffsetY = -34;
    }

    @Override
    public void update(Player player) {
        // When player presses interact (SPACE) near chest
        if (talkedTo && !isOpened) {
            currentAnimationName = "Chest_Open";
            isOpened = true;

            // Randomly assign reward
            int armorAssign = (int) (Math.random() * 3) + 1;
            Armor rewardArmor = null;
            String rewardText = "";

            if (armorAssign == 1) {
                rewardArmor = new Armor("Bronze Armor", 0, 10, new Sprite(ImageLoader.load("Bronze_armor.png")));
                rewardText = "You found Bronze Armor!";
            } else if (armorAssign == 2) {
                rewardArmor = new Armor("Iron Armor", 0, 20, new Sprite(ImageLoader.load("Iron_armor.png")));
                rewardText = "You found Iron Armor!";
            } else {
                rewardArmor = new Armor("Diamond Armor", 0, 30, new Sprite(ImageLoader.load("Diamond_armor.png")));
                rewardText = "You found Diamond Armor!";
            }

            // Add armor to player's inventory and equip automatically
            PlayerInventory inventory = new PlayerInventory();
            inventory.getaddItem(rewardArmor);
            rewardArmor.equip(player);
            inventory.setEquippedArmor(rewardArmor);
            currentAnimationName = "Chest_Closed";

            //textbox.setText(rewardText);

        } if (isOpened) {
            currentAnimationName = "Chest_Open"; // stay open
        } else {
            currentAnimationName = "Chest_Closed"; // closed until opened
        } 
        


        super.update(player);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("Chest_Closed", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .build()
            });
            put("Chest_Open", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 1))
                    .withScale(3)
                    .build()
            });
        }};
    }

    public Boolean getisOpened(Boolean isOpened){
        return isOpened;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
