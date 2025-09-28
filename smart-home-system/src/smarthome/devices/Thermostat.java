package smarthome.devices;

public class Thermostat extends AbstractDevice {
    private double temperature;

    public Thermostat(int id, double initialTemp) {
        super(id, "thermostat");
        this.temperature = initialTemp;
        this.status = "Temperature set to " + initialTemp;
    }

    public void setTemperature(double temp) {
        proxy.perform(() -> {
            temperature = temp;
            status = "Temperature set to " + temp;
        }, "Thermostat " + id + " temperature set to " + temp);
    }

    public double getTemperature() { return temperature; }
}
