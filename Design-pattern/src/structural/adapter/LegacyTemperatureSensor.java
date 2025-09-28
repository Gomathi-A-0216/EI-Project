package com.example.patterns.structural.adapter;

import java.util.Random;

// Legacy sensor returns raw integer in old format
public class LegacyTemperatureSensor {
    private final Random rnd = new Random();
    public int readRaw() { return 200 + rnd.nextInt(100); } // returns scaled value (eg hundredths)
}
