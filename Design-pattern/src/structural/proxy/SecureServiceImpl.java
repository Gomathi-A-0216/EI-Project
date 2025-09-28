package com.example.patterns.structural.proxy;

public class SecureServiceImpl implements SecureService {
    @Override
    public void performSensitiveOperation() {
        System.out.println("[SecureService] Sensitive operation performed");
    }
}
