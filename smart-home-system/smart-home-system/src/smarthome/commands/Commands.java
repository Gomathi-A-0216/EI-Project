package smarthome.commands;

import smarthome.hub.Hub;
import smarthome.devices.*;

public class Commands {
    public static void turnOn(Hub hub, int id) {
        Device d = hub.getDevice(id);
        if (d instanceof Light) { ((Light)d).turnOn(); }
    }

    public static void turnOff(Hub hub, int id) {
        Device d = hub.getDevice(id);
        if (d instanceof Light) { ((Light)d).turnOff(); }
    }

    public static void lockDoor(Hub hub, int id) {
        Device d = hub.getDevice(id);
        if (d instanceof DoorLock) { ((DoorLock)d).lock(); }
    }

    public static void unlockDoor(Hub hub, int id) {
        Device d = hub.getDevice(id);
        if (d instanceof DoorLock) { ((DoorLock)d).unlock(); }
    }

    public static void setThermostat(Hub hub, int id, double temp) {
        Device d = hub.getDevice(id);
        if (d instanceof Thermostat) { ((Thermostat)d).setTemperature(temp); }
    }
}
