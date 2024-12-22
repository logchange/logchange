package dev.logchange.cli.init;

import dev.logchange.commands.init.InitProjectCommand;
import dev.logchange.utils.logger.LogchangeLogger;
import dev.logchange.utils.logger.LoggerLevel;
import lombok.CustomLog;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = INIT_COMMAND,
        description = INIT_COMMAND_DESCRIPTION,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class InitCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "Sets log level to DEBUG", defaultValue = "false")
    boolean verbose;

    @Option(defaultValue = DEFAULT_INPUT_DIR, names = INPUT_DIR_OPTION, description = INPUT_DIR_OPTION_DESCRIPTION)
    private String inputDir;

    @Option(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, names = UNRELEASED_VERSION_DIR_OPTION, description = UNRELEASED_VERSION_DIR_OPTION_DESCRIPTION)
    private String unreleasedVersionDir;

    @Option(defaultValue = DEFAULT_OUTPUT_FILE, names = OUTPUT_FILE_OPTION, description = OUTPUT_FILE_OPTION_DESCRIPTION)
    private String outputFile;

    public void run() {
        if (verbose) {
            LogchangeLogger.setLevel(LoggerLevel.DEBUG);
        }

        log.info(INIT_COMMAND_START_LOG);
        InitProjectCommand.of(inputDir, unreleasedVersionDir, outputFile).execute();
        log.info(INIT_COMMAND_END_LOG);
    }

}
