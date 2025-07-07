package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersionEntriesGroup;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.labels.NumberOfChangesLabels;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.core.format.md.changelog.entry.MDChangelogEntry;
import dev.logchange.core.format.md.changelog.entry.MDChangelogEntryPrefix;
import dev.logchange.md.MarkdownBasics;
import org.apache.commons.lang3.StringUtils;

import java.sql.Array;
import java.util.*;

class MDChangelogEntriesGroup extends Configurable implements MD {

    private final ChangelogVersionEntriesGroup group;

    MDChangelogEntriesGroup(ChangelogVersionEntriesGroup group, Config config) {
        super(config);
        this.group = group;
    }

    @Override
    public String toString() {
        return getEntries();
    }

    private String getEntries() {
        if (group.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTypeHeading()).append("\n").append("\n");

        Comparator<ChangelogEntry> comparator = Comparator.comparing(ChangelogEntry::getId);
        MDModuleStructure<ChangelogEntry> structure = MDModuleStructure.build(group.getEntries(), comparator);

        for (ChangelogEntry entry : structure.getNoModules()) {
            MDChangelogEntryPrefix prefix = MDChangelogEntryPrefix.of(entry.getPrefix());
            stringBuilder.append(new MDChangelogEntry(entry, getConfig(), prefix)).append("\n");
        }

        structure.getGroups().forEach((module, entries) -> {
            entries.sort(Comparator.comparing(ChangelogEntry::getId));
            for (ChangelogEntry entry : entries) {
                MDChangelogEntryPrefix prefix = MDChangelogEntryPrefix.of(module.getName());
                stringBuilder.append(new MDChangelogEntry(entry, getConfig(), prefix)).append("\n");
            }
        });

        return stringBuilder.append("\n").toString();
    }

    private String getTypeHeading() {
        return MarkdownBasics.heading(getConfig().getLabels().getTypes().getType(group.getType()) + " " + getChangesNumber(group.getEntries()), 3);
    }

    private String getChangesNumber(List<ChangelogEntry> entriesByType) {
        NumberOfChangesLabels changes = getConfig().getLabels().getTypes().getNumberOfChanges();
        return "(" + entriesByType.size() + " " + (entriesByType.size() > 1 ? changes.getPlural() : changes.getSingular()) + ")";
    }

}
