package Inventory;
import java.util.ArrayList;
import java.util.List;


public class PlayerInventory{
    private int moneyCents = 3000;
    private final List<Item> owned = new ArrayList<>();
    private Item equippedWeapon;
    private Armor equippedArmor;
    private Item addItem;

    public int getMoneyCents() { 
        return moneyCents;
    }
    public void addMoneyCents(int delta){
        moneyCents = Math.max(0, moneyCents + delta);
    }
    
    public List<Item> getOwned(){
        return owned;
    }
    public Item getEquippedWeapon(){
        return equippedWeapon;
    }
    public Armor getEquippedArmor(){
        return equippedArmor;
    }
    public Item getaddItem(Armor rewardArmor){
        return addItem;
    }

    public boolean buy(Item item){
        if(moneyCents >= item.costCents){
            moneyCents -= item.costCents;
            owned.add(item);
            if (item.type == ItemType.WEAPON && equippedWeapon == null){
                equippedWeapon = item;
            }
            if (item.type == ItemType.ARMOR && equippedArmor == null){
                equippedArmor = (Armor) item;
            }
            return true;
        }
        return false;
    }
    public void equip (Item item){
        if (!owned.contains(item)){
            return;
        }
        if (item.type == ItemType.WEAPON){
            equippedWeapon = item;
        }
        if (item.type == ItemType.ARMOR) {
            equippedArmor = (Armor) item;
        }

    }
    public static String fmt(int cents){
        return String.format("$: %.2f", cents / 100.0);
    }
    public void setEquippedArmor(Armor armorItem) {
        this.equippedArmor = armorItem;
    }

}
