package dev.logchange.cli;

import io.micronaut.configuration.picocli.PicocliRunner;

import lombok.extern.java.Log;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Log
@Command(name = "logchange",
        description = "logchange (CLI) - \uD83E\uDEB5 logchange is a tool which helps creating CHANGELOG by keeping one format and solving merge request conflicts problem by extraction of new CHANGELOG entries to separate files. \n\n Visit https://logchange.dev/\n",
        mixinStandardHelpOptions = true,
        subcommands = { InitCommand.class, GenerateChangelogCommand.class })
public class LogchangeCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(LogchangeCliCommand.class, args);
    }

    public void run() {
        Version version = new Version("1.0.0");
        System.out.println("Hi! " + version);
        log.info("test1");
        if (verbose) {
            log.finest("test - debug");
        }
    }


    record Version(String version) {

    }
}
