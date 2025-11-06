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
import Game.ArmorTimer;

import java.util.HashMap;

public class Chest extends NPC {

    private static boolean isOpened = false;
    private ArmorTimer armorTimer = new ArmorTimer();

    public Chest(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Chest.png"), 16, 16), "Chest_Closed");
        isInteractable = true;
        talkedToTime = 200;
        textboxOffsetX = -4;
        textboxOffsetY = -34;
    }

    @Override
    public void update(Player player) {

        if (talkedTo && !isOpened) {
            currentAnimationName = "Chest_Open";
            isOpened = true;

            // Assign reward armor randomly
            int armorAssign = (int)(Math.random()*3)+1;
            Armor rewardArmor = null;
            if (armorAssign == 1) rewardArmor = new Armor("Bronze Armor",0,10,new Sprite(ImageLoader.load("Bronze_armor.png")));
            else if (armorAssign == 2) rewardArmor = new Armor("Iron Armor",0,20,new Sprite(ImageLoader.load("Iron_armor.png")));
            else rewardArmor = new Armor("Diamond Armor",0,30,new Sprite(ImageLoader.load("Diamond_armor.png")));

            // Give armor to player
            PlayerInventory inventory = player.getInventory();
            inventory.getaddItem(rewardArmor);
            rewardArmor.equip(player);
            inventory.setEquippedArmor(rewardArmor);

            // Start chest armor timer (15 seconds)
            armorTimer.start(15);
        }

        // Update chest animation
        currentAnimationName = isOpened ? "Chest_Open" : "Chest_Closed";

        // Unequip if timer expired
        PlayerInventory inventory = player.getInventory();
        if (!armorTimer.isActive() && inventory.getEquippedArmor() != null) {
            inventory.setEquippedArmor(null);
        }

        super.update(player);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("Chest_Closed", new Frame[]{ new FrameBuilder(spriteSheet.getSprite(0,0)).withScale(3).build() });
            put("Chest_Open",   new Frame[]{ new FrameBuilder(spriteSheet.getSprite(0,1)).withScale(3).build() });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
