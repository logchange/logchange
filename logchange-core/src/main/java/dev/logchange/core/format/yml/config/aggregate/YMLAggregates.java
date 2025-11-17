package dev.logchange.core.format.yml.config.aggregate;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.aggregate.AggregatedProject;
import dev.logchange.core.domain.config.model.aggregate.Aggregates;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CustomLog
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLAggregates {

    @Singular
    @JsonProperty(index = 1)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<YMLAggregatedProject> projects;

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warn("Unknown property: " + key + " with value " + value);
    }

    public Aggregates to() {
        return Aggregates.builder()
                .projects(toProjects())
                .build();
    }

    private List<AggregatedProject> toProjects() {
        if (projects == null) {
            return Collections.emptyList();
        } else {
            return projects.stream()
                    .map(YMLAggregatedProject::to)
                    .collect(Collectors.toList());
        }
    }
}
