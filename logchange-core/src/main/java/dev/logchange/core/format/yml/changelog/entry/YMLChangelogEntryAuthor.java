package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import lombok.*;


@Data
@Builder
@CustomLog
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelogEntryAuthor {

    @JsonProperty(index = 0)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String name;

    @JsonProperty(index = 1)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nick;

    @JsonProperty(index = 2)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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
        log.warn("Unknown property: " + key + " with value " + value);
    }

}
