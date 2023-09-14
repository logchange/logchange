package dev.logchange.core.format.yml.config.labels;

//import de.beosign.snakeyamlanno.property.YamlAnySetter;
//import de.beosign.snakeyamlanno.property.YamlProperty;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfigurationAction;
import dev.logchange.core.domain.config.model.labels.ConfigurationActionLabels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLConfigurationActionLabels {

    public static final YMLConfigurationActionLabels EMPTY = YMLConfigurationActionLabels.builder().build();

    @JsonProperty(index = 0)
    public String add;

    @JsonProperty(index = 1)
    public String update;

    @JsonProperty(index = 1)
    public String delete;

    public static YMLConfigurationActionLabels of(ConfigurationActionLabels actions) {
        return YMLConfigurationActionLabels.builder()
                .add(actions.getAction(ChangelogEntryConfigurationAction.ADD))
                .update(actions.getAction(ChangelogEntryConfigurationAction.UPDATE))
                .delete(actions.getAction(ChangelogEntryConfigurationAction.DELETE))
                .build();
    }

    @JsonAnySetter
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
