package dev.logchange.core.domain.changelog.model;

import dev.logchange.core.domain.changelog.model.archive.ChangelogArchive;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Changelog {

    private final ChangelogVersions versions;
    private final ChangelogArchives archives;

    public static Changelog of(List<ChangelogVersion> versions, List<ChangelogArchive> archives) {
        ChangelogVersions changelogVersions = ChangelogVersions.of(versions);
        ChangelogArchives changelogArchives = ChangelogArchives.of(archives);

        return new Changelog(changelogVersions, changelogArchives);
    }


}
