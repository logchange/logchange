package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersionEntriesGroup;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.labels.NumberOfChangesLabels;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.core.format.md.changelog.entry.MDChangelogEntry;
import dev.logchange.md.MarkdownBasics;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

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

        for (ChangelogEntry entry : group.getEntries()) {
            stringBuilder.append(new MDChangelogEntry(entry, getConfig())).append("\n");
        }

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
