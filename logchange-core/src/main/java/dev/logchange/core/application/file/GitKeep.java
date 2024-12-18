package dev.logchange.core.application.file;

import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;

@Log
public class GitKeep {
    private final String path;

    private GitKeep(String path) {
        this.path = path;
    }

    public static GitKeep of(String path) {
        return new GitKeep(path);
    }

    public void create(String gitKeepFileName) {
        File gitKeep = new File(path + gitKeepFileName);
        try {
            if (gitKeep.createNewFile()) {
                log.info("Created: " + gitKeep.getName());
            } else {
                log.warning(gitKeep.getName() + " already exists.");
            }
        } catch (IOException e) {
            String msg = String.format("An error occurred while creating %s in path: %s - %s", gitKeepFileName, path, e.getMessage());
            log.severe(msg);
        }
    }
}
