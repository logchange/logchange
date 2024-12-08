package dev.logchange.commands.init;

import dev.logchange.core.application.config.ConfigFile;
import dev.logchange.core.application.config.ConfigRepository;
import dev.logchange.core.application.file.Dir;
import dev.logchange.core.application.file.GitKeep;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.config.FileConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;

import static dev.logchange.commands.Constants.DEFAULT_CONFIG_FILE;
import static dev.logchange.commands.Constants.GIT_KEEP;

@Log
@RequiredArgsConstructor(staticName = "of")
public class InitProjectCommand {

    private final String inputDir;
    private final String unreleasedVersionDir;
    private final String outputFile;

    public void execute() {
        log.info("Initializing project");
        createEmptyChangelogFile(outputFile);
        createUnreleased(inputDir, unreleasedVersionDir);
        createConfig(inputDir);
        log.info("Project initialized");
    }

    private void createConfig(String inputDir) {
        log.info("Creating config file");
        File config = ConfigFile.of(inputDir).create(DEFAULT_CONFIG_FILE);
        ConfigRepository configRepository = new FileConfigRepository(config);
        configRepository.save(Config.EMPTY);
    }

    public static void createUnreleased(String inputDir, String unreleasedVersionDir) {
        log.info("Creating unreleased directory");
        Dir.of(inputDir).create();
        Dir.of(inputDir + "/" + unreleasedVersionDir).create();
        GitKeep.of(inputDir + "/" + unreleasedVersionDir + "/").create(GIT_KEEP);
    }

    private static void createEmptyChangelogFile(String path) {
        try {
            File changelog = new File(path);
            if (changelog.createNewFile()) {
                log.info("Created: " + changelog.getName());
            } else {
                log.warning(changelog.getName() + " already exists.");
            }
        } catch (IOException e) {
            String msg = String.format("An error occurred while creating empty changelog: %s", e.getMessage());
            log.severe(msg);
            throw new RuntimeException(msg);
        }
    }
}
