package dev.logchange.core.infrastructure.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogEntryRepository;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileChangelogEntryRepository implements ChangelogEntryRepository {

    private final File outputFile;

    public FileChangelogEntryRepository(File outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void save(ChangelogEntry entry) {
        String content = YMLChangelogEntry.of(entry).toYMLString();
        try (PrintWriter out = new PrintWriter(outputFile)) {
            out.println(content);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Could not save changelog entry to file: " + outputFile + " because: " + e.getMessage());
        }
    }
}
