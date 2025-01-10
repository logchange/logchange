package dev.logchange.cli;

import dev.logchange.utils.logger.LogchangeLogger;
import dev.logchange.utils.logger.LoggerLevel;
import lombok.CustomLog;

import static picocli.CommandLine.Option;

@CustomLog
public abstract class BaseCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "Sets log level to DEBUG", defaultValue = "false")
    boolean verbose;

    @Override
    public void run() {
        try {
            if (verbose) {
                LogchangeLogger.setLevel(LoggerLevel.DEBUG);
            }
            runCommand();
        } catch (Exception e) {
            log.debug(e);
            throw new RuntimeException(e);
        }
    }

    public abstract void runCommand();
}
