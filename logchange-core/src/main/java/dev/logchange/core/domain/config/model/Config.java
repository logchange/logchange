package dev.logchange.core.domain.config.model;

import dev.logchange.core.domain.config.model.labels.Labels;
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
            .build();

    private Heading heading;
    private Labels labels;
}
