package dev.logchange.core.infrastructure.persistance.archive;

import dev.logchange.core.application.changelog.repository.ChangelogPersistence;
import dev.logchange.core.application.file.repository.FileWriter;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.archive.MDArchive;
import lombok.CustomLog;
import org.apache.maven.plugins.changes.model.ChangesDocument;

@CustomLog
public class FileArchiveRepository implements ChangelogPersistence {

    private final FileWriter writer;
    private final Config config;

    public FileArchiveRepository(FileWriter writer, Config config) {
        this.writer = writer;
        this.config = config;
    }

    @Override
    public void save(Changelog changelog) {
        log.info("Creating archive markdown content...");
        String md = new MDArchive(config, changelog).toMD();
        log.info("Saving changelog archive to file...");
        writer.write(md);
    }

    @Override
    public void saveXML(ChangesDocument changesDocument) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
