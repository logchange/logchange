package dev.logchange.cli.generate;

import dev.logchange.cli.BaseCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = GENERATE_COMMAND,
        description = GENERATE_COMMAND_DESCRIPTION,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class GenerateChangelogCliCommand extends BaseCommand {

    public void runCommand() {
        log.info(GENERATE_COMMAND_START_LOG);

        //TODO

        log.info(GENERATE_COMMAND_END_LOG);
    }

}
