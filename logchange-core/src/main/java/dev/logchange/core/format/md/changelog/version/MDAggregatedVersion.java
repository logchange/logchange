package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.MDMeta;
import dev.logchange.core.format.md.changelog.Configurable;

public class MDAggregatedVersion extends Configurable implements MD {

    private final MDMeta meta;
    private final MDChangelogVersionHeading versionHeading;
    private final MDChangelogEntriesGroups entriesGroups;

    public MDAggregatedVersion(Config config, ChangelogVersion version) {
        super(config);
        this.meta = new MDMeta();
        this.versionHeading = new MDChangelogVersionHeading(version.getVersion(), version.getReleaseDateTime(), config);
        this.entriesGroups = new MDChangelogEntriesGroups(version.getEntries(), config);
    }

    @Override
    public String toString() {
        return getVersion();
    }

    private String getVersion() {
        return meta
                + versionHeading.toString()
                + entriesGroups;
    }
}
