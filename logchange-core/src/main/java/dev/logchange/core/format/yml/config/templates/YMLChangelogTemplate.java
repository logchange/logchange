package dev.logchange.core.format.yml.config.templates;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.templates.ChangelogTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Log
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelogTemplate {

    @JsonProperty(index = 0)
    private String path;

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warning("Unknown property: " + key + " with value " + value);
    }

    static YMLChangelogTemplate of(ChangelogTemplate changelogTemplate) {
        return YMLChangelogTemplate.builder()
                .path(changelogTemplate.getPath())
                .build();
    }

    ChangelogTemplate to() {
        return ChangelogTemplate.of(path);
    }
}
