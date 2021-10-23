package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelogEntryAuthor {

    private String name;
    private String nick;
    private String url;

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

}
