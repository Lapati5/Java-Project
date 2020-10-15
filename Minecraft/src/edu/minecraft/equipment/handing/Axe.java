package edu.minecraft.equipment.handing;

import edu.minecraft.items.Material;

public class Axe implements Handleable {
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
                return 5;
            }
            case "Iron" -> {
                return 10;
            }
            case "Golden" -> {
                return 13;
            }
            case "Diamond" -> {
                return 17;
            }
            default -> {
                return 1;
            }
        }
    }

    //砍树
    public Material cutTree() {
        setToughness(getToughness() - 5);
        return new Material("Wood");
    }
}
