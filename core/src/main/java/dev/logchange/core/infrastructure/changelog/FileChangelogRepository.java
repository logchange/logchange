package dev.logchange.core.infrastructure.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.format.md.changelog.MDChangelog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileChangelogRepository implements ChangelogRepository {

    private final File changelogFile;

    public FileChangelogRepository(File changelogFile) {
        this.changelogFile = changelogFile;
    }

    @Override
    public void save(Changelog changelog) {
        String md = new MDChangelog(changelog).toMD();

        try (PrintWriter out = new PrintWriter(changelogFile)) {
            out.println(md);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Could not save changelog to file: " + changelogFile);
        }
    }
}
