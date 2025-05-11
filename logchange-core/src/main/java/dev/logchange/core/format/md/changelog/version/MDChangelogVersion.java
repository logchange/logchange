package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.DetachedConfiguration;
import dev.logchange.core.domain.changelog.model.DetachedImportantNote;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MDChangelogVersion extends Configurable implements MD {

    private final MDChangelogVersionHeading versionHeading;
    private final MDChangelogVersionImportantNotes importantNotes;
    private final MDChangelogEntriesGroups entriesGroups;
    private final MDChangelogVersionConfiguration configuration;

    public MDChangelogVersion(Config config, ChangelogVersion changelogVersion) {
        super(config);
        this.versionHeading = new MDChangelogVersionHeading(changelogVersion.getVersion(), changelogVersion.getReleaseDateTime(), config);
        List<DetachedImportantNote> detachedImportantNotes = changelogVersion
                .getDetachedImportantNotes()
                .collect(Collectors.toList());
        this.importantNotes = new MDChangelogVersionImportantNotes(detachedImportantNotes, config);
        this.entriesGroups = new MDChangelogEntriesGroups(changelogVersion.getEntriesGroups(), config);
        List<DetachedConfiguration> detachedConfigurations = changelogVersion
                .getDetachedConfigurations()
                .collect(Collectors.toList());
        this.configuration = new MDChangelogVersionConfiguration(detachedConfigurations, config);
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
