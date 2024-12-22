package dev.logchange.cli;

import picocli.CommandLine.Command;

import static dev.logchange.commands.Constants.INIT_COMMAND;

@Command(name = INIT_COMMAND, description = "Initializing repository with basic logchange configuration")
public class InitCommand implements Runnable {

    public void run() {
        System.out.println("Starting initializing repository for logchange. We are happy that you have joined us :)");
    }

}
