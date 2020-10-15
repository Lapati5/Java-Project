package edu.minecraft.items;

/**
 * 物件类，可以放置在物品栏中
 */
public class Item {
    private String name;       //物件的名称

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
