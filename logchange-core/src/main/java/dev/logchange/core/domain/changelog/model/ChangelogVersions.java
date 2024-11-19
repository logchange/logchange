package dev.logchange.core.domain.changelog.model;

import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.Collections;
import java.util.List;

@Log
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogVersions {

    private final List<ChangelogVersion> versions;

    static ChangelogVersions of(List<ChangelogVersion> versions) {
        if (versions == null) {
            log.severe("Changelog versions cannot be null!");
            throw new IllegalArgumentException("Changelog versions cannot be null!");
        }
        return new ChangelogVersions(Collections.unmodifiableList(versions));
    }
}
