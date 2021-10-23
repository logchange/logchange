package dev.logchange.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangelogEntryAuthor {
    private final String name;
    private final String nick;
    private final String url;
}
