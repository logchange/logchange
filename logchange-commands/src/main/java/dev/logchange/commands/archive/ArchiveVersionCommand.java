package dev.logchange.commands.archive;

import dev.logchange.core.application.changelog.repository.ChangelogPersistence;
import dev.logchange.core.application.changelog.repository.ChangelogQuery;
import dev.logchange.core.application.changelog.service.archive.GenerateArchiveService;
import dev.logchange.core.application.config.ConfigFile;
import dev.logchange.core.application.file.Dir;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.archive.FileArchiveRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import dev.logchange.core.infrastructure.query.file.FileReader;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static dev.logchange.commands.Constants.ARCHIVE_FILE;
import static dev.logchange.core.domain.changelog.command.GenerateArchiveUseCase.GenerateArchiveCommand;

@CustomLog
@RequiredArgsConstructor(staticName = "of")
public class ArchiveVersionCommand {

    private final String rootPath;
    private final String inputDir;
    private final String version;
    private final String configFile;

    public void execute(boolean single) {
        log.info("Started archiving version " + version);

        File changelogDirectory = Dir.find(rootPath + "/" + inputDir);

        String configPath = rootPath + "/" + inputDir + "/" + configFile;
        Config config = ConfigFile.find(configPath).orElseGet(() -> {
            log.info("There is no config file:  " + configPath + " for this project, using defaults");
            return Config.EMPTY;
        });

        FileRepository fr = FileRepository.of(new File(ARCHIVE_FILE));
        ChangelogPersistence changelogPersistence = new FileArchiveRepository(fr, config);
        ChangelogQuery changelogQuery = new FileChangelogRepository(changelogDirectory, config, new FileReader(), fr, fr);

        GenerateArchiveService archiveService = new GenerateArchiveService(changelogPersistence, changelogQuery);
        GenerateArchiveCommand command = GenerateArchiveCommand.of(Version.of(version), single);

        List<String> archivedFiles = archiveService.handle(command);
        deleteArchivedFiles(archivedFiles, changelogDirectory);
        log.info("Archiving of version " + version + " successful!");
    }

    private static void deleteArchivedFiles(List<String> archivedFiles, File changelogDirectory) {
        log.info("Deleting archived files..");
        List<String> filesToBeRemoved = archivedFiles.stream()
                .filter(fn -> !ARCHIVE_FILE.equals(fn))
                .collect(Collectors.toList());

        for (String fileName : filesToBeRemoved) {
            File file = Paths.get(changelogDirectory.getPath(), fileName).toFile();
            if (file.isDirectory()) {
                Dir.delete(file.toPath());
            }
            if (file.isFile()) {
                file.delete();
            }
        }
    }
}
