package dev.logchange.core.domain.config.model.labels;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

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

    public static final TypesLabels EMPTY = TypesLabels.builder()
            .numberOfChanges(NumberOfChangesLabels.EMPTY)
            .build();

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
