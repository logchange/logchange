package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;

public class MDChangelogVersion extends Configurable implements MD {

    private final MDChangelogVersionHeading versionHeading;
    private final MDChangelogVersionImportantNotes importantNotes;
    private final MDChangelogEntriesGroups entriesGroups;
    private final MDChangelogVersionConfiguration configuration;

    public MDChangelogVersion(Config config, ChangelogVersion changelogVersion) {
        super(config);
        this.versionHeading = new MDChangelogVersionHeading(changelogVersion.getVersion(), changelogVersion.getReleaseDateTime(), config);
        this.importantNotes = new MDChangelogVersionImportantNotes(changelogVersion.getImportantNotes(), config);
        this.entriesGroups = new MDChangelogEntriesGroups(changelogVersion.getEntriesGroups(), config);
        this.configuration = new MDChangelogVersionConfiguration(changelogVersion.getConfigurations(), config);
    }

    @Override
    public String toString() {
        return getVersion();
    }

    private String getVersion() {
        return versionHeading.toString() +
                importantNotes +
                entriesGroups +
                configuration;
    }
}
