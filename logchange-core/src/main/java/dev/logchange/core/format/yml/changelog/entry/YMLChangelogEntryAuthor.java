package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.property.YamlAnySetter;
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

    private String name;
    private String nick;
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
