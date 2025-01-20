package dev.logchange.core.domain.changelog.model.entry;

import com.sun.tools.javac.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntryType {

    private final static List<ChangelogEntryType> defaultEntryTypes = List.of(
            new ChangelogEntryType("added", 1),
            new ChangelogEntryType("changed", 2),
            new ChangelogEntryType("deprecated", 3),
            new ChangelogEntryType("removed", 4),
            new ChangelogEntryType("fixed", 5),
            new ChangelogEntryType("security", 6),
            new ChangelogEntryType("dependency_update", 7),
            new ChangelogEntryType("other", 8)
    );

    private final String key;
    private final Integer order;


    public static ChangelogEntryType from(String orderNumber) {
        for (ChangelogEntryType type : values()) {
            if (type.getOrder().toString().equals(orderNumber)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Incorrect type!");
    }

    public static ChangelogEntryType fromNameIgnoreCase(String name) {
        for (ChangelogEntryType type : values()) {
            if (type.getKey().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Incorrect type!");
    }

    public static List<ChangelogEntryType> values() {
        return defaultEntryTypes;
    }

    @Override
    public String toString() {
        return order + ". " + getKey().toLowerCase();
    }
}
