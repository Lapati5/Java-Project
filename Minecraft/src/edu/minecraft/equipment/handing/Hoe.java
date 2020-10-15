package edu.minecraft.equipment.handing;

public class Hoe implements Handleable {
    private String material;
    private int toughness = 100;

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = Math.max(toughness, 0);
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public void setMaterial(String m) {
        material = m;
    }

    @Override
    public int getAttackPoint() {
        switch (material) {
            case "Wooden" -> {
                return 3;
            }
            case "Iron" -> {
                return 6;
            }
            case "Golden" -> {
                return 8;
            }
            case "Diamond" -> {
                return 11;
            }
            default -> {
                return 1;
            }
        }
    }

    //开田地
    public void plough() {
        setToughness(getToughness() - 5);
    }
}
