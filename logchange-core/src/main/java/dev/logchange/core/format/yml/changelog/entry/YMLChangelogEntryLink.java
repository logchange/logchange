package dev.logchange.core.format.yml.changelog.entry;

//import de.beosign.snakeyamlanno.property.YamlAnySetter;
//import de.beosign.snakeyamlanno.property.YamlProperty;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryLink;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelogEntryLink {

    @JsonProperty(index = 0)
    public String name;
    @JsonProperty(index = 1)
    public String url;

    static YMLChangelogEntryLink of(ChangelogEntryLink link) {
        return YMLChangelogEntryLink.builder()
                .name(link.getName())
                .url(link.getUrl())
                .build();
    }

    ChangelogEntryLink to() {
        return ChangelogEntryLink.of(name, url);
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }


}