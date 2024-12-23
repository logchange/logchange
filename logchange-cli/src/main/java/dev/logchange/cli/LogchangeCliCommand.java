package dev.logchange.cli;

import dev.logchange.cli.add.AddChangelogEntryCliCommand;
import dev.logchange.cli.aggregate.AggregateProjectsCliCommand;
import dev.logchange.cli.generate.GenerateChangelogCliCommand;
import dev.logchange.cli.init.InitCliCommand;
import dev.logchange.cli.lint.LintCliCommand;
import dev.logchange.cli.release.ReleaseVersionCliCommand;
import lombok.CustomLog;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.BASIC_FOOTER;

@CustomLog
@Command(name = "logchange",
        description = "logchange (CLI) - \uD83E\uDEB5 logchange is a tool which helps creating CHANGELOG by keeping one format and solving merge request conflicts problem by extraction of new CHANGELOG entries to separate files. \n\n Visit https://logchange.dev/ for more information\n",
        mixinStandardHelpOptions = true,
        versionProvider = LogchangeVersionProvider.class,
        footer = BASIC_FOOTER,
        showDefaultValues = true,
        subcommands = {
                InitCliCommand.class,
                GenerateChangelogCliCommand.class,
                ReleaseVersionCliCommand.class,
                AddChangelogEntryCliCommand.class,
                LintCliCommand.class,
                AggregateProjectsCliCommand.class
        })
public class LogchangeCliCommand implements Runnable {

    private static CommandLine commandLine;

    public static void main(String[] args) throws Exception {
        commandLine = new CommandLine(new LogchangeCliCommand());
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }

    public void run() {
        commandLine.usage(System.out);
    }

}
