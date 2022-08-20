package dev.logchange.core.domain.config.model.labels;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfigurationAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

@Builder
@AllArgsConstructor
public class ConfigurationActionLabels {

    public static final String DEFAULT_ADD_LABEL = "Added";
    public static final String DEFAULT_UPDATE_LABEL = "Updated";
    public static final String DEFAULT_DELETE_LABEL = "Deleted";

    private String add;
    private String update;
    private String delete;

    public String getAction(ChangelogEntryConfigurationAction action) {
        switch (action){
            case ADD:
                return StringUtils.defaultIfBlank(add, DEFAULT_ADD_LABEL);
            case UPDATE:
                return StringUtils.defaultIfBlank(update, DEFAULT_ADD_LABEL);
            case DELETE:
                return StringUtils.defaultIfBlank(delete, DEFAULT_DELETE_LABEL);
            default:
                throw new IllegalArgumentException("Unrecognized action: " + action);
        }
    }
}
