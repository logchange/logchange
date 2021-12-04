package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class YMLChangelogEntryConfiguration implements Comparable<YMLChangelogEntryConfiguration> {

    private String type;
    private YMLChangelogEntryConfigurationAction action;
    private String key;
    private String defaultValue;
    private String description;
    private String moreInfo;

    @YamlProperty(key = "action", converter = YMLChangelogEntryConfigurationActionConverter.class)
    public void setAction(YMLChangelogEntryConfigurationAction action) {
        this.action = action;
    }

    @YamlProperty(key = "default_value")
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @YamlProperty(key = "more_info")
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

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
}
