package dev.logchange.core.domain.changelog.command;

import lombok.Value;

public interface ValidateChangelogUseCase {

    void handle(ValidateChangelogCommand command);

    @Value(staticConstructor = "of")
    class ValidateChangelogCommand {
    }
}
