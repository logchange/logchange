package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.model.entry.ChangelogEntry;
import dev.logchange.core.model.entry.ChangelogEntryType;

import java.util.List;

class MDChangelogEntriesGroups implements MD {

    private final List<ChangelogEntry> entries;

    public MDChangelogEntriesGroups(List<ChangelogEntry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return getEntriesGroups();
    }

    private String getEntriesGroups() {
        StringBuilder entriesGroup = new StringBuilder();
        for (ChangelogEntryType type : ChangelogEntryType.values()) {
            entriesGroup.append(new MDChangelogEntriesGroup(type, entries));
        }
        return entriesGroup.toString();
    }
}
