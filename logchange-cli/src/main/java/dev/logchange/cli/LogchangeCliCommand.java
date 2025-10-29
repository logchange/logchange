package dev.logchange.cli;

import dev.logchange.cli.add.AddChangelogEntryCliCommand;
import dev.logchange.cli.add.AddExampleChangelogEntryCliCommand;
import dev.logchange.cli.aggregate.AggregateProjectsCliCommand;
import dev.logchange.cli.archive.ArchiveVersionCliCommand;
import dev.logchange.cli.generate.GenerateChangelogCliCommand;
import dev.logchange.cli.init.InitCliCommand;
import dev.logchange.cli.lint.LintCliCommand;
import dev.logchange.cli.release.ReleaseVersionCliCommand;
import lombok.CustomLog;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.BASIC_FOOTER;
import static dev.logchange.commands.Constants.LOGCHANGE_DESCRIPTION;

@CustomLog
@Command(name = "logchange",
        description = LOGCHANGE_DESCRIPTION,
        mixinStandardHelpOptions = true,
        versionProvider = LogchangeVersionProvider.class,
        footer = BASIC_FOOTER,
        showDefaultValues = true,
        subcommands = {
                InitCliCommand.class,
                GenerateChangelogCliCommand.class,
                ReleaseVersionCliCommand.class,
                AddChangelogEntryCliCommand.class,
                AddExampleChangelogEntryCliCommand.class,
                LintCliCommand.class,
                AggregateProjectsCliCommand.class,
                ArchiveVersionCliCommand.class
        })
public class LogchangeCliCommand implements Runnable {

    private static CommandLine commandLine;

    public static void main(String[] args) {
        commandLine = new CommandLine(new LogchangeCliCommand());
        commandLine.setExecutionExceptionHandler((ex, cmd, parseResult) -> {
            log.error(String.format("Command %s execution failed", cmd.getCommandName()));
            log.error(ex.getMessage());
            return 1;
        });
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        commandLine.usage(System.out);
    }

}
