package dev.logchange.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangelogEntryLink {
    private final String name;
    private final String url;
}
