package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfiguration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelogEntryConfiguration implements Comparable<YMLChangelogEntryConfiguration> {

    @YamlProperty(key = "type", order = 0)
    public String type;
    @YamlProperty(key = "action", order = -1, converter = YMLChangelogEntryConfigurationActionConverter.class)
    public YMLChangelogEntryConfigurationAction action;
    @YamlProperty(key = "key", order = -2)
    public String key;
    @YamlProperty(key = "default_value", order = -3)
    public String defaultValue;
    @YamlProperty(key = "description", order = -4)
    public String description;
    @YamlProperty(key = "more_info", order = -5)
    public String moreInfo;

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    @Override
    public int compareTo(YMLChangelogEntryConfiguration o) {
        return Comparator.comparing(YMLChangelogEntryConfiguration::getType)
                .thenComparing(YMLChangelogEntryConfiguration::getAction)
                .thenComparing(YMLChangelogEntryConfiguration::getKey)
                .compare(this, o);
    }

    ChangelogEntryConfiguration to() {
        return ChangelogEntryConfiguration.of(
                type,
                action.to(),
                key,
                defaultValue,
                description,
                moreInfo
        );
    }

    static YMLChangelogEntryConfiguration of(ChangelogEntryConfiguration configuration) {
        return YMLChangelogEntryConfiguration.builder()
                .type(configuration.getType())
                .action(YMLChangelogEntryConfigurationAction.of(configuration.getAction()))
                .key(configuration.getKey())
                .defaultValue(configuration.getDefaultValue())
                .description(configuration.getDescription())
                .moreInfo(configuration.getMoreInfo())
                .build();
    }
}
