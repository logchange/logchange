package dev.logchange.core.domain.changelog.model;

import dev.logchange.core.domain.changelog.model.archive.ChangelogArchive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogArchives {

    private final List<ChangelogArchive> archives;

    static ChangelogArchives of(List<ChangelogArchive> archives) {
        if (archives == null) {
            throw new IllegalArgumentException("Versions cannot be null");
        }
        return new ChangelogArchives(Collections.unmodifiableList(archives));
    }
}
