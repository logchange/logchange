package dev.logchange.core.domain.config.model.labels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Builder
@AllArgsConstructor
public class ConfigurationLabels {

    public static final String DEFAULT_HEADING_LABEL = "Configuration changes";
    public static final String DEFAULT_TYPE_LABEL = "Type";
    public static final String DEFAULT_WITH_DEFAULT_VALUE_LABEL = "with default value";
    public static final String DEFAULT_DESCRIPTION_LABEL = "Description";

    public static final ConfigurationLabels EMPTY = ConfigurationLabels.builder()
            .actions(ConfigurationActionLabels.EMPTY)
            .build();

    private String heading;
    private String type;
    private ConfigurationActionLabels actions;
    private String withDefaultValue;
    private String description;

    public String getHeading() {
        return StringUtils.defaultIfBlank(heading, DEFAULT_HEADING_LABEL);
    }

    public String getType() {
        return StringUtils.defaultIfBlank(type, DEFAULT_TYPE_LABEL);
    }

    public String getWithDefaultValue() {
        return StringUtils.defaultIfBlank(withDefaultValue, DEFAULT_WITH_DEFAULT_VALUE_LABEL);
    }

    public String getDescription() {
        return StringUtils.defaultIfBlank(description, DEFAULT_DESCRIPTION_LABEL);
    }
}
