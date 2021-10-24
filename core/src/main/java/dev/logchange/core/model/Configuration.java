package dev.logchange.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;

@Getter
@AllArgsConstructor
public class Configuration implements Comparable<Configuration> {

    private final String type;
    private final ConfigurationAction action;
    private final String key;
    private final String defaultValue;
    private final String description;
    private final String moreInfo;

    @Override
    public int compareTo(Configuration o) {
        return Comparator.comparing(Configuration::getType)
                .thenComparing(Configuration::getAction)
                .thenComparing(Configuration::getKey)
                .compare(this, o);
    }
}
