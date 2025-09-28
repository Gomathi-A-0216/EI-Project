package com.example.patterns.creational.builder;

import java.util.Objects;

public class Meal {
    private final String main;
    private final String side;
    private final String drink;

    public Meal(String main, String side, String drink) {
        this.main = main;
        this.side = side;
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "Meal[main=" + main + ", side=" + side + ", drink=" + drink + "]";
    }
}
