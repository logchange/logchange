package dev.logchange.cli.aggregate;

import lombok.CustomLog;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = AGGREGATE_COMMAND, description = AGGREGATE_COMMAND_DESCRIPTION, mixinStandardHelpOptions = true)
public class AggregateProjectsCliCommand implements Runnable {

    public void run() {
        log.info(AGGREGATE_COMMAND_START_LOG);

        //TODO

        log.info(AGGREGATE_COMMAND_END_LOG);
    }

}
