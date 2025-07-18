package dev.logchange.core.domain.changelog.model.entry;

import lombok.Getter;

@Getter
public class ChangelogModule {

    private final String name;

    public ChangelogModule(String name) {
        this.name = name;
    }
}
