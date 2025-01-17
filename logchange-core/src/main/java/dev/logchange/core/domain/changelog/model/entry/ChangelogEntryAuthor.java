package dev.logchange.core.domain.changelog.model.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntryAuthor {
    private final String name;
    private final String nick;
    private final String url;

    public static ChangelogEntryAuthor of(String name, String nick, String url) {
        if (StringUtils.isBlank(name) && StringUtils.isBlank(nick) && StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("Cannot create author with all blank properties!");
        }

        if (StringUtils.isBlank(name)) {
            name = StringUtils.EMPTY;
        }

        if (StringUtils.isBlank(nick)) {
            nick = StringUtils.EMPTY;
        }

        if (StringUtils.isBlank(url)) {
            url = StringUtils.EMPTY;
        }

        return new ChangelogEntryAuthor(name, nick, url);
    }

    public static ChangelogEntryAuthor of(String author) {
        if (StringUtils.isBlank(author)) {
            throw new IllegalArgumentException("Cannot create blank author");
        }
        String[] authorParts = author.split(";");

        String name = (authorParts.length > 0 ? authorParts[0] : "").trim();
        String nick = (authorParts.length > 1 ? authorParts[1] : "").trim();
        String url = (authorParts.length > 2 ? authorParts[2] : "").trim();

        return ChangelogEntryAuthor.of(name, nick, url);
    }

}
