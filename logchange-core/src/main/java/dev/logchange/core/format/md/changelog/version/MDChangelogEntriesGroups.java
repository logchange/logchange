package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.version.ChangelogVersionEntriesGroup;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;

import java.util.List;

class MDChangelogEntriesGroups extends Configurable implements MD {

    private final List<ChangelogVersionEntriesGroup> entriesGroups;

    public MDChangelogEntriesGroups(List<ChangelogVersionEntriesGroup> entriesGroups, Config config) {
        super(config);
        this.entriesGroups = entriesGroups;
    }

    @Override
    public String toString() {
        return getEntriesGroups();
    }

    private String getEntriesGroups() {
        StringBuilder entriesGroup = new StringBuilder();
        for (ChangelogVersionEntriesGroup group : entriesGroups) {
            entriesGroup.append(new MDChangelogEntriesGroup(group, getConfig()));
        }
        return entriesGroup.toString();
    }
}
