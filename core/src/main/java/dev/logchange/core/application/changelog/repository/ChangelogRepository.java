package dev.logchange.core.application.changelog.repository;

import dev.logchange.core.domain.changelog.model.Changelog;

public interface ChangelogRepository {
    void save(Changelog changelog);
}
