package Inventory;
import GameObject.Sprite;
import Level.Player;


public class Armor extends Item{
    
    private int hpValue;
    private boolean equipped;
    private Sprite sprite;

    public Armor(String name, int costCents, int hpValue, Sprite sprite){
        super(name, costCents, ItemType.ARMOR);

        this.hpValue = hpValue;
        this.equipped = false;
        this.sprite = sprite;
    }
 
    public int getHpValue(){
        return hpValue;
    }
    public Sprite getSprite(){
        return sprite;
    }
    public boolean isEquipped(){
        return equipped;
    }
    public void equip(Player player){
        if (!equipped){
            equipped = true;
            player.setArmor(this);
        }
    }
    public void unequip( Player player){
        if (equipped){
            equipped = false;
            player.removeArmor();
        }
    }

}

