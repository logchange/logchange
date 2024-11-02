package dev.logchange.core.domain.changelog.model.version;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfiguration;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryImportantNote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogVersion implements Comparable<ChangelogVersion> {

    private final Version version;
    private final OffsetDateTime releaseDateTime;
    private final List<ChangelogEntry> entries;

    @Override
    public int compareTo(ChangelogVersion o) {
        return version.compareTo(o.version);
    }

    public List<ChangelogEntryImportantNote> getImportantNotes() {
        return entries.stream()
                .map(ChangelogEntry::getImportantNotes)
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<ChangelogEntryConfiguration> getConfigurations() {
        return entries.stream()
                .map(ChangelogEntry::getConfigurations)
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());
    }
}
