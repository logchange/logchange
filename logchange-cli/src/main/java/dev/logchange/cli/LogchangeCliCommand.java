package dev.logchange.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "logchange-cli", description = "...", mixinStandardHelpOptions = true)
public class LogchangeCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(LogchangeCliCommand.class, args);
    }

    public void run() {
        // business logic here
        System.getProperties().list(System.out);

        Version version = new Version("1.0.0");
        if (verbose) {
            System.out.println("Hi! " + version);
        }
    }


    record Version(String version) {

    }
}
