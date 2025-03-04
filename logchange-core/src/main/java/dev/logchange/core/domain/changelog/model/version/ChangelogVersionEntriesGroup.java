package dev.logchange.core.domain.changelog.model.version;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ChangelogVersionEntriesGroup {

    private final ChangelogEntryType type;
    private final List<ChangelogEntry> entries;

    private ChangelogVersionEntriesGroup(ChangelogEntryType type, List<ChangelogEntry> entries) {
        this.type = type;
        this.entries = entries;
    }

    public static ChangelogVersionEntriesGroup ofNotFiltered(ChangelogEntryType type, List<ChangelogEntry> entries) {
        List<ChangelogEntry> filteredEntries = entries.stream()
                .filter(changelogEntry -> type.equals(changelogEntry.getType()))
                .collect(Collectors.toList());

        return new ChangelogVersionEntriesGroup(type, filteredEntries);
    }


    public static List<ChangelogVersionEntriesGroup> ofEntriesKeepingOrder(List<ChangelogEntry> entries) {
        int entriesIndex = 0;
        for (ChangelogEntry entry : entries) {
            entry.setId(entriesIndex);
            entriesIndex++;
        }

        List<ChangelogVersionEntriesGroup> entriesGroups = new ArrayList<>();
        for (ChangelogEntryType entryType: ChangelogEntryType.values()){
            entriesGroups.add(ChangelogVersionEntriesGroup.ofNotFiltered(entryType, entries));
        }
        return entriesGroups;
    }

    public boolean isEmpty(){
        return entries.isEmpty();
    }

    public boolean isNotEmpty(){
        return !isEmpty();
    }
}
