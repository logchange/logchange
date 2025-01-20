package dev.logchange.core.format.yml.config.labels;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.config.model.labels.TypesLabels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.HashMap;
import java.util.Map;

@Log
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLTypesLabels {

    public static final YMLTypesLabels EMPTY = YMLTypesLabels.builder().build();

    @JsonProperty(index = 1)
    public Map<String, String> entryTypesLabels = new HashMap<>();

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        if (value instanceof String) {
            entryTypesLabels.put(key, (String) value);
        } else {
            log.warning("Unknown property: " + key + " with value " + value);
        }
    }

    @JsonAnyGetter
    public Map<String, Object> anyGetter() {
        return new HashMap<>(entryTypesLabels);
    }

    @JsonProperty(value = "number_of_changes", index = 100)
    public YMLNumberOfChangesLabels numberOfChanges;

    public static YMLTypesLabels of(TypesLabels types) {
        return YMLTypesLabels.builder()
                .entryTypesLabels(types.getEntryTypesLabels())
                .numberOfChanges(YMLNumberOfChangesLabels.of(types.getNumberOfChanges()))
                .build();
    }

    public TypesLabels to() {
        return TypesLabels.builder()
                .entryTypesLabels(entryTypesLabels)
                .numberOfChanges(getNumberOfChanges().to())
                .build();
    }

    public YMLNumberOfChangesLabels getNumberOfChanges() {
        if (numberOfChanges != null) {
            return numberOfChanges;
        } else {
            return YMLNumberOfChangesLabels.EMPTY;
        }
    }
}
