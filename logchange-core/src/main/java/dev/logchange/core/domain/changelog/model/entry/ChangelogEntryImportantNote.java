package dev.logchange.core.domain.changelog.model.entry;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntryImportantNote implements Comparable<ChangelogEntryImportantNote> {
    private final String prefix;
    private final String value;

    public static ChangelogEntryImportantNote of(String value) {
        return new ChangelogEntryImportantNote(null, value);
    }

    public ChangelogEntryImportantNote withPrefix(String prefix) {
        return new ChangelogEntryImportantNote(prefix, value);
    }

    @Override
    public int compareTo(ChangelogEntryImportantNote o) {
        return Comparator.comparing(ChangelogEntryImportantNote::getPrefix, Comparator.nullsLast(String::compareTo))
                .compare(this, o);
    }
}
