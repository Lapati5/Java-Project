package edu.minecraft;

import edu.minecraft.equipment.armor.Armor;
import edu.minecraft.equipment.armor.Boots;
import edu.minecraft.equipment.armor.Chestplate;
import edu.minecraft.equipment.armor.Helmet;
import edu.minecraft.equipment.handing.Axe;
import edu.minecraft.equipment.handing.Pickaxe;
import edu.minecraft.equipment.handing.Sword;
import edu.minecraft.items.Anvil;
import edu.minecraft.items.Food;
import edu.minecraft.items.Material;

public class MinecraftGame {
    public static void main(String[] args) {

        //创建一个叫做Steve的玩家
        Player steve = new Player("Steve");
        //创建一个叫做Alex的玩家
        Player alex = new Player("Alex");
        //二者的初始属性
        System.out.println("初始属性：");
        System.out.println(steve);
        System.out.println(alex);
        System.out.println("==========================================================");
        //创建一个金子材质的鞋子
        Armor boots = new Boots("Golden");
        //Steve穿上这个鞋子（他防御力增加了）
        steve.wearArmor(boots);
        //Steve又带上头盔
        steve.wearArmor(new Helmet("Iron"));
        //Alex穿上胸甲（防御力增强了）
        alex.wearArmor(new Chestplate("Diamond"));
        //Steve获得一把铁剑
        Sword sword = new Sword();
        sword.setMaterial("Diamond");
        steve.getWeapon(sword);
        //Alex获得一把金镐
        Pickaxe pickaxe = new Pickaxe();
        pickaxe.setMaterial("Golden");
        alex.getWeapon(pickaxe);
        //Alex用镐子挖石头并放入包中
        Material stone = pickaxe.digStone();
        alex.pickItem(stone, 2);
        //Alex从背包抛去一块石头
        alex.throwItem(stone, 1);
        //Steve吃了一个蛋糕（饥饿值增加了）
        steve.eat(new Food("Cake"));
        //Alex吃了一片面包（饥饿值增加了）
        alex.eat(new Food("Bread"));
        //Steve和Alex的攻击值
        System.out.println("Steve's attackPoint: " + steve.getHanding().getAttackPoint());
        System.out.println("Alex's attackPoint: " + alex.getHanding().getAttackPoint());
        steve.showArmors();
        alex.showArmors();
        System.out.println("=============================");
        //两个玩家相互攻击直至一方死亡
        while (true) {
            steve.attack(alex);
            if (steve.getHealth() <= 0 || alex.getHealth() <= 0)
                break;
            System.out.println(alex);
            alex.attack(steve);
            if (steve.getHealth() <= 0 || alex.getHealth() <= 0)
                break;
            System.out.println(steve);
        }
        System.out.println("====================================");
        System.out.println(steve);
        System.out.println(alex);
        steve.showArmors();
        alex.showArmors();
        //Steve更换靴子
        steve.wearArmor(new Boots("Iron"));
        steve.showArmors();
        System.out.println("====================================");
        //修复Steve的装备
        Anvil anvil_steve = new Anvil(steve);
        anvil_steve.fixArmor(new Material("Golden"), steve.getArmor("Boots"));
        anvil_steve.fixArmor(new Material("Iron"), steve.getArmor("Helmet"));
        steve.showArmors();
        //移除靴子
        steve.removeArmor("Helmet");
        steve.showArmors();
//        //创建了64个 火把 并放入泛型容器里
//        List<Torch> torches = new ArrayList<>();
//
//        for(int i=0;i<64;i++)
//        {
//            torches.add(new Torch());
//        }
//
//        //Alex把这些 火把 放到了物品栏里
//        alex.equip(torches);

    }
}
