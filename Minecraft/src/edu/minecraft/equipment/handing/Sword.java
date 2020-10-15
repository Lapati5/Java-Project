package edu.minecraft.equipment.handing;

public class Sword implements Handleable {
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
                return 6;
            }
            case "Iron" -> {
                return 9;
            }
            case "Golden" -> {
                return 12;
            }
            case "Diamond" -> {
                return 15;
            }
            default -> {
                return 2;
            }
        }
    }

    //打飞火焰弹
    public void hitFireCharge() {
        setToughness(getToughness() - 3);
    }
}
