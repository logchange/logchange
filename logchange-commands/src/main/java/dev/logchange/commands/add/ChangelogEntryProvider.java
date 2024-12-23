package dev.logchange.commands.add;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;

public interface ChangelogEntryProvider {
    ChangelogEntry get();
}
