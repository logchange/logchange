package dev.logchange.core.format.yml.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YMLChangelogEntryType {

    ADDED("added"),
    CHANGED("changed"),
    DEPRECATED("deprecated"),
    REMOVED("removed"),
    FIXED("fixed"),
    SECURITY("security"),

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
            case OTHER:
                return YMLChangelogEntryType.OTHER;
            default:
                throw new IllegalArgumentException("Converting ChangelogEntryType failed");
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
            case OTHER:
                return ChangelogEntryType.OTHER;
            default:
                throw new IllegalArgumentException("Converting ChangelogEntryType failed");
        }
    }

}
