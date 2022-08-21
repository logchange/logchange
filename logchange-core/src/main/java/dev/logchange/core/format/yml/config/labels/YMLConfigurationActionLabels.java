package dev.logchange.core.format.yml.config.labels;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.config.model.labels.ConfigurationActionLabels;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class YMLConfigurationActionLabels {

    @YamlProperty(key = "add", order = 0)
    public String add;

    @YamlProperty(key = "update", order = -1)
    public String update;

    @YamlProperty(key = "delete", order = -1)
    public String delete;

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public ConfigurationActionLabels to() {
        return ConfigurationActionLabels.builder()
                .add(add)
                .update(update)
                .delete(delete)
                .build();
    }
}
