package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import dev.logchange.core.format.md.MD;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

import static dev.logchange.md.MarkdownBasics.link;

class MDChangelogEntryAuthor implements MD {

    private static final String authorFormat = "(${author})";
    private static final String AT = "@";
    private static final String LINK = "LINK";

    private final ChangelogEntryAuthor author;

    MDChangelogEntryAuthor(ChangelogEntryAuthor author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return getAuthor();
    }

    private String getAuthor() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("author", (getAuthorName() + " " + getAuthorNick() + " " + getLinkWhenNameEmpty()).trim());
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(authorFormat);
    }

    private String getAuthorName() {
        if (StringUtils.isNotBlank(author.getUrl()) && StringUtils.isNotBlank(author.getName())) {
            return link(author.getName(), author.getUrl());
        }

        return StringUtils.defaultString(author.getName());
    }

    private String getAuthorNick() {
        if (StringUtils.isBlank(author.getNick())) {
            return StringUtils.EMPTY;
        }

        return AT + author.getNick();
    }

    private String getLinkWhenNameEmpty() {
        if (StringUtils.isNotBlank(author.getName())) {
            return StringUtils.EMPTY;
        }

        if (StringUtils.isNotBlank(author.getUrl())) {
            return link(LINK, author.getUrl());
        }

        return StringUtils.EMPTY;
    }
}
