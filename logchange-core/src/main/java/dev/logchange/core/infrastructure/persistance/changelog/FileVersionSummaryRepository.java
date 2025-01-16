package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MDMeta;
import dev.logchange.core.format.md.changelog.version.MDChangelogVersion;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.io.File;

@Log
@AllArgsConstructor
public class FileVersionSummaryRepository implements VersionSummaryRepository {

    private final File inputDirectory;
    private final Config config;

    @Override
    public void save(ChangelogVersion version) {
        String meta = new MDMeta().toMD();
        String md = new MDChangelogVersion(config, version).toMD();

        String outputFilePath = inputDirectory.getPath() + "/" + version.getVersion().getDirName() + "/version-summary.md";
        File outputFile = new File(outputFilePath);

        FileRepository fileRepository = FileRepository.of(outputFile);
        fileRepository.write(meta + md);
    }
}
