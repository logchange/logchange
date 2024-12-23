package dev.logchange.cli.aggregate;

import dev.logchange.cli.BaseCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = AGGREGATE_COMMAND,
        description = AGGREGATE_COMMAND_DESCRIPTION,
        separator = OPTION_SEPARATOR,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class AggregateProjectsCliCommand extends BaseCommand {

    @Option(names = AGGREGATE_VERSION_OPTION, description = AGGREGATE_VERSION_OPTION_DESCRIPTION, required = true)
    private String aggregateVersion;

    @Option(defaultValue = DEFAULT_INPUT_DIR, names = INPUT_DIR_OPTION, description = INPUT_DIR_OPTION_DESCRIPTION)
    private String inputDir;

    @Option(defaultValue = DEFAULT_CONFIG_FILE, names = CONFIG_FILE_OPTION, description = CONFIG_FILE_OPTION_DESCRIPTION)
    private String configFile;

    public void runCommand() {
        log.info(AGGREGATE_COMMAND_START_LOG);

        //TODO

        log.info(AGGREGATE_COMMAND_END_LOG);
    }

}
