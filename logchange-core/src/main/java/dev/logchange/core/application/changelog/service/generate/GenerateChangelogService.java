package dev.logchange.core.application.changelog.service.generate;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;

public class GenerateChangelogService implements GenerateChangelogUseCase {

    private final ChangelogRepository changelogRepository;
    private final VersionSummaryRepository versionSummaryRepository;

    public GenerateChangelogService(ChangelogRepository changelogRepository, VersionSummaryRepository versionSummaryRepository) {
        this.changelogRepository = changelogRepository;
        this.versionSummaryRepository = versionSummaryRepository;
    }

    @Override
    public void handle(GenerateChangelogCommand command) {
        Changelog changelog = changelogRepository.find();

        for (ChangelogVersion version : changelog.getVersions().getVersions()) {
            versionSummaryRepository.save(version);
        }

        changelogRepository.save(changelog);
    }
}
