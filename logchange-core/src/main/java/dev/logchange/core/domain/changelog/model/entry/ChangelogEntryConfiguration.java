package dev.logchange.core.domain.changelog.model.entry;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;

@Getter
@ToString
@Builder(access = AccessLevel.PRIVATE)
public class ChangelogEntryConfiguration implements Comparable<ChangelogEntryConfiguration> {

    private final String type;
    private final ChangelogEntryConfigurationAction action;
    private final String key;
    private final String defaultValue;
    private final String description;
    private final String moreInfo;

    public static ChangelogEntryConfiguration of(
            String type,
            ChangelogEntryConfigurationAction action,
            String key,
            String defaultValue,
            String description,
            String moreInfo) {
        return ChangelogEntryConfiguration.builder()
                .type(type)
                .action(action)
                .key(key)
                .defaultValue(defaultValue)
                .description(description)
                .moreInfo(moreInfo)
                .build();
    }

    @Override
    public int compareTo(ChangelogEntryConfiguration o) {
        return Comparator.comparing(ChangelogEntryConfiguration::getType)
                .thenComparing(ChangelogEntryConfiguration::getAction)
                .thenComparing(ChangelogEntryConfiguration::getKey)
                .compare(this, o);
    }
}
