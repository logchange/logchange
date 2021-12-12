package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.format.md.MD;

public class MDChangelogVersion implements MD {

    private final MDChangelogVersionHeading heading;
    private final MDChangelogVersionImportantNotes importantNotes;
    private final MDChangelogEntriesGroups entriesGroups;
    private final MDChangelogVersionConfiguration configuration;

    public MDChangelogVersion(ChangelogVersion changelogVersion) {
        this.heading = new MDChangelogVersionHeading(changelogVersion.getVersion(), changelogVersion.getReleaseDateTime());
        this.importantNotes = new MDChangelogVersionImportantNotes(changelogVersion.getImportantNotes());
        this.entriesGroups = new MDChangelogEntriesGroups(changelogVersion.getEntries());
        this.configuration = new MDChangelogVersionConfiguration(changelogVersion.getConfigurations());
    }

    @Override
    public String toString() {
        return getVersion();
    }

    private String getVersion() {
        return heading +
                "\n\n" +
                importantNotes +
                "\n\n" +
                entriesGroups +
                configuration;
    }
}
