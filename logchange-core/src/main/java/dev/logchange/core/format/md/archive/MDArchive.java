package dev.logchange.core.format.md.archive;

import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.core.format.md.changelog.MDChangelogArchives;
import dev.logchange.core.format.md.changelog.MDChangelogVersions;

public class MDArchive extends Configurable implements MD {

    private final MDChangelogVersions versions;
    private final MDChangelogArchives archives;

    public MDArchive(Config config, Changelog changelog) {
        super(config);
        this.versions = new MDChangelogVersions(getConfig(), changelog.getVersions());
        this.archives = new MDChangelogArchives(changelog.getArchives());
    }

    @Override
    public String toString() {
        return getArchive();
    }

    private String getArchive() {
        return String.valueOf(versions) + archives;
    }
}
