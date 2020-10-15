package edu.minecraft;

import edu.minecraft.equipment.armor.Armor;
import edu.minecraft.equipment.handing.Handleable;
import edu.minecraft.items.Food;
import edu.minecraft.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 玩家类
 */
public class Player {
    private final String name;      //玩家姓名
    private int experience = 0;     //经验值
    private int health = 20;        //生命值（默认）
    private int hunger = 20;        //饥饿值（默认）
    private int defensePoint = 5;   //防御值（默认）
    private Handleable handing;     //主手武器
    private HashMap<String, Armor> armors = new HashMap<>();    //防具
    private List<Item> inventory = new ArrayList<>();           //物品栏

    public Player(String name) {
        this.name = name;
    }

    public int getExp() {
        return experience;
    }

    public void setExp(int experience) {
        this.experience = experience;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    public Handleable getHanding() {
        return handing;
    }

    public Armor getArmor(String armor) {
        return armors.get(armor);
    }
    //显示所有装备
    public void showArmors() {
        System.out.println(name + " equip with: ");
        for (String type : armors.keySet())
            System.out.println(getArmor(type));
    }

    @Override
    public String toString() {
        return   name + ": " +
                "experience=" + experience +
                ", health=" + health +
                ", hunger=" + hunger +
                ", defensePoint=" + defensePoint
                ;
    }

    //穿上防具
    public void wearArmor(Armor armor) {
        //获取装备种类
        String type = armor.getClass().getSimpleName();
        //移除旧的同种装备
        removeArmor(type);
        //添加新的装备
        armors.put(type, armor);
        defensePoint += armor.getDefensePoint();
        System.out.println(this.name + "已装备" + type + " in " + armor.getMaterial());
    }

    //移除防具
    public Armor removeArmor(String type) {
        Armor armor =  armors.remove(type);
        if (armor != null) {
            defensePoint -= armor.getDefensePoint();
            System.out.println(this.name + "已经移除" + type + " in " + armor.getMaterial());
        }
        return armor;
    }

    //获得武器
    public void getWeapon(Handleable h) {
        handing = h;
    }

    //移除武器
    public Handleable removeWeapon() {
        Handleable oldHanding = handing;
        handing = null;
        return oldHanding;
    }

    //拾捡物品
    public void pickItem(Item item, int number) {
        int i;
        for (i = 1; i <= number; ++i) {
            if (inventory.size() >= 64) {
                System.out.println("物品栏已满");
                break;
            }
            inventory.add(item);
        }
        System.out.println(this.name + "成功拾取" + (i - 1) + "个" + item.getName() + "至物品栏中");
    }

    //丢弃物品
    public void throwItem(Item item, int number) {
        int i;
        for (i = 0; i <= number; ++i) {
            if (!inventory.remove(item)) {
                System.out.println(i + "个" + item.getName() + "已从物品栏移除");
                break;
            }
        }
        if (i < number)
            System.out.println("背包中已无" + item.getName());
        else
            System.out.println(number + "个" + item.getName() + "已从物品栏移除");
    }

    //进食
    public void eat(Food f) {
        //补充食物
        hunger += f.getNutrition();
        if (hunger > 100) hunger = 100;
        System.out.println(this.name + "的饥饿值已增加至" + hunger);
    }

    //死亡
    public void death() {
        System.out.println(this.name + "已经死亡");
    }

    //攻击
    public void attack(Player p) {
        System.out.println("### " + name + " attacks " + p.name);
        //无武器时，默认攻击值为1
        int ap = 1;
        //当拥有武器且耐久值大于0时
        if (handing != null && handing.getToughness() > 0) {
            ap = handing.getAttackPoint();
            //武器损失10点耐久值
            handing.setToughness(handing.getToughness() - 10);
            //耐久值为0时，失去武器
            if (handing.getToughness() == 0)
                handing = null;
        }

        //攻击值大于防守值时, 收到伤害, 防御值清空，否则减少防御值
        if (p.defensePoint - ap < 0) {
            p.health += p.defensePoint - ap;
            p.defensePoint = 0;
        } else {
            p.defensePoint -= ap;
        }

        //敌方所有防具损失耐久值，防御值
        for (String type : p.armors.keySet()) {
            Armor armor = p.armors.get(type);
            armor.setToughness(armor.getToughness() - ap);
            armor.setDefensePoint(armor.getDefensePoint() - ap / p.armors.size());
            //当耐久值或防御值为0，移除改装备
            if (armor.getToughness() == 0 || armor.getDefensePoint() == 0) {
                p.removeArmor(type);
            }
        }
        //每次攻击消耗饥饿值
        hunger -= 3;
        //饥饿值小于0时，消耗生命值
        if (hunger <= 0)
            health -= 2;
        //若生命值低于0，则视为死亡，未死亡方获得经验值
        if (p.health <= 0) {
            p.death();
            experience += 50;
        }
        if (health <= 0) {
            death();
            p.setExp(p.getExp() + 50);
        }
    }
}
