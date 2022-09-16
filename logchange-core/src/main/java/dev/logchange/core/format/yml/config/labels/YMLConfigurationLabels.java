package dev.logchange.core.format.yml.config.labels;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.config.model.labels.ConfigurationLabels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLConfigurationLabels {

    public static final YMLConfigurationLabels EMPTY = YMLConfigurationLabels.builder().build();

    @YamlProperty(key = "heading", order = 0)
    public String heading;

    @YamlProperty(key = "type", order = -1)
    public String type;

    @YamlProperty(key = "actions", order = -2)
    public YMLConfigurationActionLabels actions;

    @YamlProperty(key = "with_default_value", order = -3)
    public String withDefaultValue;

    @YamlProperty(key = "description", order = -4)
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

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
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
