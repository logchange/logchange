package dev.logchange.core.domain.config.model;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import dev.logchange.core.format.yml.config.YMLCustomChangelogEntryType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomChangelogEntryType {

    public static final List<CustomChangelogEntryType> EMPTY = getDefault();

    private final String key;
    private final Integer order;

    public static CustomChangelogEntryType of(String key, Integer order) {
        return new CustomChangelogEntryType(key, order);
    }

    public ChangelogEntryType to(){
        return ChangelogEntryType.of(key, order);
    }

    private static List<CustomChangelogEntryType> getDefault() {
        return ChangelogEntryType.getDefaultEntryTypes().stream()
                .map(e -> of(e.getKey(), e.getOrder()))
                .collect(Collectors.toList());
    }

    public static List<CustomChangelogEntryType> of(List<YMLCustomChangelogEntryType> entryTypes) {
        return entryTypes.stream()
                .map(e -> of(e.getKey(), e.getOrder()))
                .collect(Collectors.toList());
    }
}
