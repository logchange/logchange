package dev.logchange.maven_plugin.mojo.add.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;

public interface ChangelogEntryProvider {
    ChangelogEntry get();
}
