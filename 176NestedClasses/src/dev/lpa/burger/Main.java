package dev.lpa.burger;

public class Main {

    public static void main(String[] args) {
        Meal meal = new Meal();
//        System.out.println(meal);
        meal.addToppings("bacon");
        System.out.println(meal);
    }
}
