package dev.logchange.cli.aggregate;

import dev.logchange.cli.BaseCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = AGGREGATE_COMMAND,
        description = AGGREGATE_COMMAND_DESCRIPTION,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class AggregateProjectsCliCommand extends BaseCommand {

    public void runCommand() {
        log.info(AGGREGATE_COMMAND_START_LOG);

        //TODO

        log.info(AGGREGATE_COMMAND_END_LOG);
    }

}
