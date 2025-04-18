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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        checkIfRootExists();
        createUnreleased(rootPath, inputDir, unreleasedVersionDir);
        createNewChangelogFile();
        createConfig();
        log.info("Project initialized");
    }

    private void checkIfRootExists() {
        if (!new File(rootPath).exists()) {
            String msg = String.format("Root path: %s must exists! Check if you are in right directory!", rootPath);
            log.severe(msg);
            throw new RuntimeException(msg);
        }
    }

    private void createNewChangelogFile() {
        log.info("Creating new changelog file");
        File changelog = Paths.get(rootPath, outputFile).toFile();
        if (changelog.exists()) {
            archiveOldChangelog(changelog);
        }
        createEmptyChangelogFile(changelog);
    }

    private void archiveOldChangelog(File changelog) {
        log.info("Archiving old " + changelog.getName());
        try {
            String archiveFilename = "archive.md";
            Path archivePath = Paths.get(rootPath, inputDir, archiveFilename);

            if (archivePath.toFile().exists()) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                archiveFilename = String.format("archive-%s.md", timestamp);
                archivePath = Paths.get(rootPath, inputDir, archiveFilename);
            }
            Files.move(changelog.toPath(), archivePath);
            log.info("Moved existing changelog to: " + archivePath);
        } catch (IOException e) {
            String msg = String.format("An error occurred while moving changelog: %s", e.getMessage());
            log.severe(msg);
            throw new RuntimeException(msg);
        }
    }

    private void createConfig() {
        log.info("Creating config file");
        File config = ConfigFile.of(Paths.get(rootPath, inputDir, DEFAULT_CONFIG_FILE)).create();
        ConfigRepository configRepository = FileConfigRepository.of(config);
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

    private static void createEmptyChangelogFile(File changelog) {
        try {
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
