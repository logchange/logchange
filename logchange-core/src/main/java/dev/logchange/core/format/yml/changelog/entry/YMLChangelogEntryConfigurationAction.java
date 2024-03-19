package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfigurationAction;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.util.Arrays;

@Log
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
                String message = "Converting ChangelogEntryConfigurationAction failed";
                log.severe(message);
                throw new IllegalArgumentException(message);
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
                String message = "Converting YMLChangelogEntryConfigurationAction failed";
                log.severe(message);
                throw new IllegalArgumentException(message);
        }
    }

    @JsonCreator
    public static YMLChangelogEntryConfigurationAction of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.getAction().equals(name))
                .findFirst()
                .orElseThrow(() -> {
                    String message = "Cannot match YMLChangelogEntryConfigurationAction for string: " + name;
                    log.severe(message);
                    return new IllegalArgumentException(message);
                });
    }


    @JsonValue
    public String getAction() {
        return action;
    }
}
