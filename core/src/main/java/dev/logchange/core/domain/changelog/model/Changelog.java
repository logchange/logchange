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

    private final ChangelogHeading heading;
    private final ChangelogVersions versions;
    private final ChangelogArchives archives;

    public static Changelog of(List<ChangelogVersion> versions, List<ChangelogArchive> archives) {
        ChangelogHeading changelogHeading = ChangelogHeading.EMPTY;
        ChangelogVersions changelogVersions = ChangelogVersions.of(versions);
        ChangelogArchives changelogArchives = ChangelogArchives.of(archives);

        return new Changelog(changelogHeading, changelogVersions, changelogArchives);
    }

    public Changelog setUpHeading(ChangelogHeading heading) {
        return Changelog.builder()
                .heading(heading)
                .versions(versions)
                .archives(archives)
                .build();
    }

}
