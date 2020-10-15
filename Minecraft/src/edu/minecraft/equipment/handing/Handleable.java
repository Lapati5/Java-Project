package edu.minecraft.equipment.handing;

/**
 * 可手持工具
 */
public interface Handleable {
    void setMaterial(String m);     //设置材料
    void setToughness(int t);       //设置耐久度
    int getToughness();             //获得耐久度
    int getAttackPoint();           //获得攻击值
}
