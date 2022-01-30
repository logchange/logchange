package dev.logchange.core.format.md.changelog;

import dev.logchange.core.domain.changelog.model.ChangelogArchives;
import dev.logchange.core.domain.changelog.model.archive.ChangelogArchive;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.archive.MDChangelogArchive;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MDChangelogArchives implements MD {

    private final ChangelogArchives archives;

    @Override
    public String toString() {
        return getChangelogArchives();
    }

    private String getChangelogArchives() {
        StringBuilder mdArchives = new StringBuilder();
        for (ChangelogArchive archive : archives.getArchives()) {
            mdArchives.append(new MDChangelogArchive(archive))
                    .append("\n");
        }
        return mdArchives.toString();
    }
}
