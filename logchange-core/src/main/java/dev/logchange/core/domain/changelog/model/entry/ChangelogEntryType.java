package dev.logchange.core.domain.changelog.model.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntryType {

    private final static List<ChangelogEntryType> defaultEntryTypes = new ArrayList<>();

    static {
        defaultEntryTypes.add(new ChangelogEntryType("added", 1));
        defaultEntryTypes.add(new ChangelogEntryType("changed", 2));
        defaultEntryTypes.add(new ChangelogEntryType("deprecated", 3));
        defaultEntryTypes.add(new ChangelogEntryType("removed", 4));
        defaultEntryTypes.add(new ChangelogEntryType("fixed", 5));
        defaultEntryTypes.add(new ChangelogEntryType("security", 6));
        defaultEntryTypes.add(new ChangelogEntryType("dependency_update", 7));
        defaultEntryTypes.add(new ChangelogEntryType("other", 8));
    }

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
