package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.model.ChangelogVersions;
import dev.logchange.core.model.version.ChangelogVersion;

public class MDChangelogVersions implements MD {

    private final ChangelogVersions versions;

    public MDChangelogVersions(ChangelogVersions versions) {
        this.versions = versions;
    }

    @Override
    public String toString() {
        return getChangelogVersions();
    }

    private String getChangelogVersions() {
        StringBuilder mdVersions = new StringBuilder();
        for (ChangelogVersion version : versions.getVersions()) {
            mdVersions.append(new MDChangelogVersion(version))
                    .append("\n");
        }
        return mdVersions.toString();
    }
}
