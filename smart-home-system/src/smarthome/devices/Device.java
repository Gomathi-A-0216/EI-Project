package smarthome.devices;

public interface Device {
    int getId();
    String getType();
    String getStatus();
    void onNotification(String message);
}
