package com.example.patterns.behavioral.strategy;

public class GzipCompressionStrategy implements CompressionStrategy {
    @Override
    public void compress(String filename) {
        System.out.println("[Strategy-GZIP] Compressing " + filename + " (simulated)");
        try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        System.out.println("[Strategy-GZIP] Done");
    }
}
