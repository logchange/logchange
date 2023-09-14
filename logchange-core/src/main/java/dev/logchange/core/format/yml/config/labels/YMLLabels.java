package dev.logchange.core.format.yml.config.labels;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.labels.Labels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLLabels {

    @JsonProperty(index = 0)
    public String unreleased;

    @JsonProperty(value = "important_notes", index = 1)
    public String importantNotes;

    @JsonProperty(index = 2)
    public YMLTypesLabels types;

    @JsonProperty(index = 3)
    public YMLConfigurationLabels configuration;

    public static YMLLabels of(Labels labels) {
        return YMLLabels.builder()
                .unreleased(labels.getUnreleased())
                .importantNotes(labels.getImportantNotes())
                .types(YMLTypesLabels.of(labels.getTypes()))
                .configuration(YMLConfigurationLabels.of(labels.getConfiguration()))
                .build();
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public Labels to() {
        return Labels.builder()
                .unreleased(unreleased)
                .importantNotes(importantNotes)
                .types(getTypes().to())
                .configuration(getConfiguration().to())
                .build();
    }

    public YMLTypesLabels getTypes() {
        if (types != null) {
            return types;
        } else {
            return YMLTypesLabels.EMPTY;
        }
    }

    public YMLConfigurationLabels getConfiguration() {
        if (configuration != null) {
            return configuration;
        } else {
            return YMLConfigurationLabels.EMPTY;
        }
    }
}
