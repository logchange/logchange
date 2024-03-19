package dev.logchange.core.domain.config.model.labels;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfigurationAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;

@Log
@Builder
@AllArgsConstructor
public class ConfigurationActionLabels {

    public static final String DEFAULT_ADD_LABEL = "Added";
    public static final String DEFAULT_UPDATE_LABEL = "Updated";
    public static final String DEFAULT_DELETE_LABEL = "Deleted";

    public static final ConfigurationActionLabels EMPTY = ConfigurationActionLabels.builder().build();

    private String add;
    private String update;
    private String delete;

    public String getAction(ChangelogEntryConfigurationAction action) {
        switch (action){
            case ADD:
                return StringUtils.defaultIfBlank(add, DEFAULT_ADD_LABEL);
            case UPDATE:
                return StringUtils.defaultIfBlank(update, DEFAULT_UPDATE_LABEL);
            case DELETE:
                return StringUtils.defaultIfBlank(delete, DEFAULT_DELETE_LABEL);
            default:
                String message = "Unrecognized configuration action: " + action;
                log.severe(message);
                throw new IllegalArgumentException(message);
        }
    }
}
