package dev.logchange.cli.lint;

import dev.logchange.cli.BaseCommand;
import dev.logchange.commands.lint.LintProjectCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = LINT_COMMAND,
        description = LINT_COMMAND_DESCRIPTION,
        separator = OPTION_SEPARATOR,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class LintCliCommand extends BaseCommand {

    @Option(defaultValue = DEFAULT_INPUT_DIR, names = INPUT_DIR_OPTION, description = INPUT_DIR_OPTION_DESCRIPTION)
    private String inputDir;

    @Option(defaultValue = DEFAULT_OUTPUT_FILE, names = OUTPUT_FILE_OPTION, description = OUTPUT_FILE_OPTION_DESCRIPTION)
    private String outputFile;

    @Option(defaultValue = DEFAULT_CONFIG_FILE, names = CONFIG_FILE_OPTION, description = CONFIG_FILE_OPTION_DESCRIPTION)
    private String configFile;

    public void runCommand() {
        log.info(LINT_COMMAND_START_LOG);
        LintProjectCommand.of(DEFAULT_PATH, inputDir, outputFile, configFile).validate();
        log.info(LINT_COMMAND_END_LOG);
    }

}
