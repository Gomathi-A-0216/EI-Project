package com.example.patterns.structural.proxy;

import java.util.Set;
import java.util.HashSet;

public class SecureServiceProxy implements SecureService {
    private final SecureService delegate;
    private final String currentUser;
    private static final Set<String> allowedUsers = new HashSet<>();

    static {
        allowedUsers.add("admin");
        allowedUsers.add("system");
        // 'alice' not allowed unless added here
    }

    public SecureServiceProxy(SecureService delegate, String currentUser) {
        this.delegate = delegate;
        this.currentUser = currentUser;
    }

    @Override
    public void performSensitiveOperation() {
        if (!allowedUsers.contains(currentUser)) {
            throw new SecurityException("User '" + currentUser + "' not authorized");
        }
        delegate.performSensitiveOperation();
    }
}
