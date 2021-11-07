package dev.logchange.core.model.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChangelogEntryConfigurationAction {

    ADD("add", "Added"),
    UPDATE("update", "Updated"),
    DELETE("delete", "Deleted");

    private final String action;
    private final String displayText;
}
