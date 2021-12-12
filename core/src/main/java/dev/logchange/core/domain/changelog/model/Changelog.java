package dev.logchange.core.domain.changelog.model;

import dev.logchange.core.domain.changelog.model.archive.ChangelogArchive;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Changelog {

    private final ChangelogHeading heading;
    private final ChangelogVersions versions;
    private final ChangelogArchives archives;

    public static Changelog of(String heading, List<ChangelogVersion> versions, List<ChangelogArchive> archives) {
        ChangelogHeading changelogHeading = ChangelogHeading.of(heading);
        ChangelogVersions changelogVersions = ChangelogVersions.of(versions);
        ChangelogArchives changelogArchives = ChangelogArchives.of(archives);

        return new Changelog(changelogHeading, changelogVersions, changelogArchives);
    }

}
