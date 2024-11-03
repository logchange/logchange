package dev.logchange.core.domain.config.model.aggregate;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class AggregatedProject {

    private final String name;
    private final String url;
    private final AggregatedProjectType type;
    private final String inputDir;

}
