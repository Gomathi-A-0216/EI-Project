package smarthome.devices;

public class DoorLock extends AbstractDevice {
    public DoorLock(int id) {
        super(id, "door");
        this.status = "locked";
    }

    public void lock() { proxy.perform(() -> status = "locked", "Door " + id + " locked"); }
    public void unlock() { proxy.perform(() -> status = "unlocked", "Door " + id + " unlocked"); }
}
