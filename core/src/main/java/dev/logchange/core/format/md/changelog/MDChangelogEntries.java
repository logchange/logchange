package dev.logchange.core.format.md.changelog;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.entry.MDChangelogEntry;
import dev.logchange.core.model.entry.ChangelogEntry;
import dev.logchange.core.model.entry.ChangelogEntryType;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

class MDChangelogEntries implements MD {

    private final ChangelogEntryType type;
    private final List<ChangelogEntry> entries;

    MDChangelogEntries(ChangelogEntryType type, List<ChangelogEntry> entries) {
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
        return new Heading(StringUtils.capitalize(type.getType()) + " " + getChangesNumber(entries), 3);
    }

    private String getChangesNumber(List<ChangelogEntry> entriesByType) {
        return "(" + entriesByType.size() + " change" + (entriesByType.size() > 1 ? "s" : "") + ")";
    }

}
