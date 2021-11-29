package dev.logchange.core.model.version;

import dev.logchange.core.model.entry.ChangelogEntry;
import dev.logchange.core.model.entry.ChangelogEntryConfiguration;
import lombok.Getter;
import lombok.Singular;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ChangelogVersion implements Comparable<ChangelogVersion> {

    private Version version;
    private OffsetDateTime releaseDateTime;
    @Singular
    private List<ChangelogEntry> entries;


    @Override
    public int compareTo(ChangelogVersion o) {
        return version.compareTo(o.version);
    }

    public List<String> getImportantNotes() {
        return entries.stream()
                .map(ChangelogEntry::getImportantNotes)
                .flatMap(List::stream)
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
