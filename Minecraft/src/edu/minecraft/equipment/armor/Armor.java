package edu.minecraft.equipment.armor;

/**
 * 防具类
 */

public abstract class Armor {
    private int defensePoint;       //防御值
    private int toughness = 100;    //耐久值
    private final String material;  //制作材料

    /**
     * 根据材料初始化护甲值
     * @param material  材料种类
     */
    public Armor(String material) {
        this.material = material;
        switch (material) {
            case "Diamond" -> defensePoint = 30;
            case "Golden" -> defensePoint = 20;
            case "Iron" -> defensePoint = 10;
            case "Wooden" -> defensePoint = 5;
            default -> defensePoint = 0;
        }
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() +": " +
                "defensePoint=" + defensePoint +
                ", toughness=" + toughness +
                ", material=" + material
                ;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = Math.max(defensePoint, 0);
    }

    public int getToughness() {
        return toughness;
    }

    //耐久度设置在0~100之间
    public void setToughness(int toughness) {
        this.toughness = Math.max(toughness, 0);
        if (this.toughness > 100)
            this.toughness = 100;
    }

    public String getMaterial() { return material; }
}
