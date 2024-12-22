package dev.logchange.cli;

import dev.logchange.cli.add.AddChangelogEntryCliCommand;
import dev.logchange.cli.aggregate.AggregateProjectsCliCommand;
import dev.logchange.cli.generate.GenerateChangelogCliCommand;
import dev.logchange.cli.init.InitCliCommand;
import dev.logchange.cli.lint.LintCliCommand;
import dev.logchange.cli.release.ReleaseVersionCliCommand;
import lombok.extern.java.Log;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Log
@Command(name = "logchange",
        description = "logchange (CLI) - \uD83E\uDEB5 logchange is a tool which helps creating CHANGELOG by keeping one format and solving merge request conflicts problem by extraction of new CHANGELOG entries to separate files. \n\n Visit https://logchange.dev/ for more information\n",
        mixinStandardHelpOptions = true,
        subcommands = {
                InitCliCommand.class,
                GenerateChangelogCliCommand.class,
                ReleaseVersionCliCommand.class,
                AddChangelogEntryCliCommand.class,
                LintCliCommand.class,
                AggregateProjectsCliCommand.class
        })
public class LogchangeCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "Sets log level to DEBUG", defaultValue = "false")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new LogchangeCliCommand()).execute(args);
        System.exit(exitCode);
    }

    public void run() {
        Version version = new Version("1.0.0");
        System.out.println("Hi! " + version);
        log.info("test1");
        if (verbose) {
            log.info("test2");
            log.finest("test - debug");
        }
    }


    record Version(String version) {

    }
}
