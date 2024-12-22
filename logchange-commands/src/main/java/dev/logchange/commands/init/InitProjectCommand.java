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
import java.nio.file.Path;
import java.nio.file.Paths;

import static dev.logchange.commands.Constants.DEFAULT_CONFIG_FILE;
import static dev.logchange.commands.Constants.GIT_KEEP;

@Log
@RequiredArgsConstructor(staticName = "of")
public class InitProjectCommand {

    private final String rootPath;
    private final String inputDir;
    private final String unreleasedVersionDir;
    private final String outputFile;

    public void execute() {
        log.info("Initializing project");
        checkIfRootExists(rootPath);
        createEmptyChangelogFile(Paths.get(rootPath, outputFile));
        createUnreleased(rootPath, inputDir, unreleasedVersionDir);
        createConfig(rootPath, inputDir);
        log.info("Project initialized");
    }

    private void checkIfRootExists(String rootPath) {
        if (!new File(rootPath).exists()) {
            String msg = String.format("Root path: %s must exists! Check if you are in right directory!", rootPath);
            log.severe(msg);
            throw new RuntimeException(msg);
        }
    }

    private void createConfig(String rootPath, String inputDir) {
        log.info("Creating config file");
        File config = ConfigFile.of(Paths.get(rootPath, inputDir, DEFAULT_CONFIG_FILE)).create();
        ConfigRepository configRepository = new FileConfigRepository(config);
        configRepository.save(Config.EMPTY);
    }

    public static void createUnreleased(String rootPath, String inputDir, String unreleasedVersionDir) {
        log.info("Creating unreleased directory");
        Path inputDirPath = Paths.get(rootPath, inputDir);
        Dir.of(inputDirPath).create();
        Path unreleasedVersionDirPath = Paths.get(rootPath, inputDir, unreleasedVersionDir);
        Dir.of(unreleasedVersionDirPath).create();
        Path gitKeepPath = Paths.get(rootPath, inputDir, unreleasedVersionDir, GIT_KEEP);
        GitKeep.of(gitKeepPath).create();
    }

    private static void createEmptyChangelogFile(Path path) {
        try {
            File changelog = path.toFile();
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
