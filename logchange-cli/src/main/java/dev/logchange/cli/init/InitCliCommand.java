package dev.logchange.cli.init;

import dev.logchange.cli.BaseCommand;
import dev.logchange.commands.init.InitProjectCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = INIT_COMMAND,
        description = INIT_COMMAND_DESCRIPTION,
        separator = OPTION_SEPARATOR,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class InitCliCommand extends BaseCommand {

    @Option(defaultValue = DEFAULT_INPUT_DIR, names = INPUT_DIR_OPTION, description = INPUT_DIR_OPTION_DESCRIPTION)
    private String inputDir;

    @Option(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, names = UNRELEASED_VERSION_DIR_OPTION, description = UNRELEASED_VERSION_DIR_OPTION_DESCRIPTION)
    private String unreleasedVersionDir;

    @Option(defaultValue = DEFAULT_OUTPUT_FILE, names = OUTPUT_FILE_OPTION, description = OUTPUT_FILE_OPTION_DESCRIPTION)
    private String outputFile;

    @Override
    public void runCommand() {
        log.info(INIT_COMMAND_START_LOG);
        InitProjectCommand.of(path(), inputDir, unreleasedVersionDir, outputFile).execute();
        log.info(INIT_COMMAND_END_LOG);
    }

}
