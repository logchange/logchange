package dev.logchange.core.format.yml.config;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.config.model.labels.Labels;
import dev.logchange.core.format.yml.config.labels.YMLLabels;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelog {

    @YamlProperty(key = "labels", order = 0)
    public YMLLabels labels;

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public Labels getLabels() {
        if (labels == null) {
            return Labels.EMPTY;
        } else {
            return labels.to();
        }
    }

}