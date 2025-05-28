package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;

public class YMLChangelogModule {

    public static YMLChangelogModule of(ChangelogModule module) {
        return new YMLChangelogModule(module.getName());
    }

    private final String name;

    @JsonCreator
    public YMLChangelogModule(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is null or empty");
        }
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public ChangelogModule to() {
        return new ChangelogModule(name);
    }
}
