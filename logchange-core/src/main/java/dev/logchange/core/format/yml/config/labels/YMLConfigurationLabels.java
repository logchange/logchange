package dev.logchange.core.format.yml.config.labels;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.labels.ConfigurationLabels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Log
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLConfigurationLabels {

    public static final YMLConfigurationLabels EMPTY = YMLConfigurationLabels.builder().build();

    @JsonProperty(index = 0)
    public String heading;

    @JsonProperty(index = 1)
    public String type;

    @JsonProperty(index = 2)
    public YMLConfigurationActionLabels actions;

    @JsonProperty(value = "with_default_value", index = 3)
    public String withDefaultValue;

    @JsonProperty(index = 4)
    public String description;

    public static YMLConfigurationLabels of(ConfigurationLabels configuration) {
        return YMLConfigurationLabels.builder()
                .heading(configuration.getHeading())
                .type(configuration.getType())
                .actions(YMLConfigurationActionLabels.of(configuration.getActions()))
                .withDefaultValue(configuration.getWithDefaultValue())
                .description(configuration.getDescription())
                .build();
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warning("Unknown property: " + key + " with value " + value);
    }

    public ConfigurationLabels to() {
        return ConfigurationLabels.builder()
                .heading(heading)
                .type(type)
                .actions(getActions().to())
                .withDefaultValue(withDefaultValue)
                .description(description)
                .build();
    }

    public YMLConfigurationActionLabels getActions() {
        if (actions != null) {
            return actions;
        } else {
            return YMLConfigurationActionLabels.EMPTY;
        }
    }
}
