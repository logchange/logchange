package dev.logchange.utils.logger;

public class LogchangeLogger {

    private static LoggerLevel level = LoggerLevel.INFO;

    private static final LogchangeLogger instance = new LogchangeLogger();

    public static LogchangeLogger getLogger(Class<?> type) {
        return instance;
    }

    public void info(String msg) {
        if (level.isEnabled(LoggerLevel.INFO)) {
            System.out.println(msg);
        }
    }

    public void debug(String msg) {
        if (level.isEnabled(LoggerLevel.DEBUG)) {
            System.out.println("[DEBUG]" + msg);
        }
    }

    public static void setLevel(LoggerLevel newLevel) {
        instance.info("Setting log level to: " + newLevel);
        level = newLevel;
    }

}
