package dev.logchange.core.domain.config.model.labels;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@Builder
@AllArgsConstructor
public class TypesLabels {

    public static final String DEFAULT_ADDED_LABEL = "Added";
    public static final String DEFAULT_CHANGED_LABEL = "Changed";
    public static final String DEFAULT_DEPRECATED_LABEL = "Deprecated";
    public static final String DEFAULT_REMOVED_LABEL = "Removed";
    public static final String DEFAULT_FIXED_LABEL = "Fixed";
    public static final String DEFAULT_SECURITY_LABEL = "Security";
    public static final String DEFAULT_OTHER_LABEL = "Other";

    public static final TypesLabels EMPTY = TypesLabels.builder().build();

    private String added;
    private String changed;
    private String deprecated;
    private String removed;
    private String fixed;
    private String security;
    private String other;

    public String getType(ChangelogEntryType type) {
        switch (type) {
            case ADDED:
                return defaultIfBlank(added, DEFAULT_ADDED_LABEL);
            case CHANGED:
                return defaultIfBlank(changed, DEFAULT_CHANGED_LABEL);
            case DEPRECATED:
                return defaultIfBlank(deprecated, DEFAULT_DEPRECATED_LABEL);
            case REMOVED:
                return defaultIfBlank(removed, DEFAULT_REMOVED_LABEL);
            case FIXED:
                return defaultIfBlank(fixed, DEFAULT_FIXED_LABEL);
            case SECURITY:
                return defaultIfBlank(security, DEFAULT_SECURITY_LABEL);
            case OTHER:
                return defaultIfBlank(other, DEFAULT_OTHER_LABEL);
            default:
                throw new IllegalArgumentException("Unrecognized type: " + type);
        }
    }
}
