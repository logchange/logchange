package dev.logchange.cli;

import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.GENERATE_COMMAND;

@Command(name = GENERATE_COMMAND, description = "Generates changelog")
public class GenerateChangelogCommand implements Runnable {

    public void run() {
        System.out.println("Starting generating changelog");
    }

}
