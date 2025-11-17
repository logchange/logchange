package dev.logchange.core.format.yml.config.templates;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.templates.ChangelogTemplate;
import dev.logchange.core.domain.config.model.templates.Templates;
import dev.logchange.core.domain.config.model.templates.VersionSummaryTemplate;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CustomLog
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

    @Singular
    @JsonProperty(value = "changelog_templates", index = 3)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<YMLChangelogTemplate> changelogTemplates;

    public static YMLTemplates of(Templates templates) {
        return YMLTemplates.builder()
                .entry(templates.getEntryFormat())
                .author(templates.getAuthorFormat())
                .versionSummaryTemplates(templates.getVersionSummaryTemplates().stream()
                        .map(YMLVersionSummaryTemplate::of)
                        .collect(Collectors.toList()))
                .changelogTemplates(templates.getChangelogTemplates().stream()
                        .map(YMLChangelogTemplate::of)
                        .collect(Collectors.toList()))
                .build();
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warn("Unknown property: " + key + " with value " + value);
    }

    public Templates to() {
        return Templates.builder()
                .entryFormat(entry)
                .authorFormat(author)
                .versionSummaryTemplates(getVersionSummaryTemplates())
                .changelogTemplates(getChangelogTemplates())
                .build();
    }

    private List<VersionSummaryTemplate> getVersionSummaryTemplates() {
        if (versionSummaryTemplates == null) {
            return Collections.emptyList();
        } else {
            return versionSummaryTemplates.stream()
                    .map(YMLVersionSummaryTemplate::to)
                    .collect(Collectors.toList());
        }
    }

    private List<ChangelogTemplate> getChangelogTemplates() {
        if (changelogTemplates == null) {
            return Collections.emptyList();
        } else {
            return changelogTemplates.stream()
                    .map(YMLChangelogTemplate::to)
                    .collect(Collectors.toList());
        }
    }
}
