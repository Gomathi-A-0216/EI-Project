package com.example.patterns.structural.adapter;

public class TemperatureAdapter implements ITemperatureSensor {
    private final LegacyTemperatureSensor legacy;
    public TemperatureAdapter(LegacyTemperatureSensor legacy) { this.legacy = legacy; }
    @Override
    public double readTemperature() {
        int raw = legacy.readRaw();
        // convert legacy scale to Celsius example
        return (raw - 273) / 1.0; // simple transform for demonstration
    }
}
