package dev.logchange.core.domain.changelog.model;

import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;
import lombok.ToString;

import java.util.List;

@ToString
public class DetachedImportantNote implements HasModules {

    private final String value;
    private final List<ChangelogModule> modules;

    public DetachedImportantNote(String value, List<ChangelogModule> modules) {
        this.value = value;
        this.modules = modules;
    }

    @Override
    public List<ChangelogModule> getModules() {
        return modules;
    }

    public String getValue() {
        return value;
    }
}
