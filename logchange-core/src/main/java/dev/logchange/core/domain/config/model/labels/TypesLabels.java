package dev.logchange.core.domain.config.model.labels;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType.*;

@Log
@Builder
@AllArgsConstructor
public class TypesLabels {

    public static final String DEFAULT_ADDED_LABEL = "Added";
    public static final String DEFAULT_CHANGED_LABEL = "Changed";
    public static final String DEFAULT_DEPRECATED_LABEL = "Deprecated";
    public static final String DEFAULT_REMOVED_LABEL = "Removed";
    public static final String DEFAULT_FIXED_LABEL = "Fixed";
    public static final String DEFAULT_SECURITY_LABEL = "Security";

    public static final String DEFAULT_DEPENDENCY_UPDATE_LABEL = "Dependency updates";
    public static final String DEFAULT_OTHER_LABEL = "Other";

    private static final Map<String, String> defaultLabels = new HashMap<>();
    public static final TypesLabels EMPTY = TypesLabels.builder()
            .entryTypesLabels(defaultLabels)
            .numberOfChanges(NumberOfChangesLabels.EMPTY)
            .build();

    static {
        defaultLabels.put(DEFAULT_ENTRY_TYPE_ADDED, DEFAULT_ADDED_LABEL);
        defaultLabels.put(DEFAULT_ENTRY_TYPE_CHANGED, DEFAULT_CHANGED_LABEL);
        defaultLabels.put(DEFAULT_ENTRY_TYPE_DEPRECATED, DEFAULT_DEPRECATED_LABEL);
        defaultLabels.put(DEFAULT_ENTRY_TYPE_REMOVED, DEFAULT_REMOVED_LABEL);
        defaultLabels.put(DEFAULT_ENTRY_TYPE_FIXED, DEFAULT_FIXED_LABEL);
        defaultLabels.put(DEFAULT_ENTRY_TYPE_SECURITY, DEFAULT_SECURITY_LABEL);
        defaultLabels.put(DEFAULT_ENTRY_TYPE_DEPENDENCY_UPDATE, DEFAULT_DEPENDENCY_UPDATE_LABEL);
        defaultLabels.put(DEFAULT_ENTRY_TYPE_OTHER, DEFAULT_OTHER_LABEL);
    }

    @Getter
    private Map<String, String> entryTypesLabels;

    @Getter
    private NumberOfChangesLabels numberOfChanges;

    public String getType(ChangelogEntryType type) {
        String label = entryTypesLabels.get(type.getKey());

        if (label == null) {
            return StringUtils.capitalize(type.getKey().replace("_", " "));
        } else {
            return label;
        }
    }
}
