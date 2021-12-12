package dev.logchange.core.domain.changelog.command;

import dev.logchange.core.domain.changelog.model.Changelog;
import lombok.Value;

public interface GenerateChangelogUseCase {
    String handle(GenerateChangelogCommand command);

    @Value(staticConstructor = "of")
    class GenerateChangelogCommand {
        Changelog changelog;
    }
}
