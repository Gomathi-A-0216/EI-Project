package smarthome;

import smarthome.hub.Hub;
import smarthome.factory.DeviceFactory;
import smarthome.devices.*;
import smarthome.commands.Commands;
import smarthome.automation.*;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Hub hub = new Hub();

        List<Map<String,Object>> initial = Arrays.asList(
            Map.of("id", 1, "type", "light"),
            Map.of("id", 2, "type", "thermostat", "temperature", 70.0),
            Map.of("id", 3, "type", "door")
        );

        for (Map<String,Object> config : initial) {
            hub.registerDevice(DeviceFactory.createDevice(config));
        }

        Commands.turnOn(hub, 1);
        Commands.setThermostat(hub, 2, 72);
        Commands.lockDoor(hub, 3);
        hub.reportStatus();

        Scheduler scheduler = new Scheduler();
        scheduler.scheduleRelative(() -> Commands.turnOff(hub, 1), 5);

        TriggerManager tm = new TriggerManager();
        Thermostat t2 = (Thermostat) hub.getDevice(2);
        tm.addTrigger(new Trigger("temperature > 75",
            () -> t2.getTemperature() > 75,
            () -> Commands.turnOff(hub, 1)
        ));

        t2.setTemperature(80);
        tm.evaluateAll();

        hub.reportStatus();

        // Keep main alive for scheduler
        Thread.sleep(6000);
        hub.reportStatus();
    }
}
