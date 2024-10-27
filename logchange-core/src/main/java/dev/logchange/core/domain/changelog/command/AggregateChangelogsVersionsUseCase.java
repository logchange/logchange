package dev.logchange.core.domain.changelog.command;

import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.domain.config.model.aggregate.Aggregates;
import lombok.Value;

public interface AggregateChangelogsVersionsUseCase {

    void handle(AggregateChangelogsVersionsCommand command);

    @Value(staticConstructor = "of")
    class AggregateChangelogsVersionsCommand {
        Aggregates aggregates;
        String version;

        Version getVersion() {
            return Version.of(version);
        }
    }
}