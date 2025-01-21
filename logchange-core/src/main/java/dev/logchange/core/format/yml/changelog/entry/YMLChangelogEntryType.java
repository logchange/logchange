package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.util.stream.Collectors;

@Log
@AllArgsConstructor
public class YMLChangelogEntryType {

    private final String type;

    static YMLChangelogEntryType of(ChangelogEntryType type) {
        return new YMLChangelogEntryType(type.getKey());
    }

    ChangelogEntryType to() {
        return ChangelogEntryType.fromNameIgnoreCase(type);
    }

    @JsonCreator
    public static YMLChangelogEntryType of(String name) {
        return ChangelogEntryType.values().stream()
                .filter(value -> value.getKey().equals(name))
                .findFirst()
                .map(YMLChangelogEntryType::of)
                .orElseThrow(() -> {
                    String availableType = ChangelogEntryType.values().stream().map(ChangelogEntryType::getKey).collect(Collectors.joining(", "));
                    String message = "Cannot match YMLChangelogEntryType for string: " + name + " - Available types: [" + availableType + "].";
                    log.severe(message);
                    return new IllegalArgumentException(message);
                });
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
