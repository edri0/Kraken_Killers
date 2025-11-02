package Inventory;
import Game.ArmorTimer;
import Game.ArmorType;
import GameObject.Sprite;
import Level.Player;


public class Armor extends Item{
    
    private int hpValue;
    private boolean equipped;
    private Sprite sprite;
    private ArmorType armorType;
    private ArmorTimer timer;

    public Armor(String name, int costCents, int hpValue, Sprite sprite){
        super(name, costCents, ItemType.ARMOR);

        this.hpValue = hpValue;
        this.equipped = false;
        this.sprite = sprite;
        this.timer = new ArmorTimer();

        String lowerName = name.toLowerCase();
        if(lowerName.contains("bronze")) armorType = ArmorType.BRONZE;
        else if(lowerName.contains("iron")) armorType = ArmorType.IRON;
        else if(lowerName.contains("diamond"))armorType = ArmorType.DIAMOND;
        else armorType = ArmorType.NONE;
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
            equipped = true;
            player.setArmor(this);


            String avatarName = player.getAvatarTypeName();
            ArmorType armorType;
//applying armor based on what the type is
            if(name.toLowerCase().contains("bronze")) armorType = ArmorType.BRONZE;
            else if(name.toLowerCase().contains("iron")) armorType = ArmorType.IRON;
            else if(name.toLowerCase().contains("diamond")) armorType = ArmorType.DIAMOND;
            else armorType = ArmorType.NONE;

            System.out.println("applying new armor sprite: " + armorType);
        
            player.updatePlayerSprite(avatarName, armorType);
    }
    public void unequip(Player player){
        if (equipped){
            equipped = false;
            player.removeArmor();
            player.updatePlayerSprite(player.getAvatarTypeName(), ArmorType.NONE);
        }
    }
    public void forceEquip(Player player){
        equipped = true;
        player.setArmor(this);
        String avatarName = player.getAvatarTypeName();
        ArmorType armorType;

        if(name.toLowerCase().contains("bronze")) armorType = ArmorType.BRONZE;
        else if (name.toLowerCase().contains("iron")) armorType = ArmorType.IRON;
        else if (name.toLowerCase().contains("diamond")) armorType = ArmorType.DIAMOND;
        else armorType = ArmorType.NONE;
        player.updatePlayerSprite(avatarName, armorType);
    }
   
}

