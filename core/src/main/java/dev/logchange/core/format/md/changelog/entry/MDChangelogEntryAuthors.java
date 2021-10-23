package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.model.ChangelogEntryAuthor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

class MDChangelogEntryAuthors implements MD {

    private final List<MDChangelogEntryAuthor> authors;

    MDChangelogEntryAuthors(List<ChangelogEntryAuthor> authors) {
        this.authors = authors.stream().map(MDChangelogEntryAuthor::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getAuthors();
    }

    public String getAuthors() {
        if (authors.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder mdAuthors = new StringBuilder(StringUtils.EMPTY);
        for (MDChangelogEntryAuthor author : authors) {
            mdAuthors.append(author).append(" ");
        }

        return mdAuthors.toString().trim();
    }
}
