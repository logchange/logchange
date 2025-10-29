package dev.logchange.core.domain.changelog.model;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfiguration;
import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Delegate;

import java.util.List;


@ToString
@RequiredArgsConstructor
public class DetachedConfiguration implements HasModules, Comparable<DetachedConfiguration> {

    @Delegate(types = ChangelogEntryConfiguration.class)
    private final ChangelogEntryConfiguration configuration;
    private final List<ChangelogModule> modules;

    @Override
    public int compareTo(DetachedConfiguration o) {
        return configuration.compareTo(o.configuration);
    }

    @Override
    public List<ChangelogModule> getModules() {
        return modules;
    }

    public ChangelogEntryConfiguration getConfiguration() {
        return configuration;
    }
}
