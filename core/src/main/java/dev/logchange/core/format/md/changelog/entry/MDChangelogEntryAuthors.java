package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.model.entry.ChangelogEntryAuthor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

class MDChangelogEntryAuthors implements MD {

    private final List<ChangelogEntryAuthor> authors;

    MDChangelogEntryAuthors(List<ChangelogEntryAuthor> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return getAuthors();
    }

    private String getAuthors() {
        if (authors == null || authors.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder mdAuthors = new StringBuilder(StringUtils.EMPTY);
        for (ChangelogEntryAuthor author : authors) {
            mdAuthors.append(new MDChangelogEntryAuthor(author)).append(" ");
        }

        return mdAuthors.toString().trim();
    }
}
