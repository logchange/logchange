package dev.logchange.core.format.yml.config;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.CustomLog;
import lombok.NoArgsConstructor;

@CustomLog
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLEntry {

    @JsonProperty(index = 0)
    public Boolean banner;

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warn("Unknown property: " + key + " with value " + value);
    }

    public boolean toBanner() {
        return banner == null || banner;
    }
}
