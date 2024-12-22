package dev.logchange.cli.add;

import lombok.CustomLog;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = ADD_COMMAND, description = ADD_COMMAND_DESCRIPTION)
public class AddChangelogEntryCliCommand implements Runnable {

    public void run() {
        log.info(ADD_COMMAND_START_LOG);

        //TODO

        log.info(ADD_COMMAND_END_LOG);
    }

}
