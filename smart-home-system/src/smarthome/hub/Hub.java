package smarthome.hub;

import smarthome.devices.Device;
import java.util.*;

public class Hub {
    private Map<Integer, Device> devices = new HashMap<>();

    public void registerDevice(Device device) {
        devices.put(device.getId(), device);
        System.out.printf("[Hub] Registered device %s with ID %d%n", device.getType(), device.getId());
    }

    public void unregisterDevice(int id) {
        devices.remove(id);
        System.out.printf("[Hub] Unregistered device with ID %d%n", id);
    }

    public Device getDevice(int id) { return devices.get(id); }

    public Collection<Device> getAllDevices() { return devices.values(); }

    public void notifyAll(String message) {
        for (Device d : devices.values()) {
            d.onNotification(message);
        }
    }

    public void reportStatus() {
        for (Device d : devices.values()) {
            System.out.printf("[Status] %s %d: %s%n", d.getType(), d.getId(), d.getStatus());
        }
    }
}
