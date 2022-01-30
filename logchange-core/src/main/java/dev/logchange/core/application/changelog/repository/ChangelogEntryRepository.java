package dev.logchange.core.application.changelog.repository;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;

public interface ChangelogEntryRepository {
    void save(ChangelogEntry entry);
}
