package dev.logchange.core.domain.changelog.model.archive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogArchive {
    private final List<String> lines;

    public static ChangelogArchive of(List<String> archiveLines) {
        if (archiveLines == null) {
            throw new IllegalArgumentException("Archive cannot be null");
        }

        return new ChangelogArchive(archiveLines);
    }
}
