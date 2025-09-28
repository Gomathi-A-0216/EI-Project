package com.example.patterns.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;

public class LoggerFactory {
    public static Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return String.format("%1$tF %1$tT %2$-7s %3$s%n", record.getMillis(), record.getLevel().getName(), record.getMessage());
            }
        });
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
        return logger;
    }
}
