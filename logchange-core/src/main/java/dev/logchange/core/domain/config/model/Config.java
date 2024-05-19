package dev.logchange.core.domain.config.model;

import dev.logchange.core.domain.config.model.aggregate.Aggregates;
import dev.logchange.core.domain.config.model.labels.Labels;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class Config {

    public static final Config EMPTY = Config.builder()
            .heading(Heading.EMPTY)
            .labels(Labels.EMPTY)
            .aggregates(Aggregates.EMPTY)
            .build();

    private Heading heading;
    private Labels labels;

    private Aggregates aggregates;

}
