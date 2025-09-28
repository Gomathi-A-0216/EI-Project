package smarthome.devices;

public class Light extends AbstractDevice {
    public Light(int id) {
        super(id, "light");
        this.status = "off";
    }

    public void turnOn() { proxy.perform(() -> status = "on", "Light " + id + " turned ON"); }
    public void turnOff() { proxy.perform(() -> status = "off", "Light " + id + " turned OFF"); }
}
