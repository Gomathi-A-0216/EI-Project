package smarthome.automation;

public class Trigger {
    private final String conditionDescription;
    private final Runnable action;
    private final java.util.function.Supplier<Boolean> condition;

    public Trigger(String desc, java.util.function.Supplier<Boolean> condition, Runnable action) {
        this.conditionDescription = desc;
        this.condition = condition;
        this.action = action;
    }

    public void evaluate() {
        if (condition.get()) {
            System.out.println("[Trigger Fired] Condition: " + conditionDescription);
            action.run();
        }
    }
}
