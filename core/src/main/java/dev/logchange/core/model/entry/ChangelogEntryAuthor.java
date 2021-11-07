package dev.logchange.core.model.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
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

}
