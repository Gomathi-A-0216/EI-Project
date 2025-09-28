package com.example.patterns.creational.builder;

public class MealBuilder {
    private String main;
    private String side;
    private String drink;

    public MealBuilder addMain(String main) { this.main = main; return this; }
    public MealBuilder addSide(String side) { this.side = side; return this; }
    public MealBuilder addDrink(String drink) { this.drink = drink; return this; }

    public Meal build() {
        if (main == null || main.isEmpty()) throw new IllegalStateException("Main course required");
        if (side == null) side = "No side";
        if (drink == null) drink = "Water";
        return new Meal(main, side, drink);
    }
}
