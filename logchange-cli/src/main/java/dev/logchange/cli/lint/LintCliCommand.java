package dev.logchange.cli.lint;

import lombok.CustomLog;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = LINT_COMMAND, description = LINT_COMMAND_DESCRIPTION)
public class LintCliCommand implements Runnable {

    public void run() {
        log.info(LINT_COMMAND_START_LOG);

        //TODO

        log.info(LINT_COMMAND_END_LOG);
    }

}
