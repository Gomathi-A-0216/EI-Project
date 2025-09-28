package smarthome.factory;

import smarthome.devices.*;

import java.util.Map;

public class DeviceFactory {
    public static Device createDevice(Map<String, Object> config) {
        int id = (int) config.get("id");
        String type = (String) config.get("type");

        switch (type) {
            case "light": return new Light(id);
            case "thermostat":
                double temp = config.get("temperature") != null ? (double) config.get("temperature") : 70.0;
                return new Thermostat(id, temp);
            case "door": return new DoorLock(id);
            default: throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}
