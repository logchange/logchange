package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

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
        return new YMLChangelogEntryType(name);
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
