package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
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

    private String name;
    private String url;

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    ChangelogEntryLink to() {
        return ChangelogEntryLink.of(name, url);
    }

    static YMLChangelogEntryLink of(ChangelogEntryLink link){
        return YMLChangelogEntryLink.builder()
                .name(link.getName())
                .url(link.getUrl())
                .build();
    }


}