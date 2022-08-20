package dev.logchange.core.format.md.changelog;

import dev.logchange.core.domain.changelog.model.ChangelogVersions;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.version.MDChangelogVersion;

public class MDChangelogVersions extends Configurable implements MD {

    private final ChangelogVersions versions;

    public MDChangelogVersions(Config config, ChangelogVersions versions) {
        super(config);
        this.versions = versions;
    }

    @Override
    public String toString() {
        return getChangelogVersions();
    }

    private String getChangelogVersions() {
        StringBuilder mdVersions = new StringBuilder();
        for (ChangelogVersion version : versions.getVersions()) {
            mdVersions.append(new MDChangelogVersion(getConfig(), version))
                    .append("\n");
        }
        return mdVersions.toString();
    }
}
