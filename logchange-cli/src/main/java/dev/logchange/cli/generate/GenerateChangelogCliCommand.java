package dev.logchange.cli.generate;

import dev.logchange.cli.BaseCommand;
import dev.logchange.commands.generate.GenerateProjectCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = GENERATE_COMMAND,
        description = GENERATE_COMMAND_DESCRIPTION,
        separator = OPTION_SEPARATOR,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class GenerateChangelogCliCommand extends BaseCommand {

    @Option(defaultValue = DEFAULT_INPUT_DIR, names = INPUT_DIR_OPTION, description = INPUT_DIR_OPTION_DESCRIPTION)
    private String inputDir;

    @Option(defaultValue = DEFAULT_OUTPUT_FILE, names = OUTPUT_FILE_OPTION, description = OUTPUT_FILE_OPTION_DESCRIPTION)
    private String outputFile;

    @Option(defaultValue = DEFAULT_CONFIG_FILE, names = CONFIG_FILE_OPTION, description = CONFIG_FILE_OPTION_DESCRIPTION)
    private String configFile;

    @Override
    public void runCommand() {
        log.info(GENERATE_COMMAND_START_LOG);
        GenerateProjectCommand.of(path(), inputDir, outputFile, configFile).execute(false);
        log.info(GENERATE_COMMAND_END_LOG);
    }

}
