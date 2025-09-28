package com.example.patterns.creational.factory;

public class ProductFactory {
    public static Product createProduct(String kind, int capacity) {
        if (kind == null) throw new IllegalArgumentException("kind required");
        switch(kind.toUpperCase()) {
            case "STANDARD": return new Product("STANDARD", capacity);
            case "PREMIUM": return new Product("PREMIUM", capacity * 2);
            default: throw new IllegalArgumentException("Unknown product kind: " + kind);
        }
    }
}
