package dev.logchange.core.domain.config.model;

import dev.logchange.core.domain.config.model.aggregate.Aggregates;
import dev.logchange.core.domain.config.model.labels.Labels;
import dev.logchange.core.domain.config.model.templates.Templates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
@AllArgsConstructor
public class Config {

    public static final Config EMPTY = Config.builder()
            .heading(Heading.EMPTY)
            .entryTypes(CustomChangelogEntryType.EMPTY)
            .labels(Labels.EMPTY)
            .templates(Templates.EMPTY)
            .aggregates(Aggregates.EMPTY)
            .build();

    private Heading heading;
    private List<CustomChangelogEntryType> entryTypes;
    private Labels labels;
    private Templates templates;
    private Aggregates aggregates;

}
