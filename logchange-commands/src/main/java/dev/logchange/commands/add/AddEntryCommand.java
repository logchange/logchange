package dev.logchange.commands.add;

import dev.logchange.core.application.changelog.repository.ChangelogEntryRepository;
import dev.logchange.core.application.changelog.service.add.AddChangelogEntryService;
import dev.logchange.core.domain.changelog.command.AddChangelogEntryUseCase;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogEntryRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import lombok.AccessLevel;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;

@CustomLog
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AddEntryCommand {

    private final String rootPath;
    private final String inputDir;
    private final String unreleasedVersionDir;

    public static AddEntryCommand of(String rootPath, String inputDir, String unreleasedVersionDir) {
        checkIfCanAdd(rootPath, inputDir, unreleasedVersionDir);
        return new AddEntryCommand(rootPath, inputDir, unreleasedVersionDir);
    }

    public void execute(ChangelogEntry entry, String outputFile) {
        log.info("Adding new entry");
        String path = rootPath + "/" + inputDir + "/" + unreleasedVersionDir + "/" + outputFile;
        File entryFile = createFile(path);

        log.debug(entry.toString());

        ChangelogEntryRepository repository = new FileChangelogEntryRepository(FileRepository.of(entryFile));
        AddChangelogEntryUseCase addChangelogEntry = new AddChangelogEntryService(repository);
        AddChangelogEntryUseCase.AddChangelogEntryCommand command = AddChangelogEntryUseCase.AddChangelogEntryCommand.of(entry);

        addChangelogEntry.handle(command);
        log.info("Entry saved");
    }

    private File createFile(String path) {
        try {
            File changelog = new File(path);
            if (changelog.createNewFile()) {
                log.info("Created: " + changelog.getName());
                return changelog;
            } else {
                String msg = "Entry with name: " + changelog.getName() + "  already exists!";
                log.warn(msg);
                throw new RuntimeException(msg);
            }
        } catch (IOException e) {
            String msg = String.format("An error occurred while creating empty changelog entry file with path: %s - %s", path, e.getMessage());
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }

    private static void checkIfCanAdd(String rootPath, String inputDir, String unreleasedVersionDir) {
        String path = rootPath + "/" + inputDir + "/" + unreleasedVersionDir;
        File unreleasedDir = new File(path);

        if (!unreleasedDir.exists() || !unreleasedDir.isDirectory()) {
            String msg = "Cannot add new entry if " + path + " not exists nor is directory";
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }
}
