package dev.logchange.core.format.yml.changelog.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum YMLChangelogEntryConfigurationAction {

    ADD("add", "Added"),
    UPDATE("update", "Updated"),
    DELETE("delete", "Deleted");

    private final String action;
    private final String displayText;
}
