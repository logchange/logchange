package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

class MDChangelogEntryAuthor extends Configurable implements MD {

    private final ChangelogEntryAuthor author;

    MDChangelogEntryAuthor(ChangelogEntryAuthor author, Config config) {
        super(config);
        this.author = author;
    }

    @Override
    public String toString() {
        return getAuthor();
    }

    private String getAuthor() {
        Map<String, String> valuesMap = new HashMap<>();
        String authorFormat = getConfig().getTemplates().getAuthorFormat();
        valuesMap.put("name", StringUtils.defaultString(author.getName()));
        valuesMap.put("url", StringUtils.defaultString(author.getUrl()));
        valuesMap.put("nick", StringUtils.defaultString(author.getNick()));
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return format(sub.replace(authorFormat).trim());
    }

    private String format(String text) {
        // This replaces any empty square brackets and empty parentheses with just the content inside the brackets (if any).
        text = text.replaceAll("\\[([^]]*)]\\(\\s*\\)", "$1");

        // Replace empty square brackets with the word "LINK" if there is a URL in parentheses
        text = text.replaceAll("\\[\\s*]\\(([^)]+)\\)", "[LINK]($1)");

        // This removes the "@" when it appears alone just before a closing parenthesis.
        text = text.replaceAll("\\s*@\\)", ")");

        // Remove leading and trailing spaces around parentheses
        text = text.replaceAll("\\(\\s*", "(").replaceAll("\\s*\\)", ")");

        // This reduces any occurrence of multiple spaces to a single space.
        return text.replaceAll("\\s{2,}", " ").trim();
    }
}
