package dev.logchange.core.domain.changelog.command;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import lombok.Value;

public interface AddChangelogEntryUseCase {
    void handle(AddChangelogEntryCommand command);

    @Value(staticConstructor = "of")
    class AddChangelogEntryCommand {
        ChangelogEntry entry;
    }
}
