package dev.logchange.maven_plugin.mojo.init;

import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.IOException;

import static dev.logchange.maven_plugin.Constants.DEFAULT_CONFIG_FILE;

public class ConfigFile {

    private final Log log;
    private final String path;

    private ConfigFile(Log log, String path) {
        this.log = log;
        this.path = path;
    }

    public static ConfigFile of(Log log, String path) {
        return new ConfigFile(log, path);
    }

    public File create(){
        File changelogConfig = new File(path + "/" + DEFAULT_CONFIG_FILE);
        try {
            if (changelogConfig.createNewFile()) {
                log.info("Created: " + changelogConfig.getName());
            } else {
                log.warn(changelogConfig.getName() + " already exists.");
            }
            return changelogConfig;
        } catch (IOException e) {
            String msg = "An error occurred while creating " + DEFAULT_CONFIG_FILE + " in path: " + path;
            log.error(msg, e);
            throw new RuntimeException(msg);
        }
    }
}
