package dev.logchange.core.format.yml.config.labels;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.config.model.labels.NumberOfChangesLabels;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class YMLNumberOfChangesLabels {

    @YamlProperty(key = "singular", order = 0)
    public String singular;

    @YamlProperty(key = "plural", order = -1)
    public String plural;

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public NumberOfChangesLabels to() {
        return NumberOfChangesLabels.builder()
                .singular(singular)
                .plural(plural)
                .build();
    }
}