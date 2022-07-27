package dev.logchange.core.application.changelog.repository;

import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;

public interface VersionSummaryRepository {

    void save(ChangelogVersion version);

}
