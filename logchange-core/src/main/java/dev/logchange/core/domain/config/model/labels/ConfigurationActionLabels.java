package dev.logchange.core.domain.config.model.labels;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ConfigurationActionLabels {

    public String add;
    public String update;
    public String delete;

}
