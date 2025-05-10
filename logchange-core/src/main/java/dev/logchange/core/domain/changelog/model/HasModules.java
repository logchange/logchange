package dev.logchange.core.domain.changelog.model;

import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;

import java.util.List;

public interface HasModules {
    List<ChangelogModule> getModules();
}
