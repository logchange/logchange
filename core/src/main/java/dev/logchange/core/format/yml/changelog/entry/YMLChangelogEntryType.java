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
    SECURITY("security");

    private final String type;

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
            default:
                throw new IllegalArgumentException("Converting ChangelogEntryType failed");
        }
    }

}
