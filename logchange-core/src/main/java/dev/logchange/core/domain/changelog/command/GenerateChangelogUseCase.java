package dev.logchange.core.domain.changelog.command;

import lombok.Value;

public interface GenerateChangelogUseCase {
    void handle(GenerateChangelogCommand command);

    @Value(staticConstructor = "of")
    class GenerateChangelogCommand {
    }
}
