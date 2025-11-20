package Inventory;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory {
    public boolean collected; 

    protected int moneyCents = 3000;
    private final List<Item> owned = new ArrayList<>();
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public int getMoneyCents() { return moneyCents; }
    public void addMoneyCents(int delta) { moneyCents = Math.max(0, moneyCents + delta); }
    public static String fmt(int cents) { return String.format("$%.2f", cents / 100.0); }
    public List<Item> getOwned() { return owned; }

    public void equip(Item item) {
        if (!owned.contains(item)) return;
        if (item.type == ItemType.WEAPON) equippedWeapon = (Weapon)item;
        if (item.type == ItemType.ARMOR) equippedArmor = (Armor) item;
    }

    public Armor getEquippedArmor() { return equippedArmor; }
    public void setEquippedArmor(Armor armorItem) { this.equippedArmor = armorItem; }

    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public void setEquippedWeapon(Weapon weaponItem) { this.equippedWeapon = weaponItem; }

    public void addArmor(Armor armor) {
        if (!owned.contains(armor)) owned.add(armor);
    }

    public void removeArmor(Armor armor) {
        owned.remove(armor);
        if (equippedArmor == armor) equippedArmor = null;
    }

    public boolean buy(Item item) {
        if (moneyCents >= item.costCents) {
            moneyCents -= item.costCents;
            owned.add(item);
            // if (item.type == ItemType.WEAPON && equippedWeapon == null) equippedWeapon = item;
            return true;
        }
        return false;
    }

    public void getaddItem(Armor rewardArmor) {
        if (!owned.contains(rewardArmor)) owned.add(rewardArmor);
    }

}
