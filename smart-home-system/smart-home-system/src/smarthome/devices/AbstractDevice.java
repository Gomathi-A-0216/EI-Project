package smarthome.devices;

import smarthome.proxy.DeviceProxy;

public abstract class AbstractDevice implements Device {
    protected int id;
    protected String type;
    protected String status;
    protected DeviceProxy proxy;

    public AbstractDevice(int id, String type) {
        this.id = id;
        this.type = type;
        this.status = "unknown";
        this.proxy = new DeviceProxy(this);
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getType() { return type; }

    @Override
    public String getStatus() { return status; }

    @Override
    public void onNotification(String message) {
        System.out.printf("[Device %d - %s] Notification: %s%n", id, type, message);
    }
}
