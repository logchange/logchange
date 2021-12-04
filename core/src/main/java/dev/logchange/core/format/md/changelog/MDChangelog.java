package dev.logchange.core.format.md.changelog;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.MDMeta;
import dev.logchange.core.model.Changelog;

public class MDChangelog implements MD {

    private final MDMeta meta;
    private final MDChangelogHeading heading;
    private final MDChangelogVersions versions;

    public MDChangelog(Changelog changelog) {
        this.meta = new MDMeta();
        this.heading = new MDChangelogHeading(changelog.getHeading());
        this.versions = new MDChangelogVersions(changelog.getVersions());
    }

    @Override
    public String toString() {
        return getChangelog();
    }

    public String getChangelog() {
        return String.valueOf(meta) +
                heading +
                versions;
    }

}
