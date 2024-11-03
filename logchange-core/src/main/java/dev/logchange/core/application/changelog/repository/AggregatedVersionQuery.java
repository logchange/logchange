package dev.logchange.core.application.changelog.repository;

import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;

import java.nio.file.Path;
import java.util.Optional;

public interface AggregatedVersionQuery {

    Optional<ChangelogVersion> find(Path changelogDirectory, String projectName);
}
