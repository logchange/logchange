package dev.logchange.core.domain.changelog.model.entry;

import dev.logchange.core.domain.config.model.CustomChangelogEntryType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntryType {

    public final static String DEFAULT_ENTRY_TYPE_ADDED = "added";
    public final static String DEFAULT_ENTRY_TYPE_CHANGED = "changed";
    public final static String DEFAULT_ENTRY_TYPE_DEPRECATED = "deprecated";
    public final static String DEFAULT_ENTRY_TYPE_REMOVED = "removed";
    public final static String DEFAULT_ENTRY_TYPE_FIXED = "fixed";
    public final static String DEFAULT_ENTRY_TYPE_SECURITY = "security";
    public final static String DEFAULT_ENTRY_TYPE_DEPENDENCY_UPDATE = "dependency_update";
    public final static String DEFAULT_ENTRY_TYPE_OTHER = "other";

    @Getter
    private final static List<ChangelogEntryType> defaultEntryTypes = new ArrayList<>();

    private static List<ChangelogEntryType> entryTypes = new ArrayList<>();

    static {
        defaultEntryTypes.add(new ChangelogEntryType(DEFAULT_ENTRY_TYPE_ADDED, 1));
        defaultEntryTypes.add(new ChangelogEntryType(DEFAULT_ENTRY_TYPE_CHANGED, 2));
        defaultEntryTypes.add(new ChangelogEntryType(DEFAULT_ENTRY_TYPE_DEPRECATED, 3));
        defaultEntryTypes.add(new ChangelogEntryType(DEFAULT_ENTRY_TYPE_REMOVED, 4));
        defaultEntryTypes.add(new ChangelogEntryType(DEFAULT_ENTRY_TYPE_FIXED, 5));
        defaultEntryTypes.add(new ChangelogEntryType(DEFAULT_ENTRY_TYPE_SECURITY, 6));
        defaultEntryTypes.add(new ChangelogEntryType(DEFAULT_ENTRY_TYPE_DEPENDENCY_UPDATE, 7));
        defaultEntryTypes.add(new ChangelogEntryType(DEFAULT_ENTRY_TYPE_OTHER, 8));
    }

    private final String key;
    private final Integer order;

    public static ChangelogEntryType of(String key, Integer order) {
        return new ChangelogEntryType(key, order);
    }

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
        if (entryTypes == null || entryTypes.isEmpty()) {
            return defaultEntryTypes;
        } else {
            return entryTypes;
        }
    }

    public static void setEntryTypes(List<CustomChangelogEntryType> entryTypes) {
        if (ChangelogEntryType.entryTypes == null || ChangelogEntryType.entryTypes.isEmpty()) {
            if (entryTypes != null && !entryTypes.isEmpty()) {
                if (ChangelogEntryType.entryTypes == null) {
                    ChangelogEntryType.entryTypes = new ArrayList<>();
                }

                for (CustomChangelogEntryType type : entryTypes) {
                    ChangelogEntryType.entryTypes.add(type.to());
                }
            }
        }
    }

    public static void clear(){
        entryTypes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return order + ". " + getKey().toLowerCase();
    }
}
