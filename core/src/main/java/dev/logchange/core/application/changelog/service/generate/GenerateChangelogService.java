package dev.logchange.core.application.changelog.service.generate;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.ChangelogHeading;

public class GenerateChangelogService implements GenerateChangelogUseCase {

    private final ChangelogRepository changelogRepository;

    public GenerateChangelogService(ChangelogRepository changelogRepository) {
        this.changelogRepository = changelogRepository;
    }

    @Override
    public void handle(GenerateChangelogCommand command) {
        Changelog changelog = changelogRepository.find();
        changelog = changelog.setUpHeading(ChangelogHeading.of(command.getHeading()));
        changelogRepository.save(changelog);
    }
}
