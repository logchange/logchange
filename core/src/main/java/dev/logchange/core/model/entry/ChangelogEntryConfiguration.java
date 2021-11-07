package dev.logchange.core.model.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;

@Getter
@AllArgsConstructor
public class ChangelogEntryConfiguration implements Comparable<ChangelogEntryConfiguration> {

    private final String type;
    private final ChangelogEntryConfigurationAction action;
    private final String key;
    private final String defaultValue;
    private final String description;
    private final String moreInfo;

    @Override
    public int compareTo(ChangelogEntryConfiguration o) {
        return Comparator.comparing(ChangelogEntryConfiguration::getType)
                .thenComparing(ChangelogEntryConfiguration::getAction)
                .thenComparing(ChangelogEntryConfiguration::getKey)
                .compare(this, o);
    }
}
