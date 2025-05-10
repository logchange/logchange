package dev.logchange.core.domain.changelog.model.version;

import dev.logchange.core.domain.changelog.model.DetachedImportantNote;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfiguration;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogVersion implements Comparable<ChangelogVersion> {

    private final Version version;
    private final ReleaseDateTime releaseDateTime;
    private final List<ChangelogVersionEntriesGroup> entriesGroups;

    @Override
    public int compareTo(ChangelogVersion o) {
        return version.compareTo(o.version);
    }

    public List<DetachedImportantNote> getDetachedImportantNotes() {
        return getEntriesWithOrder().stream()
                .map(ChangelogEntry::getDetachedImportantNotes)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<ChangelogEntryConfiguration> getConfigurations() {
        return getEntriesWithOrder().stream()
                .map(ChangelogEntry::getConfigurations)
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<ChangelogEntry> getEntries() {
        return entriesGroups.stream()
                .map(ChangelogVersionEntriesGroup::getEntries)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<ChangelogEntry> getEntriesWithOrder() {
        return entriesGroups.stream()
                .map(ChangelogVersionEntriesGroup::getEntries)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(ChangelogEntry::getId))
                .collect(Collectors.toList());
    }}
