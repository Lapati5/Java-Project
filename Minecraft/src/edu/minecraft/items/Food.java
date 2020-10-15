package edu.minecraft.items;

public class Food extends Item{
    private int nutrition;      //营养用于补充饥饿值

    public Food(String name) {  //不同食物营养不同
        super(name);
        switch (name) {
            case "Cake" -> nutrition = 10;
            case "Bread" -> nutrition = 5;
            case "Meat" -> nutrition = 15;
        }
    }

    public int getNutrition() {
        return nutrition;
    }
}
