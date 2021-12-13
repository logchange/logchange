package dev.logchange.core.application.changelog.repository;

import dev.logchange.core.domain.changelog.model.Changelog;

public interface ChangelogRepository {
    Changelog find();

    void save(Changelog changelog);
}
