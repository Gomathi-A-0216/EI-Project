package com.example.patterns.behavioral.observer;

import java.util.concurrent.CopyOnWriteArrayList;

public class ConcreteSubject implements Subject {
    private final CopyOnWriteArrayList<Observer> observers = new CopyOnWriteArrayList<>();
    private volatile double temperature;

    @Override
    public void registerObserver(Observer o) { observers.addIfAbsent(o); }

    @Override
    public void removeObserver(Observer o) { observers.remove(o); }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            try {
                o.update(temperature);
            } catch (Exception e) {
                // defensive: swallow per-observer errors but log if needed
                System.err.println("Observer error: " + e.getMessage());
            }
        }
    }

    public void setState(double temp) {
        this.temperature = temp;
        notifyObservers();
    }
}
