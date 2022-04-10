package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelogEntryAuthor {

    @YamlProperty(key = "name", order = 0)
    public String name;
    @YamlProperty(key = "nick", order = -1)
    private String nick;
    @YamlProperty(key = "url", order = -2)
    private String url;

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    ChangelogEntryAuthor to() {
        return ChangelogEntryAuthor.of(name, nick, url);
    }

    static YMLChangelogEntryAuthor of(ChangelogEntryAuthor author){
        return YMLChangelogEntryAuthor.builder()
                .name(author.getName())
                .nick(author.getNick())
                .url(author.getUrl())
                .build();
    }

}
