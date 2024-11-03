package dev.logchange.core.format.yml.config.labels;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.labels.NumberOfChangesLabels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Log
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLNumberOfChangesLabels {

    public static final YMLNumberOfChangesLabels EMPTY = YMLNumberOfChangesLabels.builder().build();

    @JsonProperty(index = 0)
    public String singular;

    @JsonProperty(index = 1)
    public String plural;

    public static YMLNumberOfChangesLabels of(NumberOfChangesLabels numberOfChanges) {
        return YMLNumberOfChangesLabels.builder()
                .singular(numberOfChanges.getSingular())
                .plural(numberOfChanges.getPlural())
                .build();
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warning("Unknown property: " + key + " with value " + value);
    }

    public NumberOfChangesLabels to() {
        return NumberOfChangesLabels.builder()
                .singular(singular)
                .plural(plural)
                .build();
    }
}
