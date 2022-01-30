package dev.logchange.core.format.yml.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfigurationAction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YMLChangelogEntryConfigurationAction {

    ADD("add"),
    UPDATE("update"),
    DELETE("delete");

    private final String action;

    ChangelogEntryConfigurationAction to() {
        switch (this) {
            case ADD:
                return ChangelogEntryConfigurationAction.ADD;
            case UPDATE:
                return ChangelogEntryConfigurationAction.UPDATE;
            case DELETE:
                return ChangelogEntryConfigurationAction.DELETE;
            default:
                throw new IllegalArgumentException("Converting ChangelogEntryConfigurationAction failed");
        }
    }

    static YMLChangelogEntryConfigurationAction of(ChangelogEntryConfigurationAction action){
        switch (action) {
            case ADD:
                return YMLChangelogEntryConfigurationAction.ADD;
            case UPDATE:
                return YMLChangelogEntryConfigurationAction.UPDATE;
            case DELETE:
                return YMLChangelogEntryConfigurationAction.DELETE;
            default:
                throw new IllegalArgumentException("Converting ChangelogEntryConfigurationAction failed");
        }
    }

}
