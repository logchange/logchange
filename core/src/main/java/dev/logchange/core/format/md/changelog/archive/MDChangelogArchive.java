package dev.logchange.core.format.md.changelog.archive;

import dev.logchange.core.domain.changelog.model.archive.ChangelogArchive;
import dev.logchange.core.format.md.MD;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MDChangelogArchive implements MD {
    private final ChangelogArchive archive;

    @Override
    public String toString() {
        return getArchive();
    }

    private String getArchive() {
        StringBuilder mdArchive = new StringBuilder();
        for (String line : archive.getLines()) {
            mdArchive.append(line).append("\n");
        }
        return mdArchive.toString();
    }
}
