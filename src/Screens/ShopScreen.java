package Screens;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import GameObject.Sprite;
import Inventory.Armor;
import Inventory.Item;
import Inventory.ItemType;
import Inventory.PlayerInventory;
import Level.Player;

public class ShopScreen extends Screen{
    private final PlayerInventory inventory;
    private final List<Item> shopItems = new ArrayList<>();
    
    private int shopIndex = 0;
    private int ownedIndex = 0;
    private boolean inShop = true;


    private Rectangle panelShop;
    private Rectangle panelOwned;
    private Rectangle panelEquipped;
    private Rectangle actionBox;

    private int keyPressTimer = 0;



    public ShopScreen(PlayerInventory inventory){
        this.inventory = inventory;
        seedCatalog();

    }
    private void seedCatalog(){
        BufferedImage bronzeImg = ImageLoader.load("Bronze_armor.png");
        Sprite bronzeArmorSprite = new Sprite(bronzeImg);
        shopItems.add(new Armor("Bronze Armor", 1500, 5, bronzeArmorSprite));


        BufferedImage ironImg = ImageLoader.load("Iron_armor.png");
        Sprite ironArmorSprite = new Sprite(ironImg);
        shopItems.add(new Armor("Iron Armor", 1500, 5, ironArmorSprite));


        BufferedImage diamondImg = ImageLoader.load("Diamond_armor.png");
        Sprite diamondArmorSprite = new Sprite(diamondImg);
        shopItems.add(new Armor("Diamond Armor", 1500, 5, diamondArmorSprite));

        
        shopItems.add(new Item("Grappling hook", 1500, ItemType.WEAPON));
        shopItems.add(new Item("Shield", 1500, ItemType.ARMOR));
        shopItems.add(new Item("Underwater boots", 1500, ItemType.ARMOR));
    }


    public void initialize(){
        int w = ScreenManager.getScreenWidth();
        int h = ScreenManager.getScreenHeight();

        panelOwned = new Rectangle(20, 80, w/2-40, h-260);
        panelEquipped = new Rectangle(20, h-170, w/2-40, 90);
        panelShop = new Rectangle(w/2+20, 80, w/2-40, h-180);
        actionBox = new Rectangle(w/2+20, h-90, 220, 50);


    }
        public void update(){
            if (keyPressTimer > 0) {
                keyPressTimer--;
                return;
            }
            if( Keyboard.isKeyDown(Key.A) || Keyboard.isKeyDown(Key.LEFT)){
                inShop = false;
                keyPressTimer = 14;
            } else if (Keyboard.isKeyDown(Key.D) || Keyboard.isKeyDown(Key.RIGHT)){
                inShop = true;
                keyPressTimer = 14;
            
            }
            if (Keyboard.isKeyDown(Key.W) || Keyboard.isKeyDown(Key.UP)) {
               if (inShop)
               shopIndex = Math.max(0, shopIndex - 1);
               else    
                    ownedIndex = Math.max(0, ownedIndex - 1);
                    keyPressTimer = 14;
                    return;

                } else if (Keyboard.isKeyDown(Key.DOWN)) {
                    if (inShop)
                        shopIndex = Math.min(shopItems.size() - 1, shopIndex + 1);
                    else 
                        ownedIndex = Math.min(inventory.getOwned().size() - 1, ownedIndex + 1);
                    keyPressTimer = 14;
                    return;
                }
                 if (Keyboard.isKeyDown(Key.SPACE) || Keyboard.isKeyDown(Key.ENTER)) {
                    if(inShop && !shopItems.isEmpty()){
                        inventory.buy(shopItems.get(shopIndex));

                    } else if(!inShop && !inventory.getOwned().isEmpty()){
                        inventory.equip(inventory.getOwned().get(ownedIndex));

                    }
                    keyPressTimer = 14;

                }
            }

        public void draw(GraphicsHandler g) {
           int w = ScreenManager.getScreenWidth();
           int h = ScreenManager.getScreenHeight();

           g.drawFilledRectangle(0,0,w,h,new Color(0,0,0,170));
           
           g.drawFilledRectangle(0,0,w,60, new Color(22,29,44));
           g.drawString("SHOP and INVENTORY (WASD + ENTER, Press S to close)", 20, 40, new Font("Ariel", Font.BOLD, 24), Color.WHITE);
           g.drawString("Cash: " + PlayerInventory.fmt(inventory.getMoneyCents()), actionBox.x + actionBox.width, actionBox.y +34, new Font("Ariel", Font.BOLD, 18), Color.WHITE);


           drawPanel(g, panelOwned, "INVENTORY ( <- / -> to switch)", new Color(31,47,70));
           drawPanel(g, panelEquipped, "EQUIPPED ITEMS", new Color(31,47,70));
           drawPanel(g, panelShop, "Shop (click to select)", new Color(30,41,59));
           
           for (int i = 0; i< inventory.getOwned().size(); i++){
            Item it = inventory.getOwned().get(i);
            boolean selected = !inShop && (i== ownedIndex);
            drawRow(g, panelOwned.x + 16, panelOwned.y + 32 + (i * 28), it.name + " [" + it.type + "]", selected);
        
           }

           g.drawString("WEAPON:  "+ nameOrDash(inventory.getEquippedWeapon()), panelEquipped.x + 16, panelEquipped.y + 32, baseFont(), Color.WHITE);
           g.drawString("ARMOR:  "+ nameOrDash(inventory.getEquippedArmor()), panelEquipped.x + 16, panelEquipped.y + 64, baseFont(), Color.WHITE);
          
           for(int i = 0; i< shopItems.size(); i++) {
            Item it = shopItems.get(i);
            boolean selected = inShop && ( i == shopIndex);
            String line = it.name + " - " + PlayerInventory.fmt(it.costCents) + " [" + it.type + "]";
            drawRow(g,panelShop.x + 16, panelShop.y + 32 + (i * 28), line, selected);
           }

           g.drawFilledRectangle(actionBox.x, actionBox.y, actionBox.width, actionBox.height, new Color(46,83,125));
           g.drawString(inShop ? "BUY (ENTER)" : "EQUIP (ENTER)",  actionBox.x + 20, actionBox.y + 34, new Font("Arial", Font.BOLD, 22), Color.WHITE);
        }
    
        private void drawPanel(GraphicsHandler g, Rectangle r, String title, Color bg){
            g.drawFilledRectangle(r.x,r.y,r.width,r.height, bg);
            g.drawRectangle(r.x, r.y,r.width,r.height,bg);
            g.drawString( title, r.x + 12, r.y - 8, new Font("Arial", Font.BOLD, 16), Color.WHITE);
        }
        private void drawRow(GraphicsHandler g, int x, int y , String text, boolean selected){
            g.drawFilledRectangle(x - 4, y - 16, 360, 24, selected ? new Color(56,105,170) : new Color(35, 50, 75));
            g.drawString(text, x, y, baseFont(), Color.WHITE);
        }
        private Font baseFont(){
            return new Font("Arial", Font.PLAIN, 16);
        }
        private String nameOrDash(Item i){
            return i == null ? "-" : i.name;
        }

    }
    
