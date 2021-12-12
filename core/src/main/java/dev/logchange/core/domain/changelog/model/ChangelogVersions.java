package dev.logchange.core.domain.changelog.model;

import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogVersions {
    @Singular
    private final List<ChangelogVersion> versions;

    static ChangelogVersions of(List<ChangelogVersion> versions) {
        if (versions == null) {
            throw new IllegalArgumentException("Versions cannot be null");
        }
        return new ChangelogVersions(versions);
    }
}
