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
        // 1. Remove empty square brackets with empty parentheses or unnecessary spaces.
        text = text.replaceAll("\\[([^]]*)]\\(\\s*\\)", "$1");

        // 2. Replace empty square brackets with the word "LINK" if there is a URL in parentheses.
        text = text.replaceAll("\\[\\s*]\\(([^)]+)\\)", "[LINK]($1)");

        // 3. Remove the lone "@" before a closing parenthesis.
        text = text.replaceAll("\\s*@\\)", ")");

        // 4. Remove leading and trailing spaces around parentheses (both opening and closing).
        text = text.replaceAll("\\(\\s*", "(").replaceAll("\\s*\\)", ")");

        // 5. Replace multiple spaces with a single space.
        return text.replaceAll("\\s{2,}", " ").trim();
    }
}
