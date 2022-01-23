package dev.logchange.maven_plugin.util;

import org.apache.maven.plugin.logging.Log;

import java.io.File;

public class Dir {
    private final Log log;
    private final String path;

    private Dir(Log log, String path) {
        this.log = log;
        this.path = path;
    }

    public static Dir of(Log log, String path){
        return new Dir(log, path);
    }

    public void create(){
        File newDir = new File(path);
        if (!newDir.exists()) {
            if (newDir.mkdir()) {
                log.info("Created: " + newDir.getName());
            } else {
                log.warn(newDir.getName() + " cannot be created.");
            }
        } else {
            log.warn(newDir.getName() + " already exists.");
        }
    }
}
