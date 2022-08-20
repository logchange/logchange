package dev.logchange.core.domain.config.model.labels;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Labels {

    public static final Labels EMPTY = Labels.builder()
            .build();

    private String importantNotes;
    private String added;
    private String changed;
    private String deprecated;
    private String removed;
    private String fixed;
    private String security;
    private String other;
    private ConfigurationLabels configuration;

}
