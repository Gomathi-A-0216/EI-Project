package com.example.patterns.behavioral.observer;

public class ConsoleObserver implements Observer {
    private final String name;
    public ConsoleObserver(String name) { this.name = name; }
    @Override
    public void update(double temperature) {
        System.out.printf("[Observer %s] Temperature update: %.2f\n", name, temperature);
    }
}
