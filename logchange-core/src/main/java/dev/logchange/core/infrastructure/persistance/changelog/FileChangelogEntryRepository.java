package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogEntryRepository;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileChangelogEntryRepository implements ChangelogEntryRepository {

    private final File outputFile;

    public FileChangelogEntryRepository(File outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void save(ChangelogEntry entry) {
        String content = YMLChangelogEntry.of(entry).toYMLString();

        try (OutputStream os = Files.newOutputStream(outputFile.toPath());
             PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {

            out.println(content);

        } catch (IOException e) {
            throw new IllegalArgumentException("Could not save changelog entry to file: " + outputFile + " because: " + e.getMessage());
        }
    }
}
