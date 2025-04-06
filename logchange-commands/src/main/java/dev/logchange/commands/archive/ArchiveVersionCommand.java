package dev.logchange.commands.archive;

import dev.logchange.core.application.changelog.repository.ChangelogPersistence;
import dev.logchange.core.application.changelog.repository.ChangelogQuery;
import dev.logchange.core.application.changelog.service.archive.ArchiveService;
import dev.logchange.core.application.config.ConfigFile;
import dev.logchange.core.application.file.Dir;
import dev.logchange.core.domain.changelog.command.ArchiveUseCase;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.archive.FileArchiveRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import dev.logchange.core.infrastructure.query.file.FileReader;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static dev.logchange.commands.Constants.ARCHIVE_FILE;
import static dev.logchange.core.domain.changelog.command.ArchiveUseCase.ArchiveCommand;

@CustomLog
@RequiredArgsConstructor(staticName = "of")
public class ArchiveVersionCommand {

    private final String rootPath;
    private final String inputDir;
    private final String version;
    private final String configFile;

    public void execute() {
        log.info("Started archiving version " + version);

        File changelogDirectory = Dir.find(rootPath + "/" + inputDir);

        String configPath = rootPath + "/" + inputDir + "/" + configFile;
        Config config = ConfigFile.find(configPath).orElseGet(() -> {
            log.info("There is no config file:  " + configPath + " for this project, using defaults");
            return Config.EMPTY;
        });
        File archive = createIfNotExists(changelogDirectory);
        FileRepository fr = FileRepository.of(archive);
        ChangelogPersistence changelogPersistence = new FileArchiveRepository(fr, config);
        ChangelogQuery changelogQuery = new FileChangelogRepository(rootPath, changelogDirectory, config, new FileReader(), fr, fr);

        ArchiveUseCase archiveService = new ArchiveService(changelogPersistence, changelogQuery);
        ArchiveCommand command = ArchiveCommand.of(Version.of(version));

        List<String> archivedFiles = archiveService.handle(command);
        deleteArchivedFiles(archivedFiles, changelogDirectory);
        log.info("Archiving of version " + version + " successful!");
    }

    private static File createIfNotExists(File changelogDirectory) {
        File archive = new File(changelogDirectory.getPath() + "/"  + ARCHIVE_FILE);
        if (!archive.exists()) {
            try {
                archive.createNewFile();
            } catch (IOException e) {
                String msg = String.format("An error occurred while creating empty archive file: %s", e.getMessage());
                log.error(msg);
                throw new RuntimeException(msg);
            }
        }
        return archive;
    }

    private static void deleteArchivedFiles(List<String> archivedFiles, File changelogDirectory) {
        log.info("Deleting archived files..");
        archivedFiles.stream()
                .filter(fn -> !ARCHIVE_FILE.equals(fn))
                .forEach(fileName -> {
                    File file = Paths.get(changelogDirectory.getPath(), fileName).toFile();
                    if (file.isDirectory()) {
                        Dir.delete(file.toPath());
                    }
                    if (file.isFile()) {
                        file.delete();
                    }
                });
    }
}
