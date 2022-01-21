package dev.logchange.maven_plugin.util;

import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.IOException;

import static dev.logchange.maven_plugin.Constants.GIT_KEEP;

public class GitKeep {
    private final Log log;
    private final String path;

    private GitKeep(Log log, String path) {
        this.log = log;
        this.path = path;
    }

    public static GitKeep of(Log log, String path){
        return new GitKeep(log, path);
    }

    public void create(){
        File gitKeep = new File(path + GIT_KEEP);
        try {
            if (gitKeep.createNewFile()) {
                log.info("Created: " + gitKeep.getName());
            } else {
                log.warn(gitKeep.getName() + " already exists.");
            }
        } catch (IOException e) {
            log.error("An error occurred while creating " + GIT_KEEP + " in path: " + path, e);
        }
    }
}
