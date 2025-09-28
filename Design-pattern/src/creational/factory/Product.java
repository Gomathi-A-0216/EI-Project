package com.example.patterns.creational.factory;

public class Product {
    private final String type;
    private final int capacity;

    public Product(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return type + "(capacity=" + capacity + ")";
    }
}
