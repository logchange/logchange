package dev.logchange.core.domain.changelog.model.archive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

import java.text.Collator;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Log
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogArchive implements Comparable<ChangelogArchive> {
    private final String fileName;
    private final List<String> lines;

    public static ChangelogArchive of(String fileName, List<String> archiveLines) {
        if (archiveLines == null) {
            log.severe("Archive cannot be null!");
            throw new IllegalArgumentException("Archive cannot be null");
        }

        return new ChangelogArchive(fileName, Collections.unmodifiableList(archiveLines));
    }

    @Override
    public int compareTo(ChangelogArchive o) {
        return Collator.getInstance(Locale.ENGLISH).compare(this.fileName, o.fileName);
    }
}
