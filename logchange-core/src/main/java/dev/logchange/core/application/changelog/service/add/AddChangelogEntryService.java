package dev.logchange.core.application.changelog.service.add;

import dev.logchange.core.application.changelog.repository.ChangelogEntryRepository;
import dev.logchange.core.domain.changelog.command.AddChangelogEntryUseCase;

public class AddChangelogEntryService implements AddChangelogEntryUseCase {

    private final ChangelogEntryRepository repository;

    public AddChangelogEntryService(ChangelogEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(AddChangelogEntryCommand command) {
        repository.save(command.getEntry());
    }
}
