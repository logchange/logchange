package dev.logchange.core.format.yml.config.labels;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.config.model.labels.ConfigurationLabels;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class YMLConfigurationLabels {

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

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public ConfigurationLabels to() {
        return ConfigurationLabels.builder()
                .heading(heading)
                .type(type)
                .actions(actions.to())
                .withDefaultValue(withDefaultValue)
                .description(description)
                .build();
    }
}
