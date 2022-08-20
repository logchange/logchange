package dev.logchange.core.domain.config.model.labels;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ConfigurationLabels {

    public static final ConfigurationLabels EMPTY = ConfigurationLabels.builder().build();

    private String changes;
    private String type;
    private ConfigurationActionLabels action;
    private String withDefaultValue;
    private String description;

}
