package edu.minecraft.items;

import edu.minecraft.equipment.armor.Armor;
import edu.minecraft.Player;

/**
 * 铁砧
 */
public class Anvil {
    private int toughness = 100;      //耐久值（默认值）
    private Player owner;             //拥有者

    public Anvil(Player owner) {
        this.owner = owner;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * 铁砧修复东西，传入材料和装备，然后返回修好后的装备
     */
    public Anvil fixArmor(Material m, Armor armor) {
        if (armor == null) {
            System.out.println("你还未装备此类装备");
            return null;
        }
        if (!(m.getName()).equals(armor.getMaterial())) {
            System.out.println("材料与装备材料不匹配，无法修复" + armor.getClass().getSimpleName()
                                + " in " + armor.getMaterial());
            return null;
        }
        System.out.println("ding ding ding ");
        System.out.println("正在修复装备");
        //获取之前的装备耐久度
        int armorToughness = armor.getToughness();
        //增加装备的耐久度
        armor.setToughness(armorToughness+20);
        //铁砧的耐久度下降了10点
        this.toughness -= 10;

        //玩家的经验值消耗  （不玩Minecraft的同学可以忽略这个，这个只是个游戏设定）
        int playerExp = this.owner.getExp();
        this.owner.setExp(playerExp-16);

        System.out.println("你的" + armor.getClass().getSimpleName() + "修好了");

        return this;
        //把修好了的装备拿出去
        //return edu.minecraft.armor;
        // todo : 拓展思考题：我能不能不返回这个对象？
    }

}
