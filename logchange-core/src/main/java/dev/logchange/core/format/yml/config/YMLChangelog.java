package dev.logchange.core.format.yml.config;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.Heading;
import dev.logchange.core.domain.config.model.labels.Labels;
import dev.logchange.core.format.yml.config.labels.YMLLabels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelog {

    @JsonProperty(index = 0)
    public String heading;


    @JsonProperty(index = 1)
    public YMLLabels labels;

    public static YMLChangelog of(Config config) {
        return YMLChangelog.builder()
                .heading(config.getHeading().getValue())
                .labels(YMLLabels.of(config.getLabels()))
                .build();
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public Labels toLabels() {
        if (labels == null) {
            return Labels.EMPTY;
        } else {
            return labels.to();
        }
    }

    public Heading toHeading() {
        if (heading == null) {
            return Heading.EMPTY;
        } else {
            return Heading.of(heading);
        }
    }

}
