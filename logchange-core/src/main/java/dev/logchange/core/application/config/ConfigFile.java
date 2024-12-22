package dev.logchange.core.application.config;

import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.config.FileConfigRepository;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@Log
public class ConfigFile {

    private final Path path;

    private ConfigFile(Path path) {
        this.path = path;
    }

    public static ConfigFile of(Path path) {
        return new ConfigFile(path);
    }

    public File create() {
        File changelogConfig = path.toFile();
        try {
            if (changelogConfig.createNewFile()) {
                log.info("Created: " + changelogConfig.getName());
                return changelogConfig;
            } else {
                String msg = changelogConfig.getName() + " already exists";
                log.warning(msg);
                if (changelogConfig.isFile()) {
                    return changelogConfig;
                } else {
                    String msg2 = path.getFileName() + " is not a file! (probably it is directory)";
                    log.severe(msg2);
                    throw new RuntimeException(msg + " and " + msg2);
                }
            }
        } catch (IOException e) {
            String msg = String.format("An error occurred while creating %s in path: %s", path, e.getMessage());
            log.severe(msg);
            throw new RuntimeException(msg);
        }
    }

    public static Optional<Config> find(String path) {
        File configFile = new File(path);

        if (!configFile.exists()) {
            return Optional.empty();
        }

        if (configFile.isDirectory()) {
            String msg = "File " + path + " is a directory !!!";
            log.severe(msg);
            throw new RuntimeException(msg);
        }

        ConfigRepository configRepository = new FileConfigRepository(configFile);
        return Optional.of(configRepository.find());
    }
}
