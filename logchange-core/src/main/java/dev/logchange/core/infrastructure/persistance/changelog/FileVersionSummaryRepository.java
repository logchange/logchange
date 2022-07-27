package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.format.md.changelog.version.MDChangelogVersion;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@AllArgsConstructor
public class FileVersionSummaryRepository implements VersionSummaryRepository {

    private final File inputDirectory;

    @Override
    public void save(ChangelogVersion version) {
        String md = new MDChangelogVersion(version).toMD();

        String outputFile = inputDirectory.getAbsolutePath() + "/" + version.getVersion().getDirName() + "/version-summary.md";

        try (PrintWriter out = new PrintWriter(outputFile)) {
            out.println(md);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Could not save changelog to file: " + outputFile + " because: " + e.getMessage());
        }
    }
}
