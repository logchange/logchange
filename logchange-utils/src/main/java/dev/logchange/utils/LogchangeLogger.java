package dev.logchange.utils;

public class LogchangeLogger {

    private static final LogchangeLogger instance = new LogchangeLogger();

    public static LogchangeLogger getLogger(Class<?> type) {
        return instance;
    }

    public void info(String msg) {
        System.out.println(msg);
    }
}
