package dev.logchange.core.format.md.changelog;

import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.MDMeta;

public class MDChangelog extends Configurable implements MD {

    private final MDMeta meta;
    private final MDChangelogHeading heading;
    private final MDChangelogVersions versions;
    private final MDChangelogArchives archives;

    public MDChangelog(Config config, Changelog changelog) {
        super(config);
        this.meta = new MDMeta();
        this.heading = new MDChangelogHeading(config.getHeading());
        this.versions = new MDChangelogVersions(getConfig(), changelog.getVersions());
        this.archives = new MDChangelogArchives(changelog.getArchives());
    }

    @Override
    public String toString() {
        return getChangelog();
    }

    private String getChangelog() {
        return String.valueOf(meta) +
                heading +
                versions +
                archives;
    }

}
