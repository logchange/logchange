package dev.logchange.core.format.yml.config.labels;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.config.model.labels.Labels;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class YMLLabels {

    @YamlProperty(key = "unreleased", order = 0)
    public String unreleased;

    @YamlProperty(key = "important_notes", order = -1)
    public String importantNotes;

    @YamlProperty(key = "types", order = -2)
    public YMLTypesLabels types;

    @YamlProperty(key = "configuration", order = -3)
    public YMLConfigurationLabels configuration;

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public Labels to() {
        return Labels.builder()
                .unreleased(unreleased)
                .importantNotes(importantNotes)
                .types(types.to())
                .configuration(configuration.to())
                .build();
    }
}
