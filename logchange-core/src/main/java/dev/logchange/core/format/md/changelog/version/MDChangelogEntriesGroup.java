package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.labels.NumberOfChangesLabels;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.core.format.md.changelog.entry.MDChangelogEntry;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

class MDChangelogEntriesGroup extends Configurable implements MD {

    private final ChangelogEntryType type;
    private final List<ChangelogEntry> entries;

    MDChangelogEntriesGroup(ChangelogEntryType type, List<ChangelogEntry> entries, Config config) {
        super(config);
        this.type = type;
        this.entries = entries.stream()
                .filter(changelogEntry -> type.equals(changelogEntry.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getEntries();
    }


    private String getEntries() {
        if (entries == null || entries.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTypeHeading()).append("\n").append("\n");

        for (ChangelogEntry entry : entries) {
            stringBuilder.append(new MDChangelogEntry(entry)).append("\n");
        }

        return stringBuilder.append("\n").toString();
    }

    private Heading getTypeHeading() {
        return new Heading(getConfig().getLabels().getTypes().getType(type) + " " + getChangesNumber(entries), 3);
    }

    private String getChangesNumber(List<ChangelogEntry> entriesByType) {
        NumberOfChangesLabels changes = getConfig().getLabels().getTypes().getNumberOfChanges();
        return "(" + entriesByType.size() + " " + (entriesByType.size() > 1 ? changes.getPlural() : changes.getSingular()) + ")";
    }

}
