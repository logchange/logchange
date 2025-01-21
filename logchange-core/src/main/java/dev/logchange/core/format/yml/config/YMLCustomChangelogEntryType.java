package dev.logchange.core.format.yml.config;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import lombok.*;

@Data
@Builder
@CustomLog
@NoArgsConstructor
@AllArgsConstructor
public class YMLCustomChangelogEntryType {

    @JsonProperty(index = 0)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String key;

    @JsonProperty(index = 1)
    public Integer order;

    static YMLCustomChangelogEntryType of(ChangelogEntryType entryType) {
        return YMLCustomChangelogEntryType.builder()
                .key(entryType.getKey())
                .order(entryType.getOrder())
                .build();
    }

    ChangelogEntryType to() {
        return ChangelogEntryType.of(key, order);
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warn("Unknown property: " + key + " with value " + value);
    }
}
