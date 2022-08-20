package dev.logchange.core.domain.config.model.labels;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ConfigurationLabels {

    private String changes;
    private String type;
    private ConfigurationActionLabels action;
    private String withDefaultValue;
    private String description;

}
