package com.example.patterns.behavioral.strategy;

public class ZipCompressionStrategy implements CompressionStrategy {
    @Override
    public void compress(String filename) {
        System.out.println("[Strategy-ZIP] Compressing " + filename + " (simulated)");
        // simulate CPU-bound lightweight task
        for (int i=0;i<100_000;i++) { int x = i*i; if (i%25000==0) {} }
        System.out.println("[Strategy-ZIP] Done");
    }
}
