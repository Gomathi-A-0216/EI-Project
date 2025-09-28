package smarthome.automation;

import java.util.*;

public class TriggerManager {
    private final List<Trigger> triggers = new ArrayList<>();

    public void addTrigger(Trigger t) { triggers.add(t); }

    public void evaluateAll() {
        for (Trigger t : triggers) {
            t.evaluate();
        }
    }
}
