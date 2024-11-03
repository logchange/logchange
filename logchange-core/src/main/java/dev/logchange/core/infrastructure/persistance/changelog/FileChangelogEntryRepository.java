package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogEntryRepository;
import dev.logchange.core.application.file.repository.FileWriter;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class FileChangelogEntryRepository implements ChangelogEntryRepository {

    private final FileWriter fileWriter;

    @Override
    public void save(ChangelogEntry entry) {
        String content = YMLChangelogEntry.of(entry).toYMLString();
        this.fileWriter.write(content);
    }
}
