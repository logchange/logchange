package dev.logchange.cli.add;

import dev.logchange.cli.BaseCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = ADD_COMMAND,
        description = ADD_COMMAND_DESCRIPTION,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class AddChangelogEntryCliCommand extends BaseCommand {

    public void runCommand() {
        log.info(ADD_COMMAND_START_LOG);

        //TODO

        log.info(ADD_COMMAND_END_LOG);
    }

}
