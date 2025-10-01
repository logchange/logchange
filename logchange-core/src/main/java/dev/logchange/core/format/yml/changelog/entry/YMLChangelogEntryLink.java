package dev.logchange.core.format.yml.changelog.entry;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryLink;
import lombok.*;

@Data
@Builder
@CustomLog
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelogEntryLink {

    @JsonProperty(index = 0)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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
        log.warn("Unknown property: " + key + " with value " + value);
    }
}
