package dev.logchange.core.domain.config.model.aggregate;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Aggregates {

    public static final Aggregates EMPTY = new Aggregates(Collections.emptyList());

    private final List<AggregatedProject> projects;


}
