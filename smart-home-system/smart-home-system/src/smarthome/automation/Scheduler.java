package smarthome.automation;

import java.util.*;

public class Scheduler {
    private final Timer timer = new Timer();

    public void scheduleRelative(Runnable task, int delaySeconds) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() { task.run(); }
        }, delaySeconds * 1000L);
    }
}
