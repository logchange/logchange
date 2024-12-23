package dev.logchange.core.application.file;

import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Log
public class GitKeep {
    private final Path path;

    private GitKeep(Path path) {
        this.path = path;
    }

    public static GitKeep of(Path path) {
        return new GitKeep(path);
    }

    public void create() {
        File gitKeep = path.toFile();
        try {
            if (gitKeep.createNewFile()) {
                log.info("Created: " + gitKeep.getName());
            } else {
                log.warning(gitKeep.getName() + " already exists.");
            }
        } catch (IOException e) {
            String msg = String.format("An error occurred while creating file: %s msg: %s", path, e.getMessage());
            log.severe(msg);
        }
    }
}
