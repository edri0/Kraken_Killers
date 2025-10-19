package Inventory;

public class Item {
    public final String name;
    public final int costCents;
    public final ItemType type;

    public Item(String name, int costCents, ItemType type){
        this.name = name;
        this.costCents = costCents;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public int getCostCents(){
        return costCents;
    }
    public ItemType getType(){
        return type;
    }
}
