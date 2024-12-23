package dev.logchange.cli.lint;

import dev.logchange.cli.BaseCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = LINT_COMMAND,
        description = LINT_COMMAND_DESCRIPTION,
        separator = OPTION_SEPARATOR,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class LintCliCommand extends BaseCommand {

    public void runCommand() {
        log.info(LINT_COMMAND_START_LOG);

        //TODO

        log.info(LINT_COMMAND_END_LOG);
    }

}
