package dev.lpa.burger;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private double base = 5.0;
    private Burger burger;
    private Item drink;
    private Item side;
    private double conversionRate;

    public Meal() {
        this(1);
    }

    public Meal(double conversionRate){
        this.conversionRate = conversionRate;
        burger = new Burger("regular", "burger");
        drink = new Item("coke", "drink", 1.5);
//        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

    public void addToppings(String topping){
        burger.addToppings(topping);
    }

    public double getTotal(){
        double total = burger.price + drink.price + side.price;
        return Item.getPrice(total, conversionRate);
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%26s$%.2f".formatted(burger, drink, side, "Total due: ", getTotal());
    }

    private class Item{
        private String name;
        private String type;
        protected double price;

        public Item(String name, String type) {
            this(name, type, type.equals("burger") ? base : 0);
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            return "%10s%15s $%.2f".formatted(type, name, getPrice(price, conversionRate));
        }

        public static double getPrice(double price, double rate){
            return price * rate;
        }
    }

    public class Burger extends Item{
        private ArrayList<Item> toppings;

        public Burger(String name, String type, double price) {
            super(name, type, price);
            this.toppings = new ArrayList<>(List.of(new Item("salad", "topping"), new Item("tomato", "topping")));
        }

        public Burger(String name, String type) {
            super(name, type);
            this.toppings = new ArrayList<>(List.of(new Item("salad", "topping"), new Item("tomato", "topping")));
        }

        public void addToppings(String topping){
            String aTopping = topping.toUpperCase();

            switch (aTopping){
                case "BACON":
                    toppings.add(new Item(topping, "topping", 3.00));
                    price += 3.00;
                    break;

                case "CHEESE":
                    toppings.add(new Item(topping, "topping", 1.50));
                    price += 1.50;
                    break;

                case "ONION":
                    toppings.add(new Item(topping, "topping", 1.00));
                    price += 1.00;
                    break;

            }
        }

//        private static double getPrice(double price, double rate){
//            return price * rate;
//        }


        private String toppingsToString(){
            String output = "";
            for(var el : toppings){
                output += "%n%10s%15s $%.2f".formatted(el.type, el.name, getPrice(el.price, conversionRate));
            }
            return output;
        }

        @Override
        public String toString() {
            return super.toString() + toppingsToString();
        }
    }
}
