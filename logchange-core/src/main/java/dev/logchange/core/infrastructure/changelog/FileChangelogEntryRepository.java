package dev.logchange.core.infrastructure.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogEntryRepository;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;

public class FileChangelogEntryRepository implements ChangelogEntryRepository {
    @Override
    public void save(ChangelogEntry entry) {

    }
}
