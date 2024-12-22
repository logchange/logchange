package dev.logchange.cli.init;

import lombok.CustomLog;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = INIT_COMMAND, description = INIT_COMMAND_DESCRIPTION)
public class InitCliCommand implements Runnable {

    public void run() {
        log.info(INIT_COMMAND_START_LOG);

        //TODO

        log.info(INIT_COMMAND_END_LOG);
    }

}
