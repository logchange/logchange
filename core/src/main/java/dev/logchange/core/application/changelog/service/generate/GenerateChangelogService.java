package dev.logchange.core.application.changelog.service.generate;

import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.format.md.changelog.MDChangelog;

public class GenerateChangelogService implements GenerateChangelogUseCase {

    @Override
    public String handle(GenerateChangelogCommand command) {
        return new MDChangelog(command.getChangelog()).toMD();
    }
}
