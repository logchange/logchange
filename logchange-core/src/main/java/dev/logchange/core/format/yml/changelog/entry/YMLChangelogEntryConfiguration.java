package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(index = 0)
    public String type;

    @JsonProperty(index = 1)
    public YMLChangelogEntryConfigurationAction action;

    @JsonProperty(index = 2)
    public String key;

    @JsonProperty(value = "default_value", index = 3)
    public String defaultValue;

    @JsonProperty(index = 4)
    public String description;

    @JsonProperty(value = "more_info", index = 5)
    public String moreInfo;

    @JsonAnySetter
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
