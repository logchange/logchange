package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogEntryRepository;
import dev.logchange.core.application.file.repository.FileWriter;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;

@CustomLog
@RequiredArgsConstructor
public class FileChangelogEntryRepository implements ChangelogEntryRepository {

    private final FileWriter fileWriter;
    private final Config config;

    @Override
    public void save(ChangelogEntry entry) {
        String content = YMLChangelogEntry.of(entry).toYMLString(config.isEntryBanner());
        this.fileWriter.write(content);
    }
}
