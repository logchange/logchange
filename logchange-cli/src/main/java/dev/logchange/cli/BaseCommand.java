package dev.logchange.cli;

import dev.logchange.utils.logger.LogchangeLogger;
import dev.logchange.utils.logger.LoggerLevel;
import lombok.CustomLog;

import java.io.File;

import static dev.logchange.commands.Constants.*;
import static picocli.CommandLine.Option;

@CustomLog
public abstract class BaseCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "Sets log level to DEBUG", defaultValue = "false")
    boolean verbose;

    @Option(names = {PATH_SHORT_OPTION, PATH_OPTION}, description = PATH_OPTION_DESCRIPTION, defaultValue = DEFAULT_PATH)
    private File path;

    protected String path() {
        return path.getAbsolutePath();
    }

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
