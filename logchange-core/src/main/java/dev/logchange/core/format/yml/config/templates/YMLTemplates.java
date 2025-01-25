package dev.logchange.core.format.yml.config.templates;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.templates.Templates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Log
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLTemplates {

    @JsonProperty(index = 0)
    public String entry;

    @JsonProperty(index = 1)
    public String author;

    public static YMLTemplates of(Templates templates) {
        return YMLTemplates.builder()
                .entry(templates.getEntryFormat())
                .author(templates.getAuthorFormat())
                .build();
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warning("Unknown property: " + key + " with value " + value);
    }

    public Templates to() {
        return Templates.builder()
                .entryFormat(entry)
                .authorFormat(author)
                .build();
    }
}
