package Inventory;

import Game.ArmorType;
import Game.WeaponType;
import GameObject.Sprite;
import Level.Player;

public class Weapon extends Item{
    private int damage; 
    private boolean equipped;
    private Sprite sprite;


    public Weapon(String name, int costCents, int damage){
        super(name, costCents, ItemType.WEAPON); 
        this.damage = damage; 
    }
    
    public int getDamage() {
        return damage; 
    }

    public void unequipWeapon(Player player){
        if (!equipped) return;
        equipped = false;
        player.removeWeapon();
        player.updatePlayerWeaponSprite(player.getAvatarTypeName(), WeaponType.NONE);
    }
    public void equip(Player player){
            if (equipped) return;
            equipped = true;

            player.setWeapon(this);

            WeaponType weaponType;
             //applying armor based on what the type is
            String lower = name.toLowerCase();
            if(name.toLowerCase().contains("pistol")) weaponType = WeaponType.PISTOL;
            else weaponType = WeaponType.NONE;

            System.out.println("applying new weapon sprite: " + weaponType);
        
            player.updatePlayerWeaponSprite(player.getAvatarTypeName(), weaponType);
    }
    
    public void unequipWeapon(Player player) {
        if (!equipped) return;
        equipped = false;
        player.removeWeapon();
        player.updatePlayerWeaponSprite(player.getAvatarTypeName(), WeaponType.NONE);
    }
}
