package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MDMeta;
import dev.logchange.core.format.md.changelog.version.MDChangelogVersion;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Log
@AllArgsConstructor
public class FileVersionSummaryRepository implements VersionSummaryRepository {

    private final File inputDirectory;
    private final Config config;

    @Override
    public void save(ChangelogVersion version) {
        String meta = new MDMeta().toMD();
        String md = new MDChangelogVersion(config, version).toMD();

        String outputFilePath = inputDirectory.getAbsolutePath() + "/" + version.getVersion().getDirName() + "/version-summary.md";
        File outputFile = new File(outputFilePath);

        try (OutputStream os = Files.newOutputStream(outputFile.toPath());
             PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {

            out.println(meta + md);

        } catch (IOException e) {
            String message = "Could not save changelog to file: " + outputFilePath + " because: " + e.getMessage();
            log.severe(message);
            throw new IllegalArgumentException(message);
        }
    }
}
