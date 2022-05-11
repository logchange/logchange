package dev.logchange.core.domain.changelog.model.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ChangelogEntryLink {
    private final String name;
    private final String url;

    public static ChangelogEntryLink of(String name, String url) {
        return new ChangelogEntryLink(name, url);
    }

}
