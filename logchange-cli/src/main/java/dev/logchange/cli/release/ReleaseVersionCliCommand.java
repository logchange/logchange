package dev.logchange.cli.release;

import dev.logchange.commands.release.ReleaseVersionCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = RELEASE_COMMAND, description = RELEASE_COMMAND_DESCRIPTION)
public class ReleaseVersionCliCommand implements Runnable {

    public void run() {
        String version = ReleaseVersionCommand.getVersion("TODO");
        log.info(RELEASE_COMMAND_START_LOG + version);

        //TODO

        log.info(RELEASE_COMMAND_END_LOG);
    }

}
