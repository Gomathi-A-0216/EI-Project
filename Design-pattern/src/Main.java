package com.example.patterns;

import com.example.patterns.util.LoggerFactory;
import com.example.patterns.behavioral.observer.*;
import com.example.patterns.behavioral.strategy.*;
import com.example.patterns.creational.factory.*;
import com.example.patterns.creational.builder.*;
import com.example.patterns.structural.proxy.*;
import com.example.patterns.structural.adapter.*;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Entry point demonstrating all six use cases.
 * The application runs as a long-lived process gathering user inputs.
 * It avoids a literal while(true) by using a ScheduledExecutorService that reschedules prompts.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class.getName());
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static final ExecutorService worker = Executors.newCachedThreadPool();
    private static volatile boolean running = true;

    public static void main(String[] args) {
        logger.info("Starting Design Patterns Demo application");

        // Setup Observer example
        ConcreteSubject weatherStation = new ConcreteSubject();
        weatherStation.registerObserver(new ConsoleObserver("Observer-1"));
        weatherStation.registerObserver(new ConsoleObserver("Observer-2"));

        // Strategy example
        CompressionStrategy zipStrategy = new ZipCompressionStrategy();
        CompressionStrategy gzipStrategy = new GzipCompressionStrategy();

        // Factory example
        Product p1 = ProductFactory.createProduct("STANDARD", 100);
        Product p2 = ProductFactory.createProduct("PREMIUM", 200);

        // Builder example
        Meal meal = new MealBuilder().addMain("Grilled Chicken").addSide("Salad").addDrink("Water").build();

        // Proxy example
        SecureService service = new SecureServiceImpl();
        SecureService proxy = new SecureServiceProxy(service, "alice"); // try with different user

        // Adapter example with legacy sensor
        LegacyTemperatureSensor legacy = new LegacyTemperatureSensor();
        ITemperatureSensor sensor = new TemperatureAdapter(legacy);

        // Demonstrate patterns
        logger.info("--- Demonstrations ---");
        logger.info("Observer: publishing temperature 25.5");
        weatherStation.setState(25.5);

        logger.info("Strategy: compressing data with zip and gzip (simulated)");
        zipStrategy.compress("data.bin");
        gzipStrategy.compress("data.bin");

        logger.info("Factory: created products -> " + p1 + ", " + p2);
        logger.info("Builder: meal -> " + meal);

        logger.info("Proxy: invoking secured operation as 'alice'");
        try {
            proxy.performSensitiveOperation();
        } catch (SecurityException se) {
            logger.warning("Security exception: " + se.getMessage());
        }

        logger.info("Adapter: read temperature from legacy sensor -> " + sensor.readTemperature());

        // Start prompt loop (rescheduled instead of while(true))
        Scanner scanner = new Scanner(System.in);
        Runnable promptTask = new Runnable() {
            @Override
            public void run() {
                if (!running) {
                    return;
                }
                System.out.println("\nEnter command (status, settemp <val>, compress <zip|gzip>, exit):");
                try {
                    if (!scanner.hasNextLine()) {
                        scheduleNext();
                        return;
                    }
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty()) {
                        scheduleNext();
                        return;
                    }
                    worker.submit(() -> handleInput(line, weatherStation, proxy));
                } catch (Exception e) {
                    logger.severe("Error reading input: " + e.getMessage());
                } finally {
                    scheduleNext();
                }
            }
            private void scheduleNext() {
                scheduler.schedule(this, 200, TimeUnit.MILLISECONDS);
            }
        };
        // schedule first prompt
        scheduler.schedule(promptTask, 100, TimeUnit.MILLISECONDS);

        // Add shutdown hook for graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutdown requested. Cleaning up...");
            running = false;
            scheduler.shutdownNow();
            worker.shutdownNow();
        }));

        // Main thread waits until exit command sets running=false
        try {
            while (running) {
                Thread.sleep(500);
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        logger.info("Application terminated");
    }

    private static void handleInput(String line, ConcreteSubject weatherStation, SecureService proxy) {
        Logger logger = LoggerFactory.getLogger("InputHandler");
        try {
            String[] parts = line.split("\\s+");
            switch (parts[0].toLowerCase()) {
                case "status":
                    System.out.println("Demo status: running");
                    break;
                case "settemp":
                    if (parts.length < 2) {
                        System.out.println("Usage: settemp <value>");
                        break;
                    }
                    double val = Double.parseDouble(parts[1]);
                    weatherStation.setState(val);
                    System.out.println("Temperature updated -> " + val);
                    break;
                case "compress":
                    if (parts.length < 2) {
                        System.out.println("Usage: compress <zip|gzip>");
                        break;
                    }
                    CompressionStrategy strat = "zip".equalsIgnoreCase(parts[1]) ? new ZipCompressionStrategy() : new GzipCompressionStrategy();
                    strat.compress("user-data.bin");
                    break;
                case "secure":
                    // try secured operation
                    try {
                        proxy.performSensitiveOperation();
                        System.out.println("Secure operation succeeded");
                    } catch (SecurityException se) {
                        System.out.println("Secure operation denied: " + se.getMessage());
                    }
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    running = false;
                    scheduler.shutdownNow();
                    worker.shutdown();
                    break;
                default:
                    System.out.println("Unknown command: " + parts[0]);
            }
        } catch (NumberFormatException nfe) {
            logger.warning("Invalid number format: " + nfe.getMessage());
            System.out.println("Invalid number format");
        } catch (Exception e) {
            logger.severe("Error handling input: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }
}
