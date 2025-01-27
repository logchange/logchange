package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

class MDChangelogEntryAuthors extends Configurable implements MD {

    private final List<ChangelogEntryAuthor> authors;

    MDChangelogEntryAuthors(List<ChangelogEntryAuthor> authors, Config config) {
        super(config);
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
            mdAuthors.append(new MDChangelogEntryAuthor(author, getConfig())).append(" ");
        }

        return mdAuthors.toString().trim();
    }
}
