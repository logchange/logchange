package dev.logchange.commands.archive;

import lombok.CustomLog;
import lombok.RequiredArgsConstructor;

@CustomLog
@RequiredArgsConstructor
public class ArchiveVersionCommand {

    private final String rootPath;
    private final String inputDir;
    private final String version;

    public void execute() {
        log.info("Started archiving version " + version);

        log.info("Archiving of version " + version + " successful");
    }
}
