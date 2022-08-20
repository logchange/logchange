package dev.logchange.core.format.yml.config.labels;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.config.model.labels.TypesLabels;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class YMLTypesLabels {
    @YamlProperty(key = "added", order = -1)
    public String added;

    @YamlProperty(key = "changed", order = -2)
    public String changed;

    @YamlProperty(key = "deprecated", order = -3)
    public String deprecated;

    @YamlProperty(key = "removed", order = -4)
    public String removed;

    @YamlProperty(key = "fixed", order = -5)
    public String fixed;

    @YamlProperty(key = "security", order = -6)
    public String security;

    @YamlProperty(key = "other", order = -7)
    public String other;

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public TypesLabels to() {
        return TypesLabels.builder()
                .added(added)
                .changed(changed)
                .deprecated(deprecated)
                .removed(removed)
                .fixed(fixed)
                .security(security)
                .other(other)
                .build();
    }
}
