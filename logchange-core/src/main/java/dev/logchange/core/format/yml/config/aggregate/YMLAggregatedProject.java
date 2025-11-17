package dev.logchange.core.format.yml.config.aggregate;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.aggregate.AggregatedProject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.CustomLog;
import lombok.NoArgsConstructor;


@CustomLog
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLAggregatedProject {

    @JsonProperty(index = 0)
    public String name;

    @JsonProperty(index = 1)
    public String url;

    @JsonProperty(index = 2)
    public YMLAggregatedProjectType type;

    @JsonProperty(value = "input_dir", index = 3)
    public String inputDir;

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warn("Unknown property: " + key + " with value " + value);
    }

    public AggregatedProject to() {
        return AggregatedProject.builder()
                .name(name)
                .url(url)
                .type(type.to())
                .inputDir(inputDir)
                .build();
    }
}
