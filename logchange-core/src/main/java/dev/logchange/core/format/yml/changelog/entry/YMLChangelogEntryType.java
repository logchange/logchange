package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.util.Arrays;

@Log
@AllArgsConstructor
public enum YMLChangelogEntryType {

    ADDED("added"),
    CHANGED("changed"),
    DEPRECATED("deprecated"),
    REMOVED("removed"),
    FIXED("fixed"),
    SECURITY("security"),
    DEPENDENCY_UPDATE("dependency_update"),
    OTHER("other");

    private final String type;

    static YMLChangelogEntryType of(ChangelogEntryType type) {
        switch (type) {
            case ADDED:
                return YMLChangelogEntryType.ADDED;
            case CHANGED:
                return YMLChangelogEntryType.CHANGED;
            case DEPRECATED:
                return YMLChangelogEntryType.DEPRECATED;
            case REMOVED:
                return YMLChangelogEntryType.REMOVED;
            case FIXED:
                return YMLChangelogEntryType.FIXED;
            case SECURITY:
                return YMLChangelogEntryType.SECURITY;
            case DEPENDENCY_UPDATE:
                return YMLChangelogEntryType.DEPENDENCY_UPDATE;
            case OTHER:
                return YMLChangelogEntryType.OTHER;
            default:
                String message = "Converting YMLChangelogEntryType failed";
                log.severe(message);
                throw new IllegalArgumentException(message);
        }
    }

    ChangelogEntryType to() {
        switch (this) {
            case ADDED:
                return ChangelogEntryType.ADDED;
            case CHANGED:
                return ChangelogEntryType.CHANGED;
            case DEPRECATED:
                return ChangelogEntryType.DEPRECATED;
            case REMOVED:
                return ChangelogEntryType.REMOVED;
            case FIXED:
                return ChangelogEntryType.FIXED;
            case SECURITY:
                return ChangelogEntryType.SECURITY;
            case DEPENDENCY_UPDATE:
                return ChangelogEntryType.DEPENDENCY_UPDATE;
            case OTHER:
                return ChangelogEntryType.OTHER;
            default:
                String message = "Converting ChangelogEntryType failed";
                log.severe(message);
                throw new IllegalArgumentException(message);
        }
    }

    @JsonCreator
    public static YMLChangelogEntryType of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.getType().equals(name))
                .findFirst()
                .orElseThrow(() -> {
                    String message = "Cannot match YMLChangelogEntryType for string: " + name;
                    log.severe(message);
                    return new IllegalArgumentException(message);
                });

    }

    @JsonValue
    public String getType() {
        return type;
    }
}
