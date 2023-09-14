package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(index = 0)
    public String name;
    @JsonProperty(index = 1)
    private String nick;
    @JsonProperty(index = 2)
    private String url;

    static YMLChangelogEntryAuthor of(ChangelogEntryAuthor author) {
        return YMLChangelogEntryAuthor.builder()
                .name(author.getName())
                .nick(author.getNick())
                .url(author.getUrl())
                .build();
    }

    ChangelogEntryAuthor to() {
        return ChangelogEntryAuthor.of(name, nick, url);
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

}
