package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;

import java.util.List;

class MDChangelogEntriesGroups extends Configurable implements MD {

    private final List<ChangelogEntry> entries;

    public MDChangelogEntriesGroups(List<ChangelogEntry> entries, Config config) {
        super(config);
        this.entries = entries;
    }

    @Override
    public String toString() {
        return getEntriesGroups();
    }

    private String getEntriesGroups() {
        StringBuilder entriesGroup = new StringBuilder();
        for (ChangelogEntryType type : ChangelogEntryType.values()) {
            entriesGroup.append(new MDChangelogEntriesGroup(type, entries, getConfig()));
        }
        return entriesGroup.toString();
    }
}
