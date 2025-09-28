package smarthome.proxy;

public class DeviceProxy {
    private final Object device;
    private boolean accessAllowed = true;

    public DeviceProxy(Object device) { this.device = device; }

    public void perform(Runnable action, String description) {
        if (accessAllowed) {
            action.run();
            System.out.println("[Proxy] " + description);
        } else {
            System.out.println("[Proxy] Access denied to " + description);
        }
    }

    public void setAccess(boolean allowed) { this.accessAllowed = allowed; }
}
