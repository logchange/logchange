package dev.logchange.core.format.yml.config.templates;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.templates.Templates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.extern.java.Log;

import java.util.List;
import java.util.stream.Collectors;

@Log
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLTemplates {

    @JsonProperty(index = 0)
    public String entry;

    @JsonProperty(index = 1)
    public String author;

    @Singular
    @JsonProperty(value = "version_summary_templates", index = 2)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<YMLVersionSummaryTemplate> versionSummaryTemplates;

    public static YMLTemplates of(Templates templates) {
        return YMLTemplates.builder()
                .entry(templates.getEntryFormat())
                .author(templates.getAuthorFormat())
                .versionSummaryTemplates(templates.getVersionSummaryTemplates().stream()
                        .map(YMLVersionSummaryTemplate::of)
                        .collect(Collectors.toList()))
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
                .versionSummaryTemplates(versionSummaryTemplates.stream()
                        .map(YMLVersionSummaryTemplate::to)
                        .collect(Collectors.toList()))
                .build();
    }
}
