package edu.minecraft.equipment.handing;

import edu.minecraft.items.Material;

public class Pickaxe implements Handleable {
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
                return 5;
            }
            case "Golden" -> {
                return 7;
            }
            case "Diamond" -> {
                return 10;
            }
            default -> {
                return 1;
            }
        }
    }

    //挖石头
    public Material digStone() {
        setToughness(getToughness() - 5);
        return new Material("Stone");
    }
}
