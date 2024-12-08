package dev.logchange.commands.add;

import dev.logchange.core.application.changelog.repository.ChangelogEntryRepository;
import dev.logchange.core.application.changelog.service.add.AddChangelogEntryService;
import dev.logchange.core.domain.changelog.command.AddChangelogEntryUseCase;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogEntryRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;

@Log
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AddEntryCommand {

    private final String inputDir;
    private final String unreleasedVersionDir;

    public static AddEntryCommand of(String inputDir, String unreleasedVersionDir) {
        checkIfCanAdd(inputDir, unreleasedVersionDir);
        return new AddEntryCommand(inputDir, unreleasedVersionDir);
    }

    public void execute(ChangelogEntry entry, String outputFile) {
        log.info("Adding new entry");
        String path = "./" + inputDir + "/" + unreleasedVersionDir + "/" + outputFile;
        File entryFile = createFile(path);

        log.finer(entry.toString());

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
                log.warning(msg);
                throw new RuntimeException(msg);
            }
        } catch (IOException e) {
            String msg = String.format("An error occurred while creating empty changelog entry file with path: %s - %s", path, e.getMessage());
            log.severe(msg);
            throw new RuntimeException(msg);
        }
    }

    private static void checkIfCanAdd(String inputDir, String unreleasedVersionDir) {
        String path = "./" + inputDir + "/" + unreleasedVersionDir;
        File unreleasedDir = new File(path);

        if (!unreleasedDir.exists() || !unreleasedDir.isDirectory()) {
            String msg = "Cannot add new entry if " + path + " not exists nor is directory";
            log.severe(msg);
            throw new RuntimeException(msg);
        }
    }
}
