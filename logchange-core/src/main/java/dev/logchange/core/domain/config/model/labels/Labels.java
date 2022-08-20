package dev.logchange.core.domain.config.model.labels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;


@Getter
@Builder
@AllArgsConstructor
public class Labels {

    public static final String DEFAULT_UNRELEASED_LABEL = "unreleased";
    public static final String DEFAULT_IMPORTANT_NOTES_LABEL = "Important notes";

    public static final Labels EMPTY = Labels.builder()
            .types(TypesLabels.EMPTY)
            .configuration(ConfigurationLabels.EMPTY)
            .build();

    private String unreleased;
    private String importantNotes;
    private TypesLabels types;
    private ConfigurationLabels configuration;

    public String getUnreleased() {
        return defaultIfBlank(unreleased, DEFAULT_UNRELEASED_LABEL);
    }

    public String getImportantNotes() {
        return defaultIfBlank(importantNotes, DEFAULT_IMPORTANT_NOTES_LABEL);
    }
}
