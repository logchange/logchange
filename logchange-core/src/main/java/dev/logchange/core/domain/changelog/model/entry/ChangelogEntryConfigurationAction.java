package dev.logchange.core.domain.changelog.model.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChangelogEntryConfigurationAction {

    ADD("add", "Added", 1),
    UPDATE("update", "Updated", 2),
    DELETE("delete", "Deleted", 3);

    private final String action;

    @Deprecated
    private final String displayText;
    private final Integer order;


    public static ChangelogEntryConfigurationAction from(String orderNumber) {
        for (ChangelogEntryConfigurationAction type : values()) {
            if (type.getOrder().toString().equals(orderNumber)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Incorrect type!");
    }

    @Override
    public String toString() {
        return order + ". " + action;
    }
}
